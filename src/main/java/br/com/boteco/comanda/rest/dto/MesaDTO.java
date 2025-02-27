package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.MesaModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import lombok.Data;

@Data
public class MesaDTO {

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "status", length = 255, nullable = false)
    private String status;

    public MesaModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MesaModel.class);

    }
}
