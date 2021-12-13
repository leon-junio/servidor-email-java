/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Tipo_Ordem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOTipo_Ordem {

    /**
     * Método que busca no banco de dados a lista de nota_fiscal
     *
     * @return A lista de nota_fiscal
     */
    public ArrayList<Tipo_Ordem> getLista() {
        String sql = "select * from Tipo_Ordem";
        ArrayList<Tipo_Ordem> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Tipo_Ordem obj = new Tipo_Ordem();
                obj.setNome(rs.getString("Nome"));
                obj.setIdTipo(rs.getInt("idTipoOrdem"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Método que inclui um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser adicionado
     * @return True se der certo e false se não der certo
     */
    public boolean incluir(Tipo_Ordem obj) {
        String sql = "insert into tipo_ordem (Nome) values (?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
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
     * Método que atualiza um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser atualizado
     * @return True se der certo e false se não der certo
     */
    public boolean alterar(Tipo_Ordem obj) {
        String sql = "update Tipo_Ordem set Nome=? where idTipoOrdem = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
            cod.setInt(2, obj.getIdTipo());
            if (cod.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "atualizado com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "não atualizado com sucesso");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return false;
        }
    }

    /**
     * Método que remove um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser removido
     * @return True se der certo e false se não der certo
     */
    public boolean remover(Tipo_Ordem obj) {
        String sql = "delete from Tipo_Ordem where idTipoOrdem = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdTipo());
            if (cod.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "excluido com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "não excluido com sucesso");
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
    public Tipo_Ordem localizar(int id) {
        String sql = "select * from Tipo_Ordem where idTipoOrdem = ?";
        Tipo_Ordem obj = new Tipo_Ordem();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setNome(rs.getString("Nome"));
                obj.setIdTipo(rs.getInt("idTipoOrdem"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
    
     /**
     * Método que localiza uma lista de objetos no banco de dados por base no
     * nome
     *
     * @param sql Comando em sql que vai ser executado para localizar a lista
     * @return A lista pronta com todos os nomes localizados
     */
    public ArrayList<Tipo_Ordem> localizarNome(String sql) {
        try {
            ArrayList<Tipo_Ordem> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Tipo_Ordem obj = new Tipo_Ordem();
                obj.setNome(rs.getString("nome"));
                obj.setIdTipo(rs.getInt("idTipoOrdem"));
                result.add(obj);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
