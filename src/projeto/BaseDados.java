/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package projeto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author João
 */
public class BaseDados implements Serializable{
    private ArrayList<Projeto> projetosAtuais;
    private ArrayList<Projeto> projetosAntigos;
    private ArrayList<Utilizador> utilizadores;

    public BaseDados() {
        projetosAtuais = new ArrayList<>();
        projetosAntigos = new ArrayList<>();
        utilizadores = new ArrayList<>();
    }

    public ArrayList<Projeto> getProjetosAtuais() {
        return projetosAtuais;
    }

    public void setProjetosAtuais(ArrayList<Projeto> projetosAtuais) {
        this.projetosAtuais = projetosAtuais;
    }

    public ArrayList<Projeto> getProjetosAntigos() {
        return projetosAntigos;
    }

    public void setProjetosAntigos(ArrayList<Projeto> projetosAntigos) {
        this.projetosAntigos = projetosAntigos;
    }

    public ArrayList<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(ArrayList<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }
    
    
}
