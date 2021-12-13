/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.SecureDate;
import classes.Suporte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOSuporte {

    SecureDate scd = new SecureDate();

    /**
     * Método que busca no banco de dados a lista de atendimentos
     *
     * @return A lista de atendimentos
     */
    public ArrayList<Suporte> getLista() {
        String sql = "select * from Suporte;";
        ArrayList<Suporte> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            Suporte obj;
            while (rs.next()) {
                obj = new Suporte();
                obj.setIdFeed(rs.getInt("idFeed"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setAjuda(rs.getString("ajuda"));
                obj.setSoftware(rs.getString("software"));
                obj.setRelato(rs.getString("relato"));
                obj.setDataRelato(rs.getDate("dataRelato"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
        } catch(Exception e){
            System.out.println("Erro de ENVIO DE PACOTES");
        }
        return lista;
    }

    /**
     * Método que inclui um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser adicionado
     * @return True se der certo e false se não der certo
     */
    public boolean incluir(Suporte obj) {
        String sql = "insert into Suporte (nome,email,telefone,ajuda,software,relato,dataRelato) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
            cod.setString(2, obj.getEmail());
            cod.setString(3, obj.getTelefone());
            cod.setString(4, obj.getAjuda());
            cod.setString(5, obj.getSoftware());
            cod.setString(6, obj.getRelato());
            cod.setDate(7, obj.getDataRelato());
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
    public boolean alterar(Suporte obj) {
        String sql = "update Suporte set nome=?,email=?,telefone=?,ajuda=?,software=?,relato=?,dataRelato=? where idFeed = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
            cod.setString(2, obj.getEmail());
            cod.setString(3, obj.getTelefone());
            cod.setString(4, obj.getAjuda());
            cod.setString(5, obj.getSoftware());
            cod.setString(6, obj.getRelato());
            cod.setDate(7, obj.getDataRelato());
            cod.setInt(8, obj.getIdFeed());
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
     * Método que remove um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser removido
     * @return True se der certo e false se não der certo
     */
    public boolean remover(Suporte obj) {
        String sql = "delete from Suporte where idFeed = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdFeed());
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
    public Suporte localizar(int id) {
        String sql = "select * from Suporte where idFeed = ?";
        Suporte obj;
        try {
            obj = new Suporte();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setIdFeed(rs.getInt("idFeed"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setAjuda(rs.getString("ajuda"));
                obj.setSoftware(rs.getString("software"));
                obj.setRelato(rs.getString("relato"));
                obj.setDataRelato(rs.getDate("dataRelato"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
}
