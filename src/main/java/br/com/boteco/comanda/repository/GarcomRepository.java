package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.GarcomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GarcomRepository extends JpaRepository<GarcomModel, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    @Query(value = """
    SELECT g.nome, SUM(c.valor_total_comanda) as totalFaturamento
    FROM comanda c
    JOIN garcom g ON c.id_garcom = g.id_garcom
    WHERE c.data_hora_fechamento BETWEEN :inicio AND :fim
    GROUP BY g.nome
    ORDER BY totalFaturamento DESC
    LIMIT 1
    """, nativeQuery = true)
    List<Object[]> findGarcomComMaiorFaturamento(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

}
