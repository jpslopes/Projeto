
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
public class Participante implements Serializable{ // <- para já não é para utilizar....
    private Utilizador utilizador;
    private int doacao;

    public Participante(Utilizador utilizador, int doacao) {
        this.utilizador = utilizador;
        this.doacao = doacao;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public int getDoacao() {
        return doacao;
    }

    public void setDoacao(int doacao) {
        this.doacao = doacao;
    }

    
}
