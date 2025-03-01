package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ComandaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaModel, Long> {

    boolean existsById(Long id);

    @Query(value = """
    SELECT SUM(valor_total_comanda) as totalFaturamento
    FROM comanda
    WHERE status = 'Fechado' AND
    data_hora_fechamento BETWEEN :inicio AND :fim
    """, nativeQuery = true)
    List<Object[]> findTotalComandas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

    @Query(value =
            """
            SELECT c.id_comanda, SUM(con.preco_total) AS valorTotalConsumo
            FROM comanda c
            JOIN consumo con ON c.id_comanda = con.id_comanda
            WHERE status = :status AND con.data_hora_consumo BETWEEN :inicio AND :fim
            GROUP BY c.id_comanda
            ORDER BY valorTotalConsumo DESC
            """, nativeQuery = true)
    List<Object[]> findComandaMaiorConsumo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            @Param("status") String status);

    @Query(value =
            """
            SELECT id_comanda, AVG(DATEDIFF('MINUTE', data_hora_abertura, data_hora_fechamento)) AS tempoMedio
            FROM comanda
            WHERE status = 'Fechado' AND data_hora_fechamento BETWEEN :inicio AND :fim
            GROUP BY id_comanda
            ORDER BY tempoMedio DESC
            """, nativeQuery = true)
    List<Object[]> findTempoMedioPermanencia(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}
