package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.repository.ConsumoRepository;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Transactional(readOnly = true)
    public List<ConsumoDTO> obterTodos() {
        List<ConsumoModel> consumos = consumoRepository.findAll();
        return consumos.stream()
                .map(ConsumoModel::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumoDTO salvar(ConsumoModel novoConsumo) {
        try {
            // Verifica se já existe um consumo com o mesmo idComanda e idProduto
            if (consumoRepository.existsById(novoConsumo.getIdConsumo())) {
                throw new ConstraintException("Já existe um consumo com essa comanda e produto na base de dados");
            }
            // Salva o consumo na base de dados
            return consumoRepository.save(novoConsumo).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar o consumo.");
        } catch (ConstraintException e) {
            throw new ConstraintException("Erro! Não foi possível salvar o consumo. Violação de integridade de dados");
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar o consumo. Violação de regra de negócios");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar o consumo. Falha na conexão com o banco de dados");
        }
    }

    @Transactional
    public ConsumoDTO atualizar(ConsumoModel consumoExistente) {
        try {
            // Verifica se o consumo existe na base de dados
            if (!consumoRepository.existsById(consumoExistente.getIdConsumo())) {
                throw new ObjectNotFoundException("O consumo com ID: " + consumoExistente.getIdConsumo() + " não foi encontrado na base de dados");
            }
            // Atualiza o consumo na base de dados
            return consumoRepository.save(consumoExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o consumo.");
        } catch (ConstraintException e) {
            throw new ConstraintException("Erro! Não foi possível atualizar o consumo. Violação de integridade de dados");
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar o consumo. Violação de regra de negócios");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o consumo. Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o consumo. Consumo não encontrado");
        }
    }

    @Transactional
    public void deletar(ConsumoModel consumoExistente) {
        try {
            // Verifica se o consumo existe na base de dados
            if (!consumoRepository.existsById(consumoExistente.getIdConsumo())) {
                throw new ObjectNotFoundException("O consumo com ID: " + consumoExistente.getIdConsumo() + " não foi encontrado na base de dados");
            }
            // Deleta o consumo da base de dados
            consumoRepository.delete(consumoExistente);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o consumo.");
        } catch (ConstraintException e) {
            throw new ConstraintException("Erro! Não foi possível deletar o consumo. Violação de integridade de dados");
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o consumo. Violação de regra de negócios");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar o consumo. Falha na conexão com o banco de dados");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o consumo. Consumo não encontrado");
        }
    }

}