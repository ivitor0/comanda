package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.GarcomModel;
import br.com.boteco.comanda.repository.GarcomRepository;
import br.com.boteco.comanda.rest.dto.GarcomDTO;
import br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GarcomService {

    @Autowired
    private GarcomRepository garcomRepository;

    @Transactional(readOnly = true) //Impede que a solicitação quebre ou não seja finalizada
    public List<GarcomDTO> obterTodos(){
        List<GarcomModel> garcons = garcomRepository.findAll(); //SELECT * FROM Table[garcom]
        return garcons.stream()//Faz a função do laço for i
                .map(garcom -> garcom.toDTO()) //mapeia cada a elemento da lista de Model e transforma em DTO
                .collect(Collectors.toList()); //Converte o elemento gerado pelo stream() em lista (uma lista de DTO)

    }
    @Transactional
    public GarcomDTO salvar(GarcomModel novoGarcom){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf já existente
            if(garcomRepository.existsByCpf(novoGarcom.getCpf())){
                throw new ConstraintException("Já existe um garçom com esse CPF: " + novoGarcom.getCpf() + " na base de dados");
            }
            if(garcomRepository.existsByEmail(novoGarcom.getEmail())){
                //Caso ocorra uma tentativa de salvar um novo garçom com email já existente
                throw new ConstraintException("Já existe um garçom com esse E-MAIL: " + novoGarcom.getEmail() + " na base de dados");
            }
            //Salva o garcom na base de dados
            return garcomRepository.save(novoGarcom).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o garçom: " + novoGarcom.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o garçom "+ novoGarcom.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o garçom "+ novoGarcom.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o garçom "+ novoGarcom.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }
    @Transactional
    public GarcomDTO atualizar(GarcomModel garcomExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf que não exista utliazando o cpf
            if(!garcomRepository.existsByCpf(garcomExistente.getCpf())){
                throw new ConstraintException("O garçom com esse CPF: " + garcomExistente.getCpf() + " não na base de dados");
            }
            //Salva o garcom na base de dados
            return garcomRepository.save(garcomExistente).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o garçom: " + garcomExistente.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar o garçom "+ garcomExistente.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar o garçom "+ garcomExistente.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o garçom "+ garcomExistente.getNome() + " . Falha na conexão com o banco de dados" );
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o garçom "+ garcomExistente.getNome() + " . Garçom não encontrado");
        }
    }
    @Transactional
    public void deletar (GarcomModel garcomExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf que não exista utliazando o cpf
            if(!garcomRepository.existsByCpf(garcomExistente.getCpf())){
                throw new ConstraintException("O garçom com esse CPF: " + garcomExistente.getCpf() + " não na base de dados");
            }
            //Salva o garcom na base de dados
            garcomRepository.delete(garcomExistente);
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o garçom: " + garcomExistente.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível deletar o garçom "+ garcomExistente.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível deletar o garçom "+ garcomExistente.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o deletar "+ garcomExistente.getNome() + " . Falha na conexão com o banco de dados" );
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o garçom "+ garcomExistente.getNome() + " . Garçom não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public List<GarcomFaturamentoDTO> getGarcomComMaiorFaturamento(LocalDateTime dataInicio, LocalDateTime dataFim) {

        try {

            List<Object[]> resultados = garcomRepository.findGarcomComMaiorFaturamento(dataInicio, dataFim);

            return resultados.stream().map(obj ->
                    new GarcomFaturamentoDTO(
                            (String) obj[0],
                            ((Number) obj[1]).floatValue()
                    )
            ).collect(Collectors.toList());

        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível obter o resultado");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível obter o resultado "+ ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível obter o resultado "+ ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível obter o resultado "+ " . Falha na conexão com o banco de dados" );
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível obter o resultado "+ " . Resultado não encontrado");
        }


    }


//        List<ComandaModel> comanda = comandaRepository.findByDataHoraAberturaBetweenAndStatus(dataInicio, dataFim, "Fechado");
//        Map<GarcomModel, Float> faturamentoPorGarcom = (Map<GarcomModel, Float>) comanda.stream().collect(Collectors.groupingBy(ComandaModel::getIdGarcom, Collectors.reducing(Float.MIN_VALUE, p -> Float.valueOf(p.getValorTotalComanda()), Float::sum)));;
//
////        if (resultado.isEmpty()) {
////            return new GarcomFaturamentoDTO("Nenhum", Float.MIN_NORMAL);
////        }
//
//        Optional<Map.Entry<GarcomModel, Float>> garcomComMaiorFaturamento = faturamentoPorGarcom.entrySet()
//                .stream()
//                .max(Comparator.comparing(Map.Entry::getValue));;
//
//        return garcomComMaiorFaturamento
//                .map(entry -> new GarcomFaturamentoDTO(entry.getKey().getNome(), entry.getValue()))
//                .orElse(new GarcomFaturamentoDTO("Nenhum", Float.MIN_VALUE));
//    }

}
