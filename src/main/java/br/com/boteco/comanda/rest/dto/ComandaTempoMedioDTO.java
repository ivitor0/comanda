package br.com.boteco.comanda.rest.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ComandaTempoMedioDTO {

    @Column(name = "tempoMedio")
    private float tempoMedio;

}
