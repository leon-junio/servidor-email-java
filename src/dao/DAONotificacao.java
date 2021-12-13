/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Notificacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAONotificacao {

    /**
     * Método que busca no banco de dados a lista de atendimentos
     *
     * @return A lista de atendimentos
     */
    public ArrayList<Notificacao> getLista() {
        String sql = "select * from Notificacao;";
        ArrayList<Notificacao> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Notificacao obj = new Notificacao();
                obj.setIdNotificacao(rs.getInt("idNotificacao"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setLeitura(rs.getInt("leitura"));
                obj.setMensagem(rs.getString("mensagem"));
                obj.setDataNot(rs.getDate("dataNot"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Método que remove um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser removido
     * @return True se der certo e false se não der certo
     */
    public boolean remover(Notificacao obj) {
        String sql = "delete from Notificacao where idNotificacao = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdNotificacao());
            if (cod.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return false;
        }
    }

    /**
     * Método que localiza um objeto dentro do banco de dados
     *
     * @param id A primary key do objeto
     * @return O objeto pronto para uso
     */
    public Notificacao localizar(int id) {
        String sql = "select * from Notificacao where idNotificacao = ?";
        Notificacao obj = new Notificacao();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setIdNotificacao(rs.getInt("idNotificacao"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setLeitura(rs.getInt("leitura"));
                obj.setMensagem(rs.getString("mensagem"));
                obj.setDataNot(rs.getDate("dataNot"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
}
