package mail_server;

import classes.Funcionario;
import classes.SecureDate;
import classes.Solicitacao;
import classes.Suporte;
import dao.Conexao;
import dao.DAOFuncionario;
import dao.DAOSolicitacao;
import dao.DAOSuporte;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import utilidades.JavaMailApp;

public class Mail_Server implements Serializable {

    private static final DAOSolicitacao daos = new DAOSolicitacao();
    private static final DAOSuporte daosp = new DAOSuporte();
    private static final SecureDate scd = new SecureDate();
    private static boolean checker = true;
    private static String limite = "";
    private static JavaMailApp jma = null;
    private static ArrayList<Solicitacao> listSolAnt = null;
    private static DAOFuncionario daof = new DAOFuncionario();
    private static ArrayList<Suporte> listSupAnt = null;
    private static ArrayList<Solicitacao> auxSol = new ArrayList<>();
    private static ArrayList<Suporte> auxSp = new ArrayList<>();
    private static ArrayList<Funcionario> listF = null;
    private static int totalSup = 0;
    private static int totalSol = 0;
    private static boolean isRunning = false;
    private static ArrayList<Suporte> listSup = new ArrayList<>();
    private static ArrayList<Solicitacao> listSol = new ArrayList<>();

    public static boolean envioSolicitacao(Solicitacao obj) {
        try {
            if (obj.getData_limite() != null) {
                limite = scd.formatHoras(new java.sql.Date(obj.getData_limite().getTime()));
            } else {
                limite = "Sem limite de tempo para responder";
            }
            if (obj.getNumero().charAt(0) == 'S') {
                jma = new JavaMailApp(obj.getLocacaoObj().getEmail(), "NOVA SOLICITAÇÃO N° " + obj.getNumero() + " DE " + obj.getFuncionarioObj().getNome(), "NOVA SOLICITAÇÃO DE SERVIÇOS VOCÊ É RESPONSÁVEL: \n"
                        + "SOLICITANTE: " + obj.getFuncionarioObj().getNome() + "\n"
                        + "LOCAL SOLICITADO: " + obj.getLocacaoObj().getNome() + " \n"
                        + "RESPONSÁVEL PELO LOCAL: " + obj.getLocacaoObj().getResponsavelObj().getNome() + " \n"
                        + "DATA DE SOLICITAÇÃO: " + scd.formatHoras(new java.sql.Date(obj.getData_solicitacao().getTime())) + " \n"
                        + "DATA LIMITE: " + limite + " \n"
                        + "CLIENTE: " + obj.getClienteObj().getNome() + " \n"
                        + "DEPARTAMENTO: " + obj.getDepartamentoObj().getNome() + " \n"
                        + "RELATO DE SERVIÇO/SOLICITAÇÃO: " + obj.getJustificativa() + " \n"
                        + "NÚMERO DA SOLICITAÇÃO: " + obj.getNumero() + " \n"
                        + "A SOLICITAÇÃO SE ENCONTRA DENTRO DO SISTEMA SAUDE+ PARA ANÁLISE E CONFIRMAÇÃO \n"
                        + "RESPONDA A MESMA PELO SISTEMA E LEMBRE-SE DE VERIFICAR SE O SERVIÇO É VIÁVEL PARA OS TÉCNICOS \n"
                        + "Fim da solicitação - Email enviado por leondev sistemas e automação \n"
                        + "SITE: http://leonsolucoes.epizy.com \n"
                        + "REDES SOCIAIS: @leonjrmartins\n"
                        + "Favor não responder a esse email pois o mesmo foi enviado automaticamente e em caso de duvidas contate o suporte do sistema saude + \n"
                        + "SAUDE+ 2021 - Copyrights and All rights reserved");
                if (!jma.enviarEmail()) {
                    System.out.println("Erro ao tentar enviar a mensagem e retorno falso no main!");
                } else {
                    System.out.println("Concluido o controle interno do servidor para envio do email ");
                }
            } else {
                jma = new JavaMailApp(obj.getLocacaoObj().getEmail(), "NOVA SOLICITAÇÃO N° " + obj.getNumero() + " DE " + obj.getFuncionarioObj().getNome(), "NOVA SOLICITAÇÃO DE ITENS DO ESTOQUE QUE VOCÊ É RESPONSÁVEL: \n"
                        + "SOLICITANTE: " + obj.getFuncionarioObj().getNome() + "\n"
                        + "LOCAL SOLICITADO: " + obj.getLocacaoObj().getNome() + " \n"
                        + "RESPONSÁVEL PELO LOCAL: " + obj.getLocacaoObj().getResponsavelObj().getNome() + " \n"
                        + "DATA DE SOLICITAÇÃO: " + scd.formatHoras(new java.sql.Date(obj.getData_solicitacao().getTime())) + " \n"
                        + "DATA LIMITE: " + limite + " \n"
                        + "CLIENTE: " + obj.getClienteObj().getNome() + " \n"
                        + "DEPARTAMENTO: " + obj.getDepartamentoObj().getNome() + " \n"
                        + "JUSTIFICATIVA DE SOLICITAÇÃO: " + obj.getJustificativa() + " \n"
                        + "NÚMERO DA SOLICITAÇÃO: " + obj.getNumero() + " \n"
                        + "OS ITENS SOLICITADOS ESTÃO DISPONIVEIS PARA VISUALIZAÇÃO NA ABA SOLICITAÇÕES DO SISTEMA SAUDE+ DO ALMOXARIFADO \n"
                        + "RESPONDA A MESMA PELO SISTEMA E LEMBRE-SE DE VERIFICAR O ESTOQUE ANTES DE QUALQUER RETIRADA \n"
                        + "Fim da solicitação - Email enviado por leondev sistemas e automação \n"
                        + "SITE: http://leonsolucoes.epizy.com \n"
                        + "REDES SOCIAIS: @leonjrmartins\n"
                        + "Favor não responder a esse email pois o mesmo foi enviado automaticamente e em caso de duvidas contate o suporte do sistema saude + \n"
                        + "SAUDE+ 2021 - Copyrights and All rights reserved");
                if (!jma.enviarEmail()) {
                    System.out.println("Erro ao tentar enviar a mensagem e retorno falso no main!");
                } else {
                    System.out.println("Concluido o controle interno do servidor para envio do email ");
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro interno no main - envio de solicitação " + e.getMessage());
            return false;
        }
    }

    public static boolean envioSuporte(Suporte obj) {
        try {
            jma = new JavaMailApp("leondev.martins@gmail.com", "NOVO SUPORTE " + obj.getIdFeed() + " - " + obj.getDataRelato() + " ABERTO PARA " + obj.getSoftware(), "NOVO SUPORTE ABERTO: \n"
                    + "NOME: " + obj.getNome() + "\n"
                    + "EMAIL: " + obj.getEmail() + " \n"
                    + "TELEFONE: " + obj.getTelefone() + " \n"
                    + "DATA DE RELATO: " + scd.formatHoras(new java.sql.Date(obj.getDataRelato().getTime())) + " \n"
                    + "SOFTWARE: " + obj.getSoftware() + " \n"
                    + "AJUDA: " + obj.getAjuda() + " \n"
                    + "RELATO: " + obj.getRelato() + " \n"
                    + "Fim do suporte \n"
                    + "RESPONDA O MESMO PELO SISTEMA OU ENTRE EM CONTATO POR MEIO DOS CONTATOS ENVIADOS! \n"
                    + "Favor não responder a esse email pois o mesmo foi enviado automaticamente");
            if (!jma.enviarEmail()) {
                System.out.println("Erro ao tentar enviar a mensagem e retorno falso no main!");
            } else {
                System.out.println("Concluido o controle interno do servidor para envio do email ");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro interno no main - envio de suporte " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("INICIANDO SERVIDOR DE EMAIL DO SAUDE+");
        System.out.println("REGISTRO DE HORA/DATA " + new Date().toString());
        System.out.println("INICIANDO THREAD DE VERIFICACAO");
        while (true) {
            System.out.println("Carregando o servidor local ...");
            if (Conexao.conectionState()) {
                listF = daof.getLista();
                listSupAnt = daosp.getLista();
                listSolAnt = daos.getLista();
                verificationThread();
                gcThread();
                rbThread();
                break;
            } else {
                Conexao.getConexao();
            }
        }
    }

    public static void rbThread() {
        try {
            new Thread() {
                @Override
                public void run() {
                    try {
                        try {
                            sleep(259200000);
                            while (true) {
                                sleep(60000);
                                if (!isRunning) {
                                    final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                                    final File currentJar = new File(Mail_Server.class
                                            .getProtectionDomain().getCodeSource().getLocation().toURI());
                                    if (!currentJar.getName().endsWith(".jar")) {
                                        return;
                                    }
                                    final ArrayList<String> command = new ArrayList<>();
                                    command.add(javaBin);
                                    command.add("-jar");
                                    command.add(currentJar.getPath());
                                    final ProcessBuilder builder = new ProcessBuilder(command);
                                    builder.start();
                                    System.exit(0);
                                }
                            }
                        } catch (IOException ex) {
                            System.out.println("Falha ao reiniciar");
                        } catch (URISyntaxException ux) {
                            System.out.println("Erro de URI " + ux);
                        }
                    } catch (Exception e) {
                        System.out.println("Garbage collector - error inside method");
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Garbage collector - error in thread of gc");
        }
    }

    public static void gcThread() {
        try {
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            sleep(670000);
                            System.gc();
                            System.out.println("Garbage collector - action clear did with sucess");
                        }
                    } catch (Exception e) {
                        System.out.println("Garbage collector - error inside method");
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Garbage collector - error in thread");
        }
    }

    public static void verificationThread() {
        try {
            new Thread() {
                @Override
                public void run() {
                    System.out.println("VERIFICANDO A ENTRADA DE NOVOS EMAILS");
                    System.out.println("SERVIDOR ---> SMS-MAIL-1");
                    totalSup = listSupAnt.size();
                    totalSol = listSolAnt.size();
                    boolean first = false;
                    while (true) {
                        if (listF.isEmpty()) {
                            System.out.println("SISTEMA DESCONECTADO - FALHA NO RECEBIMENTO DE DADOS - TENTANDO CONEXAO");
                            Conexao.resetConexao();
                            if (Conexao.getConexao() != null) {
                                if (listF.isEmpty()) {
                                    System.out.println("AINDA DESCONECTADO");
                                } else {
                                    System.out.println("CONECTADO COM SUCESSO RECEBENDO DADOS --> " + new Date().toString());
                                    listSolAnt = daos.getLista();
                                    listSupAnt = daosp.getLista();
                                }
                            }

                        }
                        first = false;
                        if (!checker && !first) {
                            System.out.println("SERVIDOR OFFLINE DEVIDO A ERROS INTERNOS NA THREAD DE VERIFICAÇÃO");
                            if (Conexao.conectionState()) {
                                System.out.println("FUNCIONANDO CORRETAMENTE E CONECTADO COM A BASE LOCAL!");
                            } else {
                                System.out.println("BASE LOCAL DESCONECTADA");
                            }
                            Conexao.resetConexao();
                            if (Conexao.getConexao() != null) {
                                System.out.println("SERVIDOR LOCALIZADO");
                            } else {
                                System.out.println("SERVIDOR NÃO LOCALIZADO");
                            }
                            first = true;
                            System.out.println("REQUER ATENÇÃO TÉCNICA");
                        }

                        if (first) {
                            Conexao.resetConexao();
                            if (Conexao.getConexao() != null) {
                                System.out.println("TENTANDO CONECTAR");
                                System.out.println("RECONECTADO");
                            } else {
                                System.out.println("OFFLINE");
                            }
                        }

                        try {
                            sleep(180000);
                        } catch (InterruptedException ex) {
                            scd.errors(null, "Erro interno ao iniciar tempo - Thread " + ex.getMessage());
                        }
                        if (!listSup.isEmpty()) {
                            listSol.clear();
                        }
                        if (!listSol.isEmpty()) {
                            listSup.clear();
                        }
                        listSol = daos.getLista();
                        listSup = daosp.getLista();
                        listF = daof.getLista();
                        System.out.println("VERIFICANDO NOVAS ENTRADAS NO SISTEMA");
                        System.out.println("CARREGANDO ...");
                        if (listF.isEmpty()) {
                            System.out.println("FALHA NA TRANSMISSÃO DE DADOS TENTANDO CONECTAR OU ABORTANDO SE HOUVER FALHAS");
                            Conexao.resetConexao();
                            Conexao.getConexao();
                            checker = false;
                        } else {
                            checker = true;
                        }
                        if (totalSol < listSol.size()) {
                            int count = 0;
                            isRunning = true;
                            for (Solicitacao sol : listSol) {
                                for (Solicitacao solAnt : listSolAnt) {
                                    if (sol.getIdSolicitacoes() == solAnt.getIdSolicitacoes()) {
                                        count++;
                                    }
                                }
                                if (count == 0) {
                                    auxSol.add(sol);
                                }
                                count = 0;
                            }
                            for (Solicitacao sol : auxSol) {
                                System.out.println("SOLICITAÇÃO IDENTIFICADA AS " + new Date());
                                System.out.println("Iniciando envio de solicitação via email");
                                if (envioSolicitacao(sol)) {
                                    System.out.println("ENVIADO COM SUCESSO VIA EMAIL");
                                    System.out.println("REINICIANDO SERVIDOR");
                                } else {
                                    System.out.println("EMAIL NÃO ENVIADO CHEQUE O LOG");
                                    System.out.println("REINICIANDO SERVIDOR");
                                }
                                System.out.println("SERVIDOR REINICIADO \n");
                            }
                            listSolAnt = daos.getLista();
                            totalSol = listSolAnt.size();
                            auxSol.clear();
                        }

                        if (totalSup < listSup.size()) {
                            int count = 0;
                            for (Suporte sup : listSup) {
                                for (Suporte supAnt : listSupAnt) {
                                    if (sup.getIdFeed() == supAnt.getIdFeed()) {
                                        count++;
                                    }
                                }
                                if (count == 0) {
                                    auxSp.add(sup);
                                }
                                count = 0;
                            }
                            for (Suporte sup : auxSp) {
                                System.out.println("SUPORTE IDENTIFICADO AS " + new Date());
                                System.out.println("Iniciando envio de suporte via email");
                                if (envioSuporte(sup)) {
                                    System.out.println("ENVIADO COM SUCESSO VIA EMAIL");
                                    System.out.println("REINICIANDO SERVIDOR");
                                } else {
                                    System.out.println("EMAIL NÃO ENVIADO CHEQUE O LOG");
                                    System.out.println("REINICIANDO SERVIDOR");
                                }
                                System.out.println("SERVIDOR REINICIADO \n");
                            }
                            listSupAnt = daosp.getLista();
                            totalSup = listSupAnt.size();
                            auxSp.clear();
                        }
                        isRunning = false;
                    }
                }
            }.start();
        } catch (Exception e) {
            System.out.println("Erro interno na thread principal de verificação");
            e.printStackTrace();
            checker = false;
        }
    }
}
