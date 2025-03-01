package br.com.boteco.comanda.rest.controller;


import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.FormaPagamentoMaisUsadaDTO;
import br.com.boteco.comanda.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime fim) {
        List<FormaPagamentoMaisUsadaDTO> resultado = formaPagamentoService.getFormaMaisUsada(inicio, fim);
        return ResponseEntity.ok(resultado);
    }

}
