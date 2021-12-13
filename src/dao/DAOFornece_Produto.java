/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Fornece_Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOFornece_Produto {

    /**
     * Método que busca no banco de dados a lista de ingredientes Fornece_Produtodos em uma
     * receita
     *
     * @return A lista de ingredientes Fornece_Produtodos em uma receita
     */
    public ArrayList<Fornece_Produto> getLista() {
        String sql = "select * from Fornece_Produto";
        ArrayList<Fornece_Produto> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Fornece_Produto obj = new Fornece_Produto();
                obj.setFornecedor(rs.getInt("Idfornecedor"));
                obj.setProduto(rs.getInt("idProduto"));
                obj.setIdFornece(rs.getInt("idFp"));
                obj.setOfs(rs.getString("ofs"));
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
    public boolean incluir(Fornece_Produto obj) {
        String sql = "insert into Fornece_Produto (IdProduto,idFornecedor,ofs) values (?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getProduto());
            cod.setInt(2, obj.getFornecedor());
            cod.setString(3, obj.getOfs());
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
    public boolean alterar(Fornece_Produto obj) {
        String sql = "update Fornece_Produto set IdProduto = ?,idFornecedor=?,ofs=? where idFp = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getProduto());
            cod.setInt(2, obj.getFornecedor());
            cod.setString(3, obj.getOfs());
            cod.setInt(4, obj.getIdFornece());
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
    public boolean remover(Fornece_Produto obj) {
        String sql = "delete from Fornece_Produto where idFp = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdFornece());
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
    public Fornece_Produto localizar(int id) {
        String sql = "select * from Fornece_Produto where idFp = ?";
        Fornece_Produto obj = new Fornece_Produto();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setFornecedor(rs.getInt("IdFornecedor"));
                obj.setProduto(rs.getInt("Produto"));
                obj.setIdFornece(rs.getInt("idFp"));
                obj.setOfs(rs.getString("ofs"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
}
