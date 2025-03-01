package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.repository.ComandaRepository;
import br.com.boteco.comanda.repository.FormaPagamentoRepository;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.FormaPagamentoMaisUsadaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    @Transactional(readOnly = true) //Impede que a solicitação quebre ou não seja finalizada
    public List<FormaPagamentoDTO> obterFormaPagamento() {
        List<FormaPagamentoModel> formasPagamento = formaPagamentoRepository.findAll();
        return formasPagamento.stream().map(formaPagamento -> formaPagamento.toDTO()).collect(Collectors.toList());
    }

    @Transactional
    public FormaPagamentoDTO salvarFormaPagamento(FormaPagamentoModel novaFormaPagamento){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf já existente
            if(formaPagamentoRepository.existsByNome(novaFormaPagamento.getNome())){
                throw new ConstraintException("Já existe uma forma de pagamento com esse Nome: " + novaFormaPagamento.getNome() + " na base de dados");
            }
            //Salva o garcom na base de dados
            return formaPagamentoRepository.save(novaFormaPagamento).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a forma de pagamento: " + novaFormaPagamento.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional
    public FormaPagamentoDTO atualizarFormaPagamento(FormaPagamentoModel novaFormaPagamento){

        try {
            if(!formaPagamentoRepository.existsByNome(novaFormaPagamento.getNome())){
                throw new ConstraintException("Não existe uma forma de pagamento com esse Nome: " + novaFormaPagamento.getNome() + " na base de dados");
            }
            return formaPagamentoRepository.save(novaFormaPagamento).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a forma de pagamento: " + novaFormaPagamento.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }
    @Transactional
    public void deletarFormaPagamento(FormaPagamentoModel novaFormaPagamento){

        try {
            if(!formaPagamentoRepository.existsByNome(novaFormaPagamento.getNome())){
                throw new ConstraintException("Não existe uma forma de pagamento com esse Nome: " + novaFormaPagamento.getNome() + " na base de dados");
            }
            formaPagamentoRepository.delete(novaFormaPagamento);
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a forma de pagamento: " + novaFormaPagamento.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a forma de pagamento "+ novaFormaPagamento.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional(readOnly = true)
    public List<FormaPagamentoMaisUsadaDTO>  getFormaMaisUsada(LocalDateTime dataInicio, LocalDateTime dataFim) {

        try {
            List<Object[]> resultados = formaPagamentoRepository.findFormaMaisUsada(dataInicio, dataFim);

            if (resultados.isEmpty()) {
//                return new List<new ProdutoMaisVendidoDTO(null, 0)> //resolver esse retorno

            }
            return resultados.stream()
                    .map(obj -> new FormaPagamentoMaisUsadaDTO(
                                    (String) obj[0],
                                    ((Number) obj[1]).intValue()
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
}
