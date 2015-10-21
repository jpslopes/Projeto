
import java.io.Serializable;
import java.util.ArrayList;

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
public class Votacao implements Serializable{
    ArrayList<Opcao> opcoes;
    String pergunta;

    public Votacao(String pergunta) {
        this.pergunta = pergunta;
        opcoes = new ArrayList<>();
    }

    public ArrayList<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(ArrayList<Opcao> opcoes) {
        this.opcoes = opcoes;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
    
    
}
