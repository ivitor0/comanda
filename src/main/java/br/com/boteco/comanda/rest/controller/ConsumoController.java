package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.service.ConsumoService;
import br.com.boteco.comanda.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ConsumoDTO>> obterConsumos() {
        List<ConsumoDTO> consumoDTOs = consumoService.obterTodos();
        return ResponseEntity.ok(consumoDTOs);
    }

    @PostMapping
    public ResponseEntity<ConsumoDTO> salvarConsumo(@RequestBody @Valid ConsumoModel novoConsumo) {
        ConsumoDTO novoConsumoDTO = consumoService.salvar(novoConsumo);
        return ResponseEntity.ok(novoConsumoDTO);
    }

    @PutMapping
    public ResponseEntity<ConsumoDTO> atualizarConsumo(@RequestBody @Valid ConsumoModel consumoExistente) {
        ConsumoDTO consumoExistenteDTO = consumoService.atualizar(consumoExistente);
        return ResponseEntity.ok(consumoExistenteDTO);
    }

    @DeleteMapping
    public void deletarConsumo(@RequestBody @Valid ConsumoModel consumoExistente) {
        consumoService.deletar(consumoExistente);
    }

}