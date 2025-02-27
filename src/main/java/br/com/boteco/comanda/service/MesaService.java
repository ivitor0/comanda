package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.BusinessRuleException;
import br.com.boteco.comanda.exception.ConstraintException;
import br.com.boteco.comanda.exception.DataIntegrityException;
import br.com.boteco.comanda.exception.SQLException;
import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.model.MesaModel;
import br.com.boteco.comanda.repository.MesaRepository;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Transactional(readOnly = true)
    public List<MesaDTO> obterMesa() {
        List<MesaModel> mesas = mesaRepository.findAll();
        return mesas.stream().map(mesa -> mesa.toDTO()).collect(Collectors.toList());
    }

    @Transactional
    public MesaDTO salvarMesa(MesaModel novaMesa){

        try {
            if(mesaRepository.existsByNumero(novaMesa.getNumero())){
                throw new ConstraintException("Já existe uma mesa com esse número: " + novaMesa.getNumero() + " na base de dados");
            }
            //Salva o garcom na base de dados
            return mesaRepository.save(novaMesa).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a mesa: " + novaMesa.getNumero());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional
    public MesaDTO atualizarMesa(MesaModel novaMesa){

        try {
            if(!mesaRepository.existsByNumero(novaMesa.getNumero())){
                throw new ConstraintException("Não existe uma mesa com esse número: " + novaMesa.getNumero() + " na base de dados");
            }
            return mesaRepository.save(novaMesa).toDTO();
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a mesa: " + novaMesa.getNumero());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + " . Falha na conexão com o banco de dados" );
        }
    }

    @Transactional
    public void deletarMesa(MesaModel novaMesa){

        try {
            if(!mesaRepository.existsByNumero(novaMesa.getNumero())){
                throw new ConstraintException("Não existe uma mesa com esse número: " + novaMesa.getNumero() + " na base de dados");
            }
            mesaRepository.delete(novaMesa);
        }catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a mesa: " + novaMesa.getNumero());
        }catch (ConstraintException e){
            throw new ConstraintException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + ". Violação de integridade de dados" );
        }catch (BusinessRuleException e){
            throw new BusinessRuleException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + ". Violação de regra de negócios" );
        }catch (SQLException e){
            throw new SQLException("Erro! Não foi possível salvar a mesa "+ novaMesa.getNumero() + " . Falha na conexão com o banco de dados" );
        }
    }
}


