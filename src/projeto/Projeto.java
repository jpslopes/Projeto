
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
public class Projeto implements Serializable {

    private Calendar dataLimite;
    private ArrayList<Recompensa> recompensas;
    private String descricao;
    private int dinheiroPretendido;
    private int dinheiroDoado;
    private int id;
    private ArrayList<Mail> mailbox;
    //private ArrayList<Participante> participantes;
    private String nome;
    private Utilizador admin;
    private Votacao vot;

    public Projeto() {
    }
    
    public Projeto(int id) {
        this.id = id;
        recompensas = new ArrayList<>();
        mailbox = new ArrayList<>();
    }
    
    public Calendar getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Calendar dataLimite) {
        this.dataLimite = dataLimite;
    }

    public ArrayList<Recompensa> getRecompensas() {
        return recompensas;
    }

    public void setRecompensas(ArrayList<Recompensa> recompensas) {
        this.recompensas = recompensas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDinheiroPretendido() {
        return dinheiroPretendido;
    }

    public void setDinheiroPretendido(int dinheiroPretendido) {
        this.dinheiroPretendido = dinheiroPretendido;
    }

    public int getDinheiroDoado() {
        return dinheiroDoado;
    }

    public void setDinheiroDoado(int dinheiroDoado) {
        this.dinheiroDoado = dinheiroDoado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Mail> getMailbox() {
        return mailbox;
    }

    public void setMailbox(ArrayList<Mail> mailbox) {
        this.mailbox = mailbox;
    }

    //public ArrayList<Participante> getParticipantes() {
        //return participantes;
    //}

    //public void setParticipantes(ArrayList<Participante> participantes) {
        //this.participantes = participantes;
    //}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Utilizador getAdmin() {
        return admin;
    }

    public void setAdmin(Utilizador admin) {
        this.admin = admin;
    }

    public Votacao getVot() {
        return vot;
    }

    public void setVot(Votacao vot) {
        this.vot = vot;
    }
    
    
    
}
