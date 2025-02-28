package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.rest.dto.ComandaDTO;
import br.com.boteco.comanda.rest.dto.TotalComandasDTO;
import br.com.boteco.comanda.service.ComandaService;
import br.com.boteco.comanda.service.GarcomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public ResponseEntity<List<ComandaDTO>> obterTodas() {
        List<ComandaDTO> comandas = comandaService.obterTodas();
        return ResponseEntity.ok(comandas);
    }

    @PostMapping
    public ResponseEntity<ComandaDTO> salvar(@RequestBody @Valid ComandaModel novaComanda) {
        ComandaDTO novaComandaDTO = comandaService.salvarComanda(novaComanda);
        return ResponseEntity.ok(novaComandaDTO);
    }


    @PutMapping
    public ResponseEntity<ComandaDTO> atualizar(@RequestBody @Valid ComandaModel comandaExistente) {
        ComandaDTO comandaAtualizadaDTO = comandaService.atualizarComanda(comandaExistente);
        return ResponseEntity.ok(comandaAtualizadaDTO);
    }

    @DeleteMapping()
    public void deletar(@RequestBody @Valid ComandaModel comandaModel) {
        comandaService.deletarComanda(comandaModel);
    }

    @GetMapping("/faturamento-total")
    public ResponseEntity<List<TotalComandasDTO>> calcularFaturamentoTotalNo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        List<TotalComandasDTO> resultado = comandaService.calcularFaturamentoTotalNoPeriodo(inicio, fim);
        return ResponseEntity.ok(resultado);
    }

}
