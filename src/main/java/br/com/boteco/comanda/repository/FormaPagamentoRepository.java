package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.FormaPagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoModel, Long> {

    boolean existsByNome(String nome);

    @Query(value =
            """
            SELECT fp.nome, COUNT(c.id_forma_pagamento) AS quantidade
            FROM comanda c
            JOIN forma_pagamento fp ON c.id_forma_pagamento = fp.id_forma_pagamento
            WHERE c.status = 'Fechado' AND c.data_hora_fechamento BETWEEN :inicio AND :fim
            GROUP BY fp.nome
            ORDER BY quantidade DESC
            """, nativeQuery = true)
    List<Object[]> findFormaMaisUsada(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

}
