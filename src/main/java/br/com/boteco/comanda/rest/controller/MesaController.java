package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.MesaModel;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.rest.dto.MesaDTO;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesa")

public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<MesaDTO>> obterMesas(){
        List<MesaDTO> mesaDTOS = mesaService.obterMesa();
        return ResponseEntity.ok(mesaDTOS);
    }

    @PostMapping
    public ResponseEntity<MesaDTO> salvarMesa(@RequestBody @Valid MesaModel novaMesa){
        MesaDTO novaMesaDTO = mesaService.salvarMesa(novaMesa);
        return ResponseEntity.ok(novaMesaDTO);
    }

    @PutMapping
    public ResponseEntity<MesaDTO> atualizaMesa(@RequestBody @Valid MesaModel mesaExistente){
        MesaDTO mesaExistenteDTO = mesaService.atualizarMesa(mesaExistente);
        return ResponseEntity.ok(mesaExistenteDTO);
    }

    @DeleteMapping
    public void deletar(@RequestBody @Valid MesaModel produtoModel){
        mesaService.deletarMesa(produtoModel);
    }

}
