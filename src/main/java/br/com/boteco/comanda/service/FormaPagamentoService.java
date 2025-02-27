package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.BusinessRuleException;
import br.com.boteco.comanda.exception.ConstraintException;
import br.com.boteco.comanda.exception.DataIntegrityException;
import br.com.boteco.comanda.exception.SQLException;
import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.repository.ComandaRepository;
import br.com.boteco.comanda.repository.FormaPagamentoRepository;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

//    @Transactional(readOnly = true)
//    public String getFormaPagamentoMaisUtilizada(LocalDate dataInicio, LocalDate dataFim) {
//
//        List<Map<String, Object>> resultados = comandaRepository.findFormaPagamentoMaisUtilizadaNoPeriodo(dataInicio, dataFim);
//
//        if (resultados.isEmpty()) {
//            return "Nenhum registro encontrado no período especificado.";
//        }
//
//        Map<String, Object> resultado = resultados.getFirst();
//        FormaPagamentoModel formaPagamento = (FormaPagamentoModel) resultado.get("formaPagamento");
//        int quantidade = (int) resultado.get("quantidade");
//
//        return String.format("Forma de pagamento mais utilizada: %s | Quantidade de vezes utilizada: %d",
//                formaPagamento.getDescricao(), quantidade);
//    }
}
