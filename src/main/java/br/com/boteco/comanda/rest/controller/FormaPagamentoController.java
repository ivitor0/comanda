package br.com.boteco.comanda.rest.controller;


import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.FormaPagamentoMaisUsadaDTO;
import br.com.boteco.comanda.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/formapagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> obterProdutos(){
        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.obterFormaPagamento();
        return ResponseEntity.ok(formaPagamentoDTOS);
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> salvarProduto(@RequestBody @Valid FormaPagamentoModel novaformaPagamento){
        FormaPagamentoDTO novaformaPagamentoDTO = formaPagamentoService.salvarFormaPagamento(novaformaPagamento);
        return ResponseEntity.ok(novaformaPagamentoDTO);
    }

    @PutMapping
    public ResponseEntity<FormaPagamentoDTO> atualizarProduto(@RequestBody @Valid FormaPagamentoModel produtoExistente){
        FormaPagamentoDTO formaExistenteDTO = formaPagamentoService.atualizarFormaPagamento(produtoExistente);
        return ResponseEntity.ok(formaExistenteDTO);
    }

    @DeleteMapping
    public void deletar(@RequestBody @Valid FormaPagamentoModel produtoExistente){
        formaPagamentoService.deletarFormaPagamento(produtoExistente);
    }

    @GetMapping("/mais-usada")
    public ResponseEntity<List<FormaPagamentoMaisUsadaDTO>> calcularFormaMaisUsada(
            @Valid
            @RequestParam String inicio,
            @RequestParam String fim) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataInicio = LocalDateTime.parse(inicio, formatter);
        LocalDateTime dataFim = LocalDateTime.parse(fim, formatter);

        List<FormaPagamentoMaisUsadaDTO> resultado = formaPagamentoService.getFormaMaisUsada(dataInicio, dataFim);
        return ResponseEntity.ok(resultado);

    }

}
