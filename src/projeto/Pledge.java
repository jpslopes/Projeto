
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
public class Pledge implements Serializable{
    private Projeto projeto;
    private int idProjeto;
    private int doacao;

    public Pledge(int idProjeto, int doacao) {
        this.idProjeto = idProjeto;
        this.doacao = doacao;
        projeto = new Projeto();
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public int getDoacao() {
        return doacao;
    }

    public void setDoacao(int doacao) {
        this.doacao = doacao;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
}
