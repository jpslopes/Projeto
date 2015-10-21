import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class ServidorTCP {

    public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException {
        int numero = 0;
        //ArrayList<Socket> users = new ArrayList();
        try {
            int serverPort = 6000;
            System.out.println("À Escuta no Porto 6000");
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("LISTEN SOCKET=" + listenSocket);
            InterfaceDados iDados;

            try {
                iDados = (InterfaceDados) LocateRegistry.getRegistry(2499).lookup("dados");

                while (true) {
                    Socket clientSocket = listenSocket.accept(); // Bloqueante
                    System.out.println("CLIENT_SOCKET (created at accept())=" + clientSocket);
                    //synchronized (users) {
                    //users.add(clientSocket);
                    //}
                    numero++;
                    new Connection(clientSocket, numero, iDados);
                }
            } catch (Exception e) {
                System.out.println("Exceção: " + e);
            }
        } catch (IOException e) {
            System.out.println("Listen:" + e.getMessage());
        }
    }
}
//= Thread para tratar de cada canal de comunica��o com um cliente

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    int thread_number;
    //ArrayList<Socket> dataSocket;
    InterfaceDados iDados;

    public Connection(Socket aClientSocket, int numero, InterfaceDados iDados) {
        thread_number = numero;
        this.iDados = iDados;
        //synchronized(dataSocket){
        // this.dataSocket = dataSocket;
        //}
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Conexão: " + e.getMessage());
        }
    }

    //=============================
    public void run() {
        String resposta, opcao, nome, palavraPasse, aux;
        int numProjetos, vf = 0;

        try {
            //DEBUG
            synchronized (iDados) {
                //System.out.println("Aqui -> " + iDados.getBD().getUtilizadores().get(0).getNome());
                System.out.println("aqui -> " + iDados.funcaoA());
                System.out.println("proj -> " + iDados.funcaoB().getId());
            }
            while (true) {
                //an echo server
                opcao = in.readUTF();

                switch (opcao) {
                    case "1":
                        //Opção «Listagem de Projetos»

                        listaProjetosAtuais();
                        break;
                    case "2":
                        //Opção «Listagem de Projetos Antigos»

                        //Irá verificar com o Servidor de Dados se existem projetos ativos
                        out.writeUTF("2");
                        numProjetos = 1;
                        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Implementar Código---

                        if (numProjetos > 0) {
                            //Irá enviar informação relevante relativa a cad projeto ativo
                            for (int i = 0; i < numProjetos; i++) {
                                //Irá enviar o id do projeto
                                //aux = ... (recebe do Servidor de Dados)
                                //out.writeUTF(1 + "");

                                //Irá enviar o nome do projeto
                                //aux = ... (recebe do Servidor de Dados)
                                //out.writeUTF("FitJumpingRope");
                                //Irá enviar o dinheiro doado
                                //aux = ... (recebe do Servidor de Dados)
                                //out.writeUTF(5500 + "");
                                //Irá enviar a meta de dinheiro
                                //aux = ... (recebe do Servidor de Dados)
                                //out.writeUTF(7500 + "");
                                out.writeUTF(1 + " -> " + "FitJumpingRope" + " -> " + "5500" + "/" + "7500");

                            }
                        }
                        break;
                    case "3":

                        break;
                    case "4":
                        //Opção «Registar Conta»
                        //Recebe o nome de utilizador
                        nome = in.readUTF();

                        //Irá verificar se já existe este nome usando o servidor de dados
                        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Implementar Código---
                        out.writeUTF("nao");

                        //Recebe a palavra-passe
                        palavraPasse = in.readUTF();

                        //Irá registar o utilizador usando o servidor de dados e confirmar se a conta foi criada (se não ocorreram problemas)
                        out.writeUTF("feito");

                        break;
                    case "5":
                        //Opção «Login»
                        do {
                            //Recebe o nome de utilizador e a palavra-passe
                            nome = in.readUTF();
                            palavraPasse = in.readUTF();

                            //Irá verificar se existe uma conta com este nome e palavra-passe, comunicando para isso com o ServidorDados
                            for (int i = 0; i < iDados.getBD().getUtilizadores().size(); i++) {
                                //Irá procurar o nome
                                if (nome.equals(iDados.getBD().getUtilizadores().get(i).getNome())) {

                                    //Irá confirmar a password
                                    if (palavraPasse.equals(iDados.getBD().getUtilizadores().get(i).getPalavraPasse())) {

                                        //Caso a password esteja correta
                                        vf = 1;
                                        i = iDados.getBD().getUtilizadores().size();
                                        out.writeUTF("sim");
                                    } else {
                                        //Caso a password esteja incorreta
                                        vf = 2;
                                        i = iDados.getBD().getUtilizadores().size();
                                        out.writeUTF("nao");
                                    }
                                }
                            }
                            if (vf == 0) {
                                //Caso não encontre o utilizador
                                out.writeUTF("nao");
                            }
                        } while (vf != 1);

                        String opcao2 = in.readUTF();
                        switch (opcao2) {
                            case "1":
                                break;

                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                /*Vai enviar o saldo*/
                                out.writeUTF("100");
                                break;
                            case "5":
                                break;
                            case "6":
                                break;
                            case "7":
                                String opcao3 = in.readUTF();

                                switch (opcao3) {
                                    case "1":
                                        Projeto newProj = new Projeto();
                                        vf = 0;

                                        do {
                                            String nomeProj = in.readUTF();

                                            /*Verifica se o nome já é utilizado*/
                                            /*CODE*/
                                            out.writeUTF("sim");
                                            vf = 1;
                                            newProj.setNome(nomeProj);
                                        } while (vf != 0);

                                        newProj.setDescricao(in.readUTF());

                                        vf = 0;
                                        do {
                                            if (in.readUTF().equals("sim")) {
                                                Recompensa newRecom;
                                                int valor = Integer.parseInt(in.readUTF());
                                                String desc = in.readUTF();

                                                newRecom = new Recompensa(valor, desc);

                                                newProj.getRecompensas().add(newRecom);
                                            } else {
                                                vf = 1;
                                            }
                                        } while (vf != 0);
                                        break;

                                    case "2":
                                        vf = 0;

                                        do {
                                            /*Envia informaçao de todos os projetos*/
                                            /*CODE*/
                                        } while (vf == 1); //<------- mudar isto (apenas para não dar erro)

                                        break;
                                }

                                break;

                        }

                        break;

                    case "-1":
                        //Irá sair, logo irá "finalizar" a thread
                        //try { <-----------------não sei se precisa deste código ou se é imediata a terminação da thread
                        //in.close();
                        //out.close();
                        //this.finalize();
                        // } catch (Throwable ex) {
                        //  Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                        //}
                        break;

                }
                /*System.out.println("T["+thread_number + "] Recebeu: "+data);*/
                //resposta = data.toUpperCase();
                /*out.writeUTF(resposta);*/

                //for (int i = 0; i < dataSocket.size(); i++) {
                //synchronized (dataSocket) {
                //new DataOutputStream(dataSocket.get(i).getOutputStream()).writeUTF(resposta);
                //}
                //}
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e);
        } catch (Exception e) {
            System.out.println("Exceção:" + e);
        }
    }

    public void listaProjetosAtuais() throws RemoteException {
        try {

            for (int i = 0; i < iDados.getBD().getProjetosAtuais().size(); i++) {

                Projeto proj = iDados.getBD().getProjetosAtuais().get(i);

                //System.out.printf("\nData: " + iDados.getBD().getProjetosAtuais().get(i).getDataLimite().getTime().getDate() + "/" + iDados.getBD().getProjetosAtuais().get(i).getDataLimite().getTime().getMonth() + "/" + iDados.getBD().getProjetosAtuais().get(i).getDataLimite().getTime().getYear());
                //System.out.printf(" | Descrição: " + iDados.getBD().getProjetosAtuais().get(i).getDescricao());
                //for (int j=0 ; j < iDados.getBD().getProjetosAtuais().get(i).getRecompensas().size() ; j++) {
                    
                    //System.out.printf(" | Recompensa " + j + " -> valor: " + iDados.getBD().getProjetosAtuais().get(i).getRecompensas().get(j).getValorAInvestir() + " -> desc: " + iDados.getBD().getProjetosAtuais().get(i).getRecompensas().get(j).getDescricao());
                //}
                //System.out.printf(" | Dinheiro Pretendido: " + proj.getDinheiroPretendido() + " e Dinheiro Doado: " + proj.getDinheiroDoado());
                 //System.out.printf(" | Admin: " + iDados.getBD().getProjetosAtuais().get(i).getAdmin().getNome());
                  //for (int j=0 ; j < iDados.getBD().getProjetosAtuais().get(i).getMailbox().size() ; j++) {
                    
                    //System.out.printf(" | Mail " + j + " -> remetente: " + proj.getMailbox().get(j).getUtilizador().getNome() + " -> msg: " + proj.getMailbox().get(j).getMensagem());
                //}
                  //System.out.printf(" | Votacao: " + proj.getVot().getPergunta());
                       //           for (int j=0 ; j < proj.getVot().getOpcoes().size() ; j++) {
                    
                    //System.out.printf(" | Vot opcao " + j + " -> n votos: " + proj.getVot().getOpcoes().get(j).getRes() + " -> desc: " + proj.getVot().getOpcoes().get(j).getDescricao());
                //}  
                out.writeUTF(proj.getId() + " -> " + proj.getNome() + " -> " + proj.getDinheiroDoado() + "/" + proj.getDinheiroPretendido());
            }
            
            out.writeUTF("fim");
        } catch (Exception e) {
            System.out.println("Erro na função listaProjetosAtuais(): " + e);
        }
    }
}
