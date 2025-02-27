package br.com.boteco.comanda.model;


import br.com.boteco.comanda.rest.dto.ComandaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comanda")

public class ComandaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda")
    private Long comanda;

    @Column(name = "dataHoraAbertura", nullable = false)
    @NotNull(message = "Não admite valor nulo")
    private LocalDate dataHoraAbertura;

    @Column(name = "dataHoraFechamento", nullable = false)
    @NotNull(message = "Não admite valor nulo")
    //@NotBlank(message = "Não admite valor em branco")
    private LocalDate dataHoraFechamento;

    @Column(name = "valorTotalComanda", nullable = false)
    private float valorTotalComanda;

    @Column(name = "valorGorjeta", nullable = false)
    private float valorGorjeta;

    @Column(name = "status", length = 255, nullable = false)
    private String status;

    @NotNull(message = "O campo não pode ser nulo.")
    @Column(name = "idMesa", length = 255, nullable = false)
    private Long idMesa;

    @NotNull(message = "O campo não pode ser nulo.")
    @Column(name = "idformaPagamento", length = 255, nullable = false)
    private Long idformaPagamento;

    @NotNull(message = "O campo não pode ser nulo.")
    @Column(name = "idGarcom", length = 255, nullable = false)
    private Long idGarcom;

    public ComandaDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaDTO.class);
    }

}
