/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Tecnico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOTecnico {

    /**
     * Método que busca no banco de dados a lista de produção
     *
     * @return A lista de produção
     */
    public ArrayList<Tecnico> getLista() {
        String sql = "select * from Tecnico";
        ArrayList<Tecnico> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Tecnico obj = new Tecnico();
                obj.setNome(rs.getString("Nome"));
                obj.setIdDepartamento(rs.getInt("IdDepartamento"));
                obj.setFunc(rs.getInt("idFunc"));
                obj.setIdTecnico(rs.getInt("idTecnico"));
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
    public boolean incluir(Tecnico obj) {
        String sql = "insert into Tecnico (Nome,IdDepartamento,idFunc) values (?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            if (obj.getFunc() != 0) {
                cod.setString(1, obj.getNome());
                cod.setInt(2, obj.getIdDepartamento());
                cod.setInt(3, obj.getFunc());
            } else {
                cod.setString(1, obj.getNome());
                cod.setInt(2, obj.getIdDepartamento());
                cod.setString(3, null);
            }

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
    public boolean alterar(Tecnico obj) {
        String sql = "update Tecnico set Nome = ?,  IdDepartamento = ?, idFunc = ? where idTecnico = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            if (obj.getFunc() > 0) {
                cod.setString(1, obj.getNome());
                cod.setInt(2, obj.getIdDepartamento());
                cod.setInt(3, obj.getFunc());
                cod.setInt(4, obj.getIdTecnico());
            } else {
                cod.setString(1, obj.getNome());
                cod.setInt(2, obj.getIdDepartamento());
                cod.setString(3, null);
                cod.setInt(4, obj.getIdTecnico());
            }

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
    public boolean remover(Tecnico obj) {
        String sql = "delete from Tecnico where idTecnico = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdTecnico());
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
    public Tecnico localizar(int id) {
        String sql = "select * from Tecnico where idTecnico = ?";
        Tecnico obj = new Tecnico();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setNome(rs.getString("Nome"));
                obj.setIdDepartamento(rs.getInt("IdDepartamento"));
                obj.setFunc(rs.getInt("idFunc"));
                obj.setIdTecnico(rs.getInt("idTecnico"));
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
    public ArrayList<Tecnico> localizarNome(String sql) {
        try {
            ArrayList<Tecnico> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Tecnico obj = new Tecnico();
                obj.setNome(rs.getString("Nome"));
                obj.setIdDepartamento(rs.getInt("IdDepartamento"));
                obj.setFunc(rs.getInt("idFunc"));
                obj.setIdTecnico(rs.getInt("idTecnico"));
                result.add(obj);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
