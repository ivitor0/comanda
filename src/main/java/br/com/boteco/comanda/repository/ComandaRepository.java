package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ComandaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaModel, Long> {

    boolean existsById(Long id);


//    @Query("SELECT new map(c.formaPagamento as formaPagamento, COUNT(c.formaPagamento) as quantidade) " +
//            "FROM ComandaModel c " +
//            "WHERE c.status = 'Fechado' " +
//            "AND c.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
//            "GROUP BY c.formaPagamento " +
//            "ORDER BY COUNT(c.formaPagamento) DESC")
//    List<Map<String, Object>> findFormaPagamentoMaisUtilizadaNoPeriodo(
//            @Param("dataInicio") LocalDate dataInicio,
//            @Param("dataFim") LocalDate dataFim);


    @Query(value = """
    SELECT SUM(valor_total_comanda) as totalFaturamento
    FROM comanda
    WHERE status = 'Fechado' AND
    data_hora_fechamento BETWEEN :inicio AND :fim
    """, nativeQuery = true)
    List<Object[]> findTotalComandas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}
