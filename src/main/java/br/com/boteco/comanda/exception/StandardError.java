package br.com.boteco.comanda.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long timestamp; //Atributo que armazena um timestamp
    private Integer status; //armazena o código do erro
    private String error; // mensagem de erro a ser exibida
    private String message; //o tipo do erro, descriminado
    private String path; // o caminho a string da url
}
