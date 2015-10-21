/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author João
 */
public class Ficheiro {
   
    private BufferedReader fR;
    private BufferedWriter fW;

    public Ficheiro() throws IOException{
       
    }

    public BufferedReader getfR() {
        return fR;
    }

    public void setfR(BufferedReader fR) {
        this.fR = fR;
    }

    public BufferedWriter getfW() {
        return fW;
    }

    public void setfW(BufferedWriter fW) {
        this.fW = fW;
    }
    
    public void abreLeitura(String nomeDoFicheiro) throws IOException{
        fR = new BufferedReader(new FileReader(nomeDoFicheiro));
    }
        
    public void abreEscrita(String nomeDoFicheiro) throws IOException{
        fW = new BufferedWriter(new FileWriter(nomeDoFicheiro,true));
    }
        
    public String leLinha() throws IOException{
        return fR.readLine();
    }
        
    public void escreveLinha(String linha) throws IOException{
        fW.write(linha,0,linha.length());
        fW.newLine();
    }
}
