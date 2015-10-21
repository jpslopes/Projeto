import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
public class ClienteTCP {

    public static void main(String args[]) {
        // args[0] <- hostname do destinosdf
        if (args.length == 0) {
            System.out.println("java TCPClient hostname");
            System.exit(0);
        }
        //Scanner sc = new Scanner(System.in);
        Socket s = null;
        int serversocket = 6000;
        int vf = 0;
        String opcao;
        int numProjetos;
        String aux;
        String resposta;
        //String palavraPasse;

        try {
            // 1o passo
            s = new Socket(args[0], serversocket);

            System.out.println("SOCKET=" + s);

            // 2o passo
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);

            //Cria a thread que irá ler dos sockets
            //new LeSocket(in);
            //Apresenta as opções do menu principal
            do {
                System.out.println("---Fund Starter---\n1 - Listar projetos atuais\n2 - Listar projetos antigos\n3 - Consultar detalhes de um projeto\n4 - Registar conta"
                        + "\n5 - Login\n-1 - Sair");
                System.out.printf("Opção: ");

                opcao = reader.readLine();

                switch (opcao) {
                    case "1":
                        //Opção «Listagem de Projetos»
                        out.writeUTF("1");
                        listaProjetos(in);
                        break;
                    case "2":
                        //Opção «Listagem de Projetos Antigos»
                        out.writeUTF("2");
                        listaProjetosAntigos(in);
                        break;
                    case "3": //<---------------------------------------------------------------------------O código neste case não está mal? tipo é detalhes de um projeto e está a imprimir todos os projetos...
                        //Opção «Detalhes de um Projeto»
                        out.writeUTF("3");
                        mostraDetalhes(in);
                        break;
                    case "4":
                        //Opção «Registar Conta»
                        out.writeUTF("4");

                        //Irá registar uma conta (Começa com um saldo de 100€)
                        System.out.printf("\n---Registar---\n");
                        do {
                            System.out.printf("Nome de utilizador: ");
                            aux = reader.readLine();
                            out.writeUTF(aux);

                            resposta = in.readUTF();

                            //Irá verificar se já existe um utilizador com esta conta
                            if (resposta.equalsIgnoreCase("nao")) {
                                System.out.printf("Palavra-passe: ");
                                aux = reader.readLine();
                                out.writeUTF(aux);

                                resposta = in.readUTF();

                                if (resposta.equalsIgnoreCase("feito")) {
                                    System.out.println("Conta criada com sucesso.\n");
                                    vf = 1;
                                } else {
                                    System.out.println("Houve um problema e a conta não foi criada.\n");
                                }
                            } else {
                                System.out.println("Já existe uma conta registada com este nome. Escolha outro por favor.\n");
                            }
                        } while (vf == 0);
                        vf = 0;
                        break;
                    case "5":
                        //Opção «Login»
                        out.writeUTF("5");

                        String nomeUtilizador;

                        //Irá fazer login de uma conta já existente
                        System.out.printf("\n---Login---\n");
                        do {
                            System.out.printf("Nome de utilizador: ");
                            nomeUtilizador = reader.readLine();
                            out.writeUTF(nomeUtilizador);

                            System.out.printf("Palavra-passe: ");
                            aux = reader.readLine();
                            out.writeUTF(aux);

                            //Recebe a confirmação se o Login foi feito ou não com sucesso
                            resposta = in.readUTF();
                            if (resposta.equalsIgnoreCase("sim")) {
                                System.out.println("Login efetuado com êxito.\n");
                                vf = 1;
                            } else {
                                System.out.println("O nome do utilizador ou a palavra-passe está incorreto. Volte a tentar por favor.\n");
                            }
                        } while (vf == 0);
                        vf = 0;
                        do {
                            System.out.println("---Utilizador: " + nomeUtilizador + " ---\n1 - Listar projetos atuais\n2 - Listar projetos antigos\n3 - Consultar detalhes de um projeto\n4 - Consultar saldo"
                                    + "\n5 - Consultar recompensas\n6 - Doar dinheiro ao projeto\n7 - Enviar mensagem para um projeto\n8 - Meus projetos\n9 - Logout");
                            System.out.println("Opção: ");

                            opcao = reader.readLine();

                            switch (opcao) {
                                case "1":
                                    out.writeUTF("1");
                                    listaProjetos(in);
                                    break;
                                case "2":
                                    out.writeUTF("2");
                                    listaProjetosAntigos(in);
                                    break;
                                case "3":
                                    out.writeUTF("3");
                                    mostraDetalhes(in);
                                    break;
                                case "4":
                                    out.writeUTF("4");
                                    break;
                                case "5":
                                    out.writeUTF("5");
                                    break;
                                case "6":
                                    out.writeUTF("6");
                                    break;
                                case "7":
                                    out.writeUTF("7");
                                    System.out.println("1 - Criar um projeto\n2 - Cancelar um projeto\n3 - Adicionar recompensas\n4 - Remover recompensas\n5 - Responder a mensagens\n6 - Voltar atras");
                                    System.out.println("Opção: ");
                                    opcao = reader.readLine();

                                    switch (opcao) {
                                        case "1":
                                            out.writeUTF("1");
                                            criarProjeto(in, out);
                                            break;
                                        case "2":
                                            out.writeUTF("2");
                                            break;
                                        case "3":
                                            out.writeUTF("3");

                                            break;
                                        case "4":
                                            out.writeUTF("4");
                                            break;
                                        case "5":
                                            out.writeUTF("5");
                                            break;
                                        case "6":
                                            out.writeUTF("6");
                                            break;
                                    }
                                    break;
                                case "8":
                                    break;
                                case "9":
                                    break;
                            }
                        } while (vf == 0);

                        break;
                }
                System.out.println("");

            } while (!opcao.equalsIgnoreCase("-1"));

            //A opção escolhida foi -1 logo irá sair
            out.writeUTF("-1");
            System.out.println("O cliente irá sair!");

            // 3o passo
            //while (true) {
            // READ STRING FROM KEYBOARD
            // System.out.println("Introduza texto:");
            //try {
            //texto = reader.readLine();
            //} catch (Exception e) {
            //}
                // WRITE INTO THE SOCKET
            //out.writeUTF(texto);
            //}
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
    }

    public static void listaProjetos(DataInputStream in) throws IOException {
        String aux;

        while (!(aux = in.readUTF()).equals("fim")) {
            System.out.println(aux);
        }
    }

    public static void listaProjetosAntigos(DataInputStream in) throws IOException {
        int numProjetos;
        String aux;

        //Irá listar todos os projetos antigos
        System.out.println("\n---Listagem de Projetos Antigos---\n");
        numProjetos = Integer.parseInt(in.readUTF());

        //Verifica se o número de projetos é não nulo
        if (numProjetos > 0) {
            for (int i = 0; i < numProjetos; i++) {

                                //Recebe o id do projeto
                //aux = in.readUTF();
                                //Recebe o nome do Projeto
                //System.out.printf("[ ID = " + aux + "] -> " + in.readUTF());
                                //Recebe o valor doado
                //aux = in.readUTF();
                                //Recebe a meta (valor que tem que ser atingido)
                //System.out.println(" -> " + aux + " / " + in.readUTF());
                                //Recebe o id, o nome, o valor doado e a meta final de dinheiro doado
                aux = in.readUTF(); //<- recebe tudo de uma vez

                System.out.println(aux);

            }
        } else {
            System.out.println("Não existem projetos antigos...\n");
        }
    }

    public static void mostraDetalhes(DataInputStream in) throws IOException {
        int numProjetos;
        //Irá consultar detalhes de um dado projeto
        System.out.println("\n---Listagem de Projetos---\n");
        numProjetos = Integer.parseInt(in.readUTF());

        //Verifica se o número de projetos é nulo
        if (numProjetos > 0) {
            for (int i = 0; i < numProjetos; i++) {

                //Recebe o nome do Projeto
                System.out.println("[" + i + "] -> " + in.readUTF());

            }
        } else {
            System.out.println("Não existem projetos ativos...\n");
        }
    }

    public static void criarProjeto(DataInputStream in, DataOutputStream out) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String nome, aux;
        int vf = 0;

        do {
            System.out.println("Insira o nome que desja para o projeto: ");
            nome = reader.readLine();
            out.writeUTF(nome);
            if (in.readUTF().equalsIgnoreCase("sim")) {
                vf = 1;
            } else {
                System.out.println("Já existe um projeto com esse nome. Exprimente outro nome");
            }
        } while (vf != 0);

        System.out.println("Insira uma descrição do projeto: ");
        aux = reader.readLine();
        out.writeUTF(aux);

        vf = 0;
        do {
            System.out.println("Quer adicionar uma recompensa? (s/n)");
            String a = reader.readLine();

            if (a.equals("sim")) {
                System.out.println("Doação para receber a recompensa: ");
                aux = reader.readLine() + " ";

                System.out.println("Descrição da recompensa: ");
                aux += reader.readLine();
            } else {
                vf = 1;
            }
        } while (vf != 0);
    }

    public static void removeProjeto(DataInputStream in, DataOutputStream out) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String aux;
        int vf = 0;

        do {

            /*Listar projetos criados pelo user*/
            /*CODE*/
            System.out.println("Insira o id do projeto que deseja eliminar: ");
            out.writeUTF(reader.readLine());

            if (in.readUTF().equals("sim")) {
                vf = 1;
            }

        } while (vf != 0);

    }
}

class LeSocket extends Thread {

    DataInputStream in;

    LeSocket(DataInputStream in) {
        this.in = in;

        this.start();
    }

    public void run() {
        String resposta;
        try {
            while (true) {
                // READ FROM SOCKET
                String data = in.readUTF();

                // DISPLAY WHAT WAS READ
                System.out.println("Received: " + data);
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e);
        } catch (IOException e) {
            System.out.println("IO:" + e);
        }
    }
}
