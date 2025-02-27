package br.com.boteco.comanda.rest.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarcomFaturamentoDTO {

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "totalFaturado", length = 10, nullable = false)
    private Float totalFaturado;

}
