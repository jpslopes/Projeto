
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
public class Recompensa implements Serializable{
    private int valorAInvestir;
    private String descricao; 

    public Recompensa(int valorAInvestir, String descricao) {
        this.valorAInvestir = valorAInvestir;
        this.descricao = descricao;
    }

    public int getValorAInvestir() {
        return valorAInvestir;
    }

    public void setValorAInvestir(int valorAInvestir) {
        this.valorAInvestir = valorAInvestir;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
