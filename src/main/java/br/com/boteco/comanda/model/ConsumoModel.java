package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consumo")

public class ConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsumo")
    private Long idConsumo;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "dataHoraConsumo", nullable = false)
    @NotNull(message = "Não admite valor nulo")
    private LocalDateTime dataHoraConsumo;

    @Column(name = "precoUnitarioVendido", nullable = false)
    private float precoUnitarioVendido;

    @Column(name = "precoTotal", nullable = false)
    private float precoTotal;

    @Column(name = "idComanda")
    private Long comanda;

    @Column(name = "idProduto")
    private Long idProduto;

    public ConsumoDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConsumoDTO.class);
    }

}
