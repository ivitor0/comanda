package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    boolean existsByNome(String nome); //O nome do método "existsByNome", após o BY tem que estar igual ao nome no DTO

    @Query(value =
            """
            SELECT p.nome, SUM(con.quantidade) AS quantidadeTotal
            FROM consumo con
            JOIN produto p ON con.id_produto = p.id_produto
            WHERE con.data_hora_consumo BETWEEN :inicio AND :fim
            GROUP BY p.nome
            ORDER BY quantidadeTotal DESC
            """, nativeQuery = true)
    List<Object[]> findProdutoMaisVendido(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

}
