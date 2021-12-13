/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Categoria_Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOCategoria_Produto {

    /**
     * Método que busca no banco de dados a lista de atendimentos
     *
     * @return A lista de atendimentos
     */
    public ArrayList<Categoria_Produto> getLista() {
        String sql = "select * from Categoria_produto";
        ArrayList<Categoria_Produto> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Categoria_Produto obj = new Categoria_Produto();
                obj.setIdCategoria(rs.getInt("idCategoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setTipo(rs.getString("Tipo"));
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
    public boolean incluir(Categoria_Produto obj) {
        String sql = "insert into Categoria_produto (Tipo,Descricao) values (?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getTipo());
            cod.setString(2, obj.getDescricao());
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
    public boolean alterar(Categoria_Produto obj) {
        String sql = "update Categoria_Produto set tipo = ?,descricao=? where idCategoria = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getTipo());
            cod.setString(2, obj.getDescricao());
            cod.setInt(3, obj.getIdCategoria());
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
    public boolean remover(Categoria_Produto obj) {
        String sql = "delete from Categoria_Produto where idCategoria = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdCategoria());
            if (cod.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "excluido com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "não excluido pois tem vinculos ativos com outros produtos.", null, JOptionPane.ERROR_MESSAGE);
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
    public Categoria_Produto localizar(int id) {
        String sql = "select * from Categoria_Produto where idCategoria = ?";
        Categoria_Produto obj = new Categoria_Produto();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setIdCategoria(rs.getInt("idCategoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setTipo(rs.getString("Tipo"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
}
