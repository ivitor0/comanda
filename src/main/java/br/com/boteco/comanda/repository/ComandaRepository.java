package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaModel, Long> {

    boolean existsById(Long id);

//    @Query("SELECT SUM(c.valorTotalComanda) FROM ComandaModel c " +
//            "WHERE c.dataHoraAbertura BETWEEN :dataInicio AND :dataFim " +
//            "AND c.status = :status")
//    float calcularFaturamentoTotalNoPeriodo(
//            @Param("dataInicio") LocalDate dataInicio,
//            @Param("dataFim") LocalDate dataFim,
//            @Param("status") String status
//    );
//
//    @Query("SELECT new map(c.garcom as garcom, SUM(c.valorTotalComanda) as faturamentoTotal) " + // o new map() retorna uma lista com os valores das colunas selecionadas
//            "FROM ComandaModel c " +
//            "WHERE c.status = 'Fechado' " +
//            "AND c.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
//            "GROUP BY c.garcom " +
//            "ORDER BY SUM(c.valorTotalComanda) DESC")
//    List<Map<String, Object>> findGarcomComMaiorFaturamentoNoPeriodo( //cria uma lista onde cada Map representa uma linha do resultado da consulta
//            @Param("dataInicio") LocalDate dataInicio,
//            @Param("dataFim") LocalDate dataFim);
//
//
//    @Query("SELECT new map(c.formaPagamento as formaPagamento, COUNT(c.formaPagamento) as quantidade) " +
//            "FROM ComandaModel c " +
//            "WHERE c.status = 'Fechado' " +
//            "AND c.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
//            "GROUP BY c.formaPagamento " +
//            "ORDER BY COUNT(c.formaPagamento) DESC")
//    List<Map<String, Object>> findFormaPagamentoMaisUtilizadaNoPeriodo(
//            @Param("dataInicio") LocalDate dataInicio,
//            @Param("dataFim") LocalDate dataFim);


//    @Query("SELECT new map(c.garcom as garcom, SUM(c.valorTotalComanda) as faturamentoTotal) " + // o new map() retorna uma lista com os valores das colunas selecionadas
//            "FROM ComandaModel c " +
//            "WHERE c.status = 'Fechado' " +
//            "AND c.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
//            "GROUP BY c.garcom " +
//            "ORDER BY SUM(c.valorTotalComanda) DESC")
//    List<Map<String, Object>> findByDataHoraAberturaBetween(
//            @Param("dataInicio") LocalDate dataInicio,
//            @Param("dataFim") LocalDate dataFim);

//    List<ComandaModel> findByDataHoraAberturaBetweenAndStatus(LocalDate dataInicio, LocalDate dataFim, String status);

//    @Query("SELECT new br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO(g.nome, SUM(c.valorTotalComanda)) " +
//            "FROM comanda c " +
//            "JOIN garcom g ON c.idGarcom = g.idGarcom" +
//            "WHERE c.dataHoraFechamento BETWEEN :inicio AND :fim " +
//            "GROUP BY g.nome ORDER BY SUM(c.valorTotalComanda) DESC")
//    List<GarcomFaturamentoDTO> findGarcomComMaiorFaturamento(
//            @Param("inicio") LocalDate inicio,
//            @Param("fim") LocalDate fim);

//    @Query(value = """
//    SELECT g.nome, SUM(c.valor_total_comanda) as totalFaturamento
//    FROM comanda c
//    JOIN garcom g ON c.id_garcom = g.id_garcom
//    WHERE c.data_hora_fechamento BETWEEN :inicio AND :fim
//    GROUP BY g.nome
//    ORDER BY totalFaturamento DESC
//    LIMIT 1
//    """, nativeQuery = true)
//    List<Object[]> findGarcomComMaiorFaturamento(
//            @Param("inicio") LocalDate inicio,
//            @Param("fim") LocalDate fim);
}
