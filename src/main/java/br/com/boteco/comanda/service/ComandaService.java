package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.repository.ComandaRepository;
import br.com.boteco.comanda.rest.dto.ComandaDTO;
import br.com.boteco.comanda.rest.dto.ComandaMaiorConsumoDTO;
import br.com.boteco.comanda.rest.dto.ComandaTempoMedioDTO;
import br.com.boteco.comanda.rest.dto.TotalComandasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;


    @Transactional(readOnly = true)
    public List<ComandaDTO> obterTodas() {
        List<ComandaModel> comandas = comandaRepository.findAll();
        return comandas.stream()
                .map(ComandaModel::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ComandaDTO salvarComanda(ComandaModel novaComanda) {

        try {
            return comandaRepository.save(novaComanda).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a comanda.");
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Violação de regra de negócios ao salvar a comanda.");
        } catch (SQLException e) {
            throw new SQLException("Erro! Falha na conexão com o banco de dados ao salvar a comanda.");
        }
    }

    @Transactional
    public ComandaDTO atualizarComanda(ComandaModel comandaExistente) {
        try {
            if (!comandaRepository.existsById(comandaExistente.getComanda())) {
                throw new ObjectNotFoundException("Comanda não encontrada.");
            }
            return comandaRepository.save(comandaExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar a comanda.");
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Violação de regra de negócios ao atualizar a comanda.");
        } catch (SQLException e) {
            throw new SQLException("Erro! Falha na conexão com o banco de dados ao atualizar a comanda.");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Comanda não encontrada.");
        }
    }

    @Transactional
    public void deletarComanda(ComandaModel comandaExistente) {
        try {
            if (!comandaRepository.existsById(comandaExistente.getComanda())) {
                throw new ObjectNotFoundException("Comanda não encontrada.");
            }
            comandaRepository.deleteById(comandaExistente.getComanda());
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar a comanda.");
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Violação de regra de negócios ao deletar a comanda.");
        } catch (br.com.boteco.comanda.exception.SQLException e) {
            throw new SQLException("Erro! Falha na conexão com o banco de dados ao deletar a comanda.");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Comanda não encontrada.");
        }
    }


    public List<TotalComandasDTO> getFaturamentoTotalNoPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        try {

            List<Object[]> resultados = comandaRepository.findTotalComandas(dataInicio, dataFim);

            return resultados.stream().map(obj ->
                    new TotalComandasDTO(
                            ((Number) obj[0]).floatValue()
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
    public List<ComandaMaiorConsumoDTO> getMaiorConsumo(LocalDateTime dataInicio, LocalDateTime dataFim, String status) {
        try {

            List<Object[]> resultados = comandaRepository.findComandaMaiorConsumo(dataInicio, dataFim, status);

            return resultados.stream().map(obj ->
                    new ComandaMaiorConsumoDTO(
                            ((Number) obj[0]).longValue(),
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
    public List<ComandaTempoMedioDTO> getTempoMedio(LocalDateTime dataInicio, LocalDateTime dataFim) {
        try {

            List<Object[]> resultados = comandaRepository.findTempoMedioPermanencia(dataInicio, dataFim);

            return resultados.stream().map(obj ->
                    new ComandaTempoMedioDTO(
                            ((Number) obj[0]).floatValue()
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
