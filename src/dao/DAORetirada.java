/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Retirada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAORetirada {

    /**
     * Método que busca no banco de dados a lista de Retiradas
     *
     * @return A lista de Retiradas
     */
    public ArrayList<Retirada> getLista() {
        String sql = "select * from Retirada order by idRetirada DESC";
        ArrayList<Retirada> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Retirada obj = new Retirada();
                obj.setIdRetirada(rs.getInt("idRetirada"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setCliente(rs.getInt("cliente"));
                obj.setSetor(rs.getInt("setor"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setNumero(rs.getString("numero_retirada"));
                obj.setData_atual(rs.getDate("data_atual"));
                obj.setData_dev(rs.getDate("data_dev"));
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
    public boolean incluir(Retirada obj) {
        String sql = "insert into Retirada (funcionario,cliente,data_atual,setor,departamento,numero_retirada,data_dev) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            if (obj.getSetor() == 0) {
                cod.setInt(1, obj.getFuncionario());
                cod.setInt(2, obj.getCliente());
                cod.setDate(3, obj.getData_atual());
                cod.setString(4, null);
                cod.setString(5, null);
                cod.setString(6, obj.getNumero());
                cod.setDate(7, obj.getData_dev());
            } else {
                cod.setInt(1, obj.getFuncionario());
                cod.setInt(2, obj.getCliente());
                cod.setDate(3, obj.getData_atual());
                cod.setInt(4, obj.getSetor());
                cod.setInt(5, obj.getDepartamento());
                cod.setString(6, obj.getNumero());
                cod.setDate(7, obj.getData_dev());
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
    public boolean alterar(Retirada obj) {
        String sql = "update Retirada set funcionario=?,cliente=?,data_atual=?,setor=?,departamento=?,numero_retirada=?,data_dev=? where idRetirada = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getFuncionario());
            cod.setInt(2, obj.getCliente());
            cod.setDate(3, obj.getData_atual());
            cod.setInt(4, obj.getSetor());
            cod.setInt(5, obj.getDepartamento());
            cod.setString(6, obj.getNumero());
            cod.setDate(7, obj.getData_dev());
            cod.setInt(8, obj.getIdRetirada());
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
    public boolean remover(Retirada obj) {
        String sql = "delete from Retirada where idRetirada = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdRetirada());
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
    public Retirada localizar(int id) {
        String sql = "select * from Retirada where idRetirada = ?";
        Retirada obj = new Retirada();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setIdRetirada(rs.getInt("idRetirada"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setCliente(rs.getInt("cliente"));
                obj.setSetor(rs.getInt("setor"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setNumero(rs.getString("numero_retirada"));
                obj.setData_atual(rs.getDate("data_atual"));
                obj.setData_dev(rs.getDate("data_dev"));
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
    public ArrayList<Retirada> localizarNome(String sql) {

        try {
            ArrayList<Retirada> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Retirada obj = new Retirada();
                obj.setIdRetirada(rs.getInt("idRetirada"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setCliente(rs.getInt("cliente"));
                obj.setSetor(rs.getInt("setor"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setNumero(rs.getString("numero_retirada"));
                obj.setData_atual(rs.getDate("data_atual"));
                obj.setData_dev(rs.getDate("data_dev"));
                result.add(obj);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
