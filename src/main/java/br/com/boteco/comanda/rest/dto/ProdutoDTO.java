package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ProdutoModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class ProdutoDTO {
    @Column(name = "nome", length = 255, nullable = false)
    @NotNull(message = "Não admite valor nulo")
    @NotBlank(message = "Não admite valores vazios")
    private String nome;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "preco")
    private float preco;

    @Column(name = "status", length = 255, nullable = false)
    private String status;

    public ProdutoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProdutoModel.class);
    }
}
