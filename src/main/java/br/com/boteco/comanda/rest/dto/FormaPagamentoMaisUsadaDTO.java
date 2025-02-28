package br.com.boteco.comanda.rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoMaisUsadaDTO {

    @Column
    @NotNull(message = "Não admite valor nulo")

    private String nome;

    @Column(name = "quantidade")
    private int quantidade;

}
