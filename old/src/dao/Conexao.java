/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.SecureDate;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Leon Jr
 */
public class Conexao {

    private static final SecureDate sd = new SecureDate();
    private static final String user = "root";
    private static final String senha = "";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static Connection conexao = null;

    /**
     * Construtor da classe
     */
    public Conexao() {

    }

    /**
     * Método que verifica a conexão
     *
     * @return O estado da conexao
     */
    public static boolean conectionState() {
        if (conexao != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para conectar ao banco de dados
     *
     * @return A conexão realizada
     */
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName(driver);
                conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:2212/testBD", user, senha);
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro de conexão por falta de classe " + ex.getMessage());
            } catch (SQLException ex) {
                System.out.println("ERRO AO CONECTAR COM O SERVIDOR DO MYSQL " + ex.getMessage());
            } catch (Exception e) {
                System.out.println("Erro interno na conexao " + e.getMessage());
            }
        }
        return conexao;
    }

    public static void resetConexao() {
        conexao = null;
    }

    /**
     * Método para mandar comandos sql para o banco
     *
     * @param sql Comando em sql que vai ser executado
     * @return A execução do comando
     */
    public static PreparedStatement getPreparedStatement(String sql) {
        if (conexao == null) {
            getConexao();
        }
        try {
            return conexao.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar Preparar o Statment -> " + ex.getMessage());
            resetConexao();
        }
        return null;
    }

    public static String infoServer() {
        String sql = "call info();";
        PreparedStatement cod = Conexao.getPreparedStatement(sql);
        ResultSet rs;
        try {
            rs = cod.executeQuery();
            String info = "", data = "";
            while (rs.next()) {
                info = rs.getString("info");
                data = rs.getDate("data_atual").toString();
            }
            return info + " - \n" + data;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static String infoVer() {
        String sql = "call info();";
        PreparedStatement cod = Conexao.getPreparedStatement(sql);
        ResultSet rs;
        try {
            rs = cod.executeQuery();
            String ver = "";
            while (rs.next()) {
                ver = rs.getString("versao");
            }
            return ver;
        } catch (SQLException ex) {
            return null;
        }
    }

}
