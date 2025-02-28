package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ComandaModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
@Data
public class ComandaDTO {
    @Column(name = "dataHoraAbertura", nullable = false)
    private LocalDate dataHoraAbertura;

    @Column(name = "dataHoraFechamento")
    private LocalDate dataHoraFechamento;

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
