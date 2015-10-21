
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
public class Utilizador implements Serializable{
    private String nome;
    private String palavraPasse;
    private ArrayList<Pledge> pledges;
    private int saldo;
    private ArrayList<Projeto> projetos;

    public Utilizador(String nome, String palavraPasse, int saldo) {
        this.nome = nome;
        this.palavraPasse = palavraPasse;
        this.saldo = saldo;
        pledges = new ArrayList<>();
        projetos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPalavraPasse() {
        return palavraPasse;
    }

    public void setPalavraPasse(String palavraPasse) {
        this.palavraPasse = palavraPasse;
    }

    public ArrayList<Pledge> getPledges() {
        return pledges;
    }

    public void setPledges(ArrayList<Pledge> pledges) {
        this.pledges = pledges;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }
    
    
}
