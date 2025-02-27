package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ConsumoModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
public class ConsumoDTO {
    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "dataHoraConsumo", nullable = false)
    private LocalDate dataHoraConsumo;

    @Column(name = "precoUnitarioVendido", nullable = false)
    private float precoUnitarioVendido;

    @Column(name = "precoTotal", nullable = false)
    private float precoTotal;

    @JoinColumn(name = "idComanda", nullable = false)
    private Long idComanda;

    @JoinColumn(name = "idProduto", nullable = false)
    private Long idProduto;

    public ConsumoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConsumoModel.class);
    }
}
