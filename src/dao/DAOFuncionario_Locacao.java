/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Funcionario_Locacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOFuncionario_Locacao {

    /**
     * Método que busca no banco de dados a lista de receitas de produtos
     *
     * @return A lista de receitas de produtos
     */
    public ArrayList<Funcionario_Locacao> getLista() {
        String sql = "select * from Funcionario_Locacao";
        ArrayList<Funcionario_Locacao> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Funcionario_Locacao obj = new Funcionario_Locacao();
                obj.setLocacao(rs.getInt("Locacao"));
                obj.setFuncionario(rs.getInt("Funcionario"));
                obj.setIdFuncionario_locacao(rs.getInt("Idfuncloc"));
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
    public boolean incluir(Funcionario_Locacao obj) {
        String sql = "insert into Funcionario_Locacao (Locacao,Funcionario) values (?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getLocacao());
            cod.setInt(2, obj.getFuncionario());
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
    public boolean alterar(Funcionario_Locacao obj) {
        String sql = "update Funcionario_Locacao set Locacao = ?,Funcionario=? where Idfuncloc = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getLocacao());
            cod.setInt(2, obj.getFuncionario());
            cod.setInt(3, obj.getIdFuncionario_locacao());
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
    public boolean remover(Funcionario_Locacao obj) {
        String sql = "delete from Funcionario_Locacao where Idfuncloc = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdFuncionario_locacao());
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
    public Funcionario_Locacao localizar(int id) {
        String sql = "select * from Funcionario_Locacao where Idfuncloc = ?";
        Funcionario_Locacao obj = new Funcionario_Locacao();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setLocacao(rs.getInt("Locacao_IdLocacao"));
                obj.setFuncionario(rs.getInt("Funcionario_idFuncionario"));
                obj.setIdFuncionario_locacao(rs.getInt("idLocacao_Funcionario"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }
}
