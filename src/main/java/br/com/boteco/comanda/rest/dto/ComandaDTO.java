package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ComandaModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ComandaDTO {
    @Column(name = "dataHoraAbertura", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraAbertura;

    @Column(name = "dataHoraFechamento")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraFechamento;

    @Column(name = "valorTotalComanda", nullable = false)
    private float valorTotalComanda;

    @Column(name = "valorGorjeta", nullable = false)
    private float valorGorjeta;

    @Column(name = "status", length = 255, nullable = false)
    private String status;

    @JoinColumn(name = "idMesa", nullable = false)
    private Long idMesa;

    @JoinColumn(name = "idGarcom", nullable = false)
    private Long idGarcom;

    @JoinColumn(name = "idFormaPagamento", nullable = false)
    private Long idFormaPagamento;



    public ComandaModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaModel.class);
    }

}
