package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.MesaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mesa")

public class MesaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesa")
    private Long idMesa;

    @Column(name = "numero", nullable = false)
    @NotNull(message = "Não admite valor nulo")
    //@NotBlank(message = "Não admite valores vazios")
    private String numero;

    @Column(name = "status", length = 255, nullable = false)
    private String status;

    public MesaDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MesaDTO.class);
    }
}
