package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ConsumoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Repository
public interface ConsumoRepository extends JpaRepository<ConsumoModel, Long> {

    boolean existsById(Long id);

//    @Query("SELECT new map(p.nome as nomeProduto, SUM(c.quantidade) as quantidadeTotal) " +
//            "FROM ConsumoModel c " +
//            "JOIN c.produto p " +
//            "JOIN c.comanda cm " +
//            "WHERE c.dataHoraConsumo >= :dataInicio AND c.dataHoraConsumo <= :dataFim " +
//            "AND cm.status = 'Fechado' " +
//            "GROUP BY p.nome " +
//            "ORDER BY SUM(c.quantidade) DESC")
//    List<Map<String, Object>> findProdutoMaisVendidoNoPeriodo(
//            @Param("dataInicio") LocalDate dataInicio,
//            @Param("dataFim") LocalDate dataFim);

}
