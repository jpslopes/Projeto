
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package projeto;

/**
 *
 * @author João
 */
public class Nivel implements Serializable{
    private int valorRecebido;
    private String descricao;

    public Nivel(int valorRecebido, String descricao) {
        this.valorRecebido = valorRecebido;
        this.descricao = descricao;
    }

    public int getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(int valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
