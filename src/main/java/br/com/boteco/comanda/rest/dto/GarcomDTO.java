package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.GarcomModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GarcomDTO {

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;


    @Column(name = "dataNascimento", nullable = false)
    private LocalDateTime dataNascimento;


    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;


    @Column(name = "telefone", length = 11, nullable = false, unique = true)
    private String telefone;

    @Email(message = "E-mail inválido")
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;


    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    public GarcomModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, GarcomModel.class);
    }
}
