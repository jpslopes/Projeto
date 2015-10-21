import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import sun.org.mozilla.javascript.internal.ScriptRuntime;
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
public class ServidorDados extends UnicastRemoteObject implements InterfaceDados {
//final BaseDados bD = new BaseDados();
    private BaseDados bD;
    protected ServidorDados() throws RemoteException {
        super();

        bD = new BaseDados();
    }
    public String funcaoA() {
        return bD.getUtilizadores().get(0).getNome();
    }
    
    public Projeto funcaoB() {
        System.out.println("B -> " + bD.getProjetosAtuais().get(0).getNome());
        return bD.getProjetosAtuais().get(0);
    }
    
    public BaseDados getBD() {    
        return bD;
    }
    public void setBD(BaseDados a) {
        //bD = a;
    }
    //public void colocaDadosLidos(BaseDados bD) {
    //this.bD = bD;
    //}
    public void leFicheiro() {
        String linha;
        StringTokenizer divisor;
        Ficheiro users;
        Ficheiro user;
        Ficheiro projetos;
        Ficheiro projeto;
        try {
            users = new Ficheiro();
            users.abreLeitura("users.txt");
            user = new Ficheiro();
            projetos = new Ficheiro();
            projetos.abreLeitura("projetos.txt");
            projeto = new Ficheiro();

            //Lê os utilizadores
            try {
                while ((linha = users.getfR().readLine()) != null) {
                    try {
                        System.out.println("DEBUG -> utilizador: " + linha);
                        user.abreLeitura(linha + ".txt");
                        //StringTokenizer divisor = new StringTokenizer(linha);
                        try {
                            Utilizador utilizador;
                            //Lê a password
                            String password = user.getfR().readLine();
                            //Lê o saldo
                            int saldo = Integer.parseInt(user.getfR().readLine());

                            //Cria o utilizador
                            utilizador = new Utilizador(linha, password, saldo);

                            //Lê os pledges que este utilizador já fez
                            while ((linha = user.getfR().readLine()) != null) {
                                divisor = new StringTokenizer(linha);
                                utilizador.getPledges().add(new Pledge(Integer.parseInt(divisor.nextToken()), Integer.parseInt(divisor.nextToken())));
                            }

                            //Adiciona o utilizador à base de dados
                            bD.getUtilizadores().add(utilizador);

                            user.getfR().close();
                        } catch (IOException e) {
                            System.out.println("Não li nada do Ficheiro " + linha + ".txt");
                        }
                    } catch (IOException e) {
                        System.out.println("Nao foi possivel abrir o Ficheiro");
                    }
                }
                users.getfR().close();
            } catch (IOException e) {
                System.out.println("Não li nada do Ficheiro users.txt");
            }
            
            //Lê os projetos
            try {
                while ((linha = projetos.getfR().readLine()) != null) {
                    try {
                        System.out.println("DEBUG -> projeto: " + linha);
                        projeto.abreLeitura("proj" + linha + ".txt");
                        //StringTokenizer divisor = new StringTokenizer(linha);
                        try {
                            Projeto proj;
                            String nome = linha;
                            //Lê o id do projeto
                            int id = Integer.parseInt(projeto.getfR().readLine());

                            //Cria o projeto
                            proj = new Projeto(id);
                            proj.setNome(nome);
                            //Lê a data
                            linha = projeto.getfR().readLine();
                            divisor = new StringTokenizer(linha);

                            int dia = Integer.parseInt(divisor.nextToken());
                            int mes = Integer.parseInt(divisor.nextToken());
                            int ano = Integer.parseInt(divisor.nextToken());
                            Calendar data = new GregorianCalendar();
                            switch (mes) {
                                case 1:
                                    data = new GregorianCalendar(ano, Calendar.JANUARY, dia);
                                    break;
                                case 2:
                                    data = new GregorianCalendar(ano, Calendar.FEBRUARY, dia);
                                    break;
                                case 3:
                                    data = new GregorianCalendar(ano, Calendar.MARCH, dia);
                                    break;
                                case 4:
                                    data = new GregorianCalendar(ano, Calendar.APRIL, dia);
                                    break;
                                case 5:
                                    data = new GregorianCalendar(ano, Calendar.MAY, dia);
                                    break;
                                case 6:
                                    data = new GregorianCalendar(ano, Calendar.JUNE, dia);
                                    break;
                                case 7:
                                    data = new GregorianCalendar(ano, Calendar.JULY, dia);
                                    break;
                                case 8:
                                    data = new GregorianCalendar(ano, Calendar.AUGUST, dia);
                                    break;
                                case 9:
                                    data = new GregorianCalendar(ano, Calendar.SEPTEMBER, dia);
                                    break;
                                case 10:
                                    data = new GregorianCalendar(ano, Calendar.OCTOBER, dia);
                                    break;
                                case 11:
                                    data = new GregorianCalendar(ano, Calendar.NOVEMBER, dia);
                                    break;
                                case 12:
                                    data = new GregorianCalendar(ano, Calendar.DECEMBER, dia);
                                    break;
                            }
                            //Guarda a data
                            proj.setDataLimite(data);

                            //Lê a descrição
                            String desc = projeto.getfR().readLine();

                            //Guarda a descrição
                            proj.setDescricao(desc);

                            int vf = 0;
                            String pr;
                            //Lê as recompensas
                            do {
                                linha = projeto.getfR().readLine();
                                divisor = new StringTokenizer(linha);

                                pr = divisor.nextToken();

                                if (pr.equalsIgnoreCase("recompensa")) {

                                    int valor = Integer.parseInt(divisor.nextToken());

                                    String dR = "";
                                    while (divisor.hasMoreTokens()) {
                                        dR = dR + " " + divisor.nextToken();
                                    }

                                    //Cria a recompensa
                                    Recompensa rec = new Recompensa(valor, dR);

                                    //Adiciona-a ao projeto
                                    proj.getRecompensas().add(rec);
                                } else {
                                    vf = 1;
                                }
                            } while (vf == 0);
                            vf = 0;

                            //Lê o dinheiro pedido (meta)
                            if (pr.equals("dinheirop")) {
                                //pr = divisor.nextToken();
                                //System.out.println("PR - " + pr);
                                //int v = Integer.parseInt(divisor.nextToken());
                                //int v = Integer.parseInt(pr);
                                //System.out.println("V - " + v);

                                proj.setDinheiroPretendido(Integer.parseInt(divisor.nextToken()));
                            } else {
                                System.out.println("Erro ao ler o projeto " + proj.getNome() + " (ficheiro: " + proj.getNome() + ".txt" + " <- não encontrou o dinheiro pedido (meta do projeto)...");
                            }

                            linha = projeto.getfR().readLine();
                            divisor = new StringTokenizer(linha);
                            pr = divisor.nextToken();

                            //Lê o dinheiro já doado (investido)
                            if (pr.equals("dinheirod")) {
                                int v = Integer.parseInt(divisor.nextToken());

                                proj.setDinheiroDoado(v);
                            } else {
                                System.out.println("Erro ao ler o projeto " + proj.getNome() + " (ficheiro: " + proj.getNome() + ".txt" + " <- não encontrou o dinheiro já doado...");
                            }

                            //Lê o dono do projeto
                            String nomeDono = projeto.getfR().readLine();
                            int pos = 0;
                            for (int i = 0; i < bD.getUtilizadores().size(); i++) {

                                if (bD.getUtilizadores().get(i).getNome().equalsIgnoreCase(nomeDono)) {
                                    pos = i;
                                    proj.setAdmin(bD.getUtilizadores().get(i));

                                    //bD.getUtilizadores().get(i).getProjetos().add(proj);
                                    i = bD.getUtilizadores().size();
                                }
                            }

                            //Lê os mails (se existirem)
                            do {
                                linha = projeto.getfR().readLine();
                                divisor = new StringTokenizer(linha);

                                pr = divisor.nextToken();

                                if (pr.equalsIgnoreCase("mail")) {

                                    String remetente = divisor.nextToken();

                                    String msg = "";
                                    while (divisor.hasMoreTokens()) {
                                        msg = msg + " " + divisor.nextToken();
                                    }

                                    //Procura o remetente no ArrayList dos utilizadores
                                    for (int i = 0; i < bD.getUtilizadores().size(); i++) {

                                        if (bD.getUtilizadores().get(i).getNome().equalsIgnoreCase(remetente)) {
                                            //Cria o mail
                                            Mail mail = new Mail(bD.getUtilizadores().get(i), msg);

                                            //Adiciona-o ao projeto
                                            proj.getMailbox().add(mail);

                                            i = bD.getUtilizadores().size();
                                        }
                                    }
                                } else {
                                    vf = 1;
                                }
                            } while (vf == 0);
                            vf = 0;

                            //Verifica se existe uma votação decorrente
                            if (pr.equalsIgnoreCase("votacao")) {

                                //Verifica se existe votação
                                if (!divisor.nextToken().equalsIgnoreCase("nao")) {
                                    String perg = "";
                                    while (divisor.hasMoreTokens()) {
                                        perg = perg + " " + divisor.nextToken();
                                    }

                                    Votacao votacao = new Votacao(perg);

                                    while ((linha = projeto.getfR().readLine()) != null) {

                                        divisor = new StringTokenizer(linha);

                                        votacao.getOpcoes().add(new Opcao(Integer.parseInt(divisor.nextToken()), divisor.nextToken()));
                                    }
                                    
                                    proj.setVot(votacao);
                                }
                            } else {
                                System.out.println("Erro ao ler o projeto " + proj.getNome() + " (ficheiro: " + proj.getNome() + ".txt" + " <- não sabe se existe uma votação...");
                            }

                            //Vai colocar um ponteiro do projeto nos pledges que lhe foram feitos (se existir algum)
                            for (int i = 0; i < bD.getUtilizadores().size(); i++) {
                                for (int j = 0; j < bD.getUtilizadores().get(i).getPledges().size(); j++) {

                                    if (bD.getUtilizadores().get(i).getPledges().get(j).getIdProjeto() == proj.getId()) {
                                        bD.getUtilizadores().get(i).getPledges().get(j).setProjeto(proj);
                                    }
                                }
                            }
                            
                            data
                            
                            //Acabou de ler o projeto
                            //Verifica se está ativo ou se é antigo
                            if (proj.getDataLimite().compareTo(new GregorianCalendar()) < 0) {

                                bD.getProjetosAtuais().add(proj);
                                System.out.println("Atual");
                            } else {
                                bD.getProjetosAntigos().add(proj);
                                System.out.println("Antigo");
                            }
                            projeto.getfR().close();
                            
                        } catch (IOException e) {
                            System.out.println("Não li nada do Ficheiro " + linha + ".txt");
                        }
                    } catch (IOException e) {
                        System.out.println("Nao foi possivel abrir o Ficheiro");
                    }
                }
                projetos.getfR().close();
            } catch (IOException e) {
                System.out.println("Não li nada do Ficheiro projetos.txt");
            }
        } catch (IOException e) {
            System.out.println("Nao foi possivel abrir o Ficheiro");
        }
    }
    
    private Calendar getCalendar(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c;
    }

    //<--------------------------------------------------------Funções como a de guardar dados, entre outras
    public static void main(String[] args) throws RemoteException {

        ServidorDados servidorDados = new ServidorDados();

        //Inicialmente lê do ficheiro e coloca os dados na base de dados
        //servidorDados.colocaDadosLidos(servidorDados.leFicheiro());
        servidorDados.leFicheiro();

        InterfaceDados iDados = servidorDados;

        LocateRegistry.createRegistry(2499).rebind("dados", servidorDados);
        System.out.println("O servidor de dados está pronto...");
        
        //System.out.println("A - " + bD.getProjetosAtuais().get(0).getDinheiroDoado());
    }
}
