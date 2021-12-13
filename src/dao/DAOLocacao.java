/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Locacoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOLocacao {

    /**
     * Método que busca no banco de dados a lista de Locacaos
     *
     * @return A lista de Locacaos
     */
    public ArrayList<Locacoes> getLista() {
        String sql = "select * from Locacao";
        ArrayList<Locacoes> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Locacoes obj = new Locacoes();
                obj.setIdLocacao(rs.getInt("idLocacao"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setEmail(rs.getString("email"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setResponsavel(rs.getInt("responsavel"));
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
    public boolean incluir(Locacoes obj) {
        String sql = "insert into Locacao (nome,endereco,telefone,celular,email,departamento,descricao,responsavel) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
            cod.setString(2, obj.getEndereco());
            cod.setString(3, obj.getTelefone());
            cod.setString(4, obj.getCelular());
            cod.setString(5, obj.getEmail());
            cod.setInt(6, obj.getDepartamento());
            cod.setString(7, obj.getDescricao());
            cod.setInt(8, obj.getResponsavel());
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
    public boolean alterar(Locacoes obj) {
        String sql = "update Locacao set nome=?,endereco=?,telefone=?,celular=?,email=?,departamento=?,descricao=?,responsavel=? where idLocacao  = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
            cod.setString(2, obj.getEndereco());
            cod.setString(3, obj.getTelefone());
            cod.setString(4, obj.getCelular());
            cod.setString(5, obj.getEmail());
            cod.setInt(6, obj.getDepartamento());
            cod.setString(7, obj.getDescricao());
            cod.setInt(8, obj.getResponsavel());
            cod.setInt(9,obj.getIdLocacao());
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
    public boolean remover(Locacoes obj) {
        String sql = "delete from Locacao where idLocacao = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdLocacao());
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
    public Locacoes localizar(int id) {
        String sql = "select * from Locacao where idLocacao = ?";
        Locacoes obj = new Locacoes();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
               obj.setIdLocacao(rs.getInt("idLocacao"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setEmail(rs.getString("email"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setResponsavel(rs.getInt("responsavel"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
}
