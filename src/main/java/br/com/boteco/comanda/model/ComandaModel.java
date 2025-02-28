package br.com.boteco.comanda.model;


import br.com.boteco.comanda.rest.dto.ComandaDTO;
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
    @Column(name = "idFormaPagamento", length = 255, nullable = false)
    private Long idFormaPagamento;

    @NotNull(message = "O campo não pode ser nulo.")
    @Column(name = "idGarcom", length = 255, nullable = false)
    private Long idGarcom;

    public ComandaDTO toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaDTO.class);
    }

}
