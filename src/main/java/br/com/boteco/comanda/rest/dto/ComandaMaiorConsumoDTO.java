package br.com.boteco.comanda.rest.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ComandaMaiorConsumoDTO {
    @Column(name = "idComanda")
    private Long comanda;

    @Column(name = "valorTotalComanda", nullable = false)
    private float valorTotalComanda;

}
