package br.com.boteco.comanda.service;


import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.repository.ProdutoRepository;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.rest.dto.ProdutoMaisVendidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true) //Impede que a solicitação quebre ou não seja finalizada
    public List<ProdutoDTO> obterProdutos() {
        List<ProdutoModel> produtos = produtoRepository.findAll(); //SELECT * FROM Table[garcom]
        return produtos.stream().map(produto -> produto.toDTO()).collect(Collectors.toList());
    }

    @Transactional
    public ProdutoDTO salvarProduto(ProdutoModel novoProduto){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf já existente
            if(produtoRepository.existsByNome(novoProduto.getNome())){
                throw new ConstraintException("Já existe um produto com esse Nome: " + novoProduto.getNome() + " na base de dados");
            }
            //Salva o garcom na base de dados
            return produtoRepository.save(novoProduto).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o produto: " + novoProduto.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o produto "+ novoProduto.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o produto "+ novoProduto.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o produto "+ novoProduto.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional
    public ProdutoDTO atualizarProduto(ProdutoModel produtoExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf já existente
            if(!produtoRepository.existsByNome(produtoExistente.getNome())){
                throw new ConstraintException("Não existe um produto com esse Nome: " + produtoExistente.getNome() + " na base de dados");
            }
            //Salva o garcom na base de dados
            return produtoRepository.save(produtoExistente).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o produto: " + produtoExistente.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o produto "+ produtoExistente.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o produto "+ produtoExistente.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o produto "+ produtoExistente.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional
    public void deletarProduto(ProdutoModel produtoExistente){

        try {
            //Caso ocorra uma tentativa de salvar um novo garçom com cpf já existente
            if(!produtoRepository.existsByNome(produtoExistente.getNome())){
                throw new ConstraintException("Não existe um produto com esse Nome: " + produtoExistente.getNome() + " na base de dados");
            }
            //Salva o garcom na base de dados
            produtoRepository.delete(produtoExistente);
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o produto: " + produtoExistente.getNome());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar o produto "+ produtoExistente.getNome() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar o produto "+ produtoExistente.getNome() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar o produto "+ produtoExistente.getNome() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional(readOnly = true)
    public List<ProdutoMaisVendidoDTO>  identificarProdutoMaisVendido(LocalDateTime dataInicio, LocalDateTime dataFim) {

        try {
            List<Object[]> resultados = produtoRepository.findProdutoMaisVendido(dataInicio, dataFim);

            if (resultados.isEmpty()) {
//                return new List<new ProdutoMaisVendidoDTO(null, 0)> //resolver esse retorno

            }
            return resultados.stream()
                    .map(obj -> new ProdutoMaisVendidoDTO(
                            (String) obj[0],
                            ((Number) obj[1]).intValue()
                    )
            ).collect(Collectors.toList());

        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o garçom: ");
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível atualizar o garçom "+ ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível atualizar o garçom "+ ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível atualizar o garçom "+ " . Falha na conexão com o banco de dados" );
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o garçom "+ " . Garçom não encontrado");
        }

    }
}

