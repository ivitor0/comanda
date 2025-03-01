package br.com.boteco.comanda.rest.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoMaisVendidoDTO {

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private int quantidade;

}
