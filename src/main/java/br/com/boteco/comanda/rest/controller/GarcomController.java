package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.GarcomModel;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO;
import br.com.boteco.comanda.service.GarcomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController //Identifica a classe como controller
@RequestMapping("/garcom") //Direciona a requisição para o Controller Garcom
public class GarcomController {

    @Autowired
    private GarcomService garcomService;

    @GetMapping
    public ResponseEntity<List<GarcomDTO>> obterTodos(){ //<?> == Resposta generica. É mais usal já utilizar o tipo de objeto de retorno do Service
        List<GarcomDTO> garcomDTOS = garcomService.obterTodos(); //Chamando o Service, para ele ir no Repository e então ir no BD
        return ResponseEntity.ok(garcomDTOS); //.ok é a resposta 200 do http
    }

    @PostMapping
    public ResponseEntity<GarcomDTO> salvar(@RequestBody @Valid GarcomModel novoGarcom){ //@RequestBody == Avisa que o objeto está no corpo da requisição @Valid == já faz as validações que colocamos no Model, sem precisar chegar o BD para validar
        GarcomDTO novoGarcomDTO = garcomService.salvar(novoGarcom);
        return ResponseEntity.ok(novoGarcomDTO);
    }

    @PutMapping
    public ResponseEntity<GarcomDTO> atualizar(@RequestBody @Valid GarcomModel garcomExistente){
        GarcomDTO garcomExistenteDTO = garcomService.atualizar(garcomExistente);
        return ResponseEntity.ok(garcomExistenteDTO);
    }

    @DeleteMapping
    public void deletar(@RequestBody @Valid GarcomModel garcomModel){
        garcomService.deletar(garcomModel);
    }

    @GetMapping("/maior-faturamento")
    public ResponseEntity<List<GarcomFaturamentoDTO>> calcularGarcomComMaiorFaturamento(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        List<GarcomFaturamentoDTO> resultado = garcomService.getGarcomComMaiorFaturamento(inicio, fim);
        return ResponseEntity.ok(resultado);
    }
}
