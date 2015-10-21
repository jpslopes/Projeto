
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
public class Opcao implements Serializable{
    int vot;
    String descricao;

    public Opcao(int vot, String descricao) {
        this.vot = vot;
        this.descricao = descricao;
    }

    public int getRes() {
        return vot;
    }

    public void setRes(int vot) {
        this.vot = vot;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
