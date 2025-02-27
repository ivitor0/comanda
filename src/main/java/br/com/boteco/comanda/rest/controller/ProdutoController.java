package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController //Identifica a classe como controller
@RequestMapping("/produto") //Direciona a requisição para o Controller Garcom
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> obterProdutos(){
        List<ProdutoDTO> produtoDTOS = produtoService.obterProdutos();
        return ResponseEntity.ok(produtoDTOS);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody @Valid ProdutoModel novoProduto){
        ProdutoDTO novoProdutoDTO = produtoService.salvarProduto(novoProduto);
        return ResponseEntity.ok(novoProdutoDTO);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizarProduto(@RequestBody @Valid ProdutoModel produtoExistente){
        ProdutoDTO produtoExistenteDTO = produtoService.atualizarProduto(produtoExistente);
        return ResponseEntity.ok(produtoExistenteDTO);
    }

    @DeleteMapping
    public void deletar(@RequestBody @Valid ProdutoModel produtoModel){
        produtoService.deletarProduto(produtoModel);
    }

//    @GetMapping("/mais-vendido")
//    public ResponseEntity<String> identificarProdutoMaisVendido(
//            @RequestParam LocalDate dataInicio,
//            @RequestParam LocalDate dataFim) {
//
//        if (dataInicio.isAfter(dataFim)) {
//            return ResponseEntity.badRequest().body("A data de início deve ser anterior à data de fim.");
//        }
//
//        String resultado = produtoService.identificarProdutoMaisVendido(dataInicio, dataFim);
//
//        return ResponseEntity.ok(resultado);
//    }
}

