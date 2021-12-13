/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Ordem_Servico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOOs {

    /**
     * Método que busca no banco de dados a lista de receitas
     *
     * @return A lista de receitas
     */
    public ArrayList<Ordem_Servico> getLista() {
        String sql = "select * from OS";
        ArrayList<Ordem_Servico> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Ordem_Servico obj = new Ordem_Servico();
                obj.setIdOS(rs.getInt("idOS"));
                obj.setServico(rs.getInt("idServico"));
                obj.setTipoOrdem(rs.getInt("idTipoOrdem"));
                obj.setCliente(rs.getInt("idCliente"));
                obj.setTecnico(rs.getInt("tecnico"));
                obj.setRelato(rs.getString("relato"));
                obj.setValor(rs.getFloat("valor"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setData_inicio(rs.getDate("data_inicio"));
                obj.setData_finalizacao(rs.getDate("data_finalizacao"));
                obj.setNumero(rs.getString("numero"));
                obj.setPrioridade(rs.getString("prioridade"));
                obj.setSolicitante(rs.getInt("solicitante"));
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
    public boolean incluir(Ordem_Servico obj) throws SQLException {
        String sql = "insert into OS (idServico,idTipoOrdem,tecnico,descricao,relato,valor,data_inicio,data_finalizacao,numero,idCliente,prioridade,solicitante) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement cod = Conexao.getPreparedStatement(sql);
        cod.setInt(1, obj.getServico());
        cod.setInt(2, obj.getTipoOrdem());
        cod.setInt(3, obj.getTecnico());
        cod.setString(4, obj.getDescricao());
        cod.setString(5, obj.getRelato());
        cod.setFloat(6, obj.getValor());
        cod.setDate(7, obj.getData_inicio());
        cod.setDate(8, obj.getData_finalizacao());
        cod.setString(9, obj.getNumero());
        cod.setInt(10, obj.getCliente());
        cod.setString(11, obj.getPrioridade());
        cod.setInt(12, obj.getSolicitante());
        if (cod.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que atualiza um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser atualizado
     * @return True se der certo e false se não der certo
     */
    public boolean alterar(Ordem_Servico obj) {
        try {
            String sql = "update OS set idServico=?,idTipoOrdem=?,tecnico=?,descricao=?,relato=?,valor=?,data_inicio=?,data_finalizacao=?,numero=?,idCliente=?,prioridade=?,solicitante=? where idOs = ?";
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getServico());
            cod.setInt(2, obj.getTipoOrdem());
            cod.setInt(3, obj.getTecnico());
            cod.setString(4, obj.getDescricao());
            cod.setString(5, obj.getRelato());
            cod.setFloat(6, obj.getValor());
            cod.setDate(7, obj.getData_inicio());
            cod.setDate(8, obj.getData_finalizacao());
            cod.setString(9, obj.getNumero());
            cod.setInt(10, obj.getCliente());
            cod.setString(11, obj.getPrioridade());
            cod.setInt(12, obj.getSolicitante());
            cod.setInt(13, obj.getIdOS());
            if (cod.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Método que remove um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser removido
     * @return True se der certo e false se não der certo
     */
    public boolean remover(Ordem_Servico obj) {
        String sql = "delete from OS where idOs = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdOS());
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
    public Ordem_Servico localizar(int id) {
        String sql = "select * from OS where idOs = ?";
        Ordem_Servico obj = new Ordem_Servico();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setIdOS(rs.getInt("idOS"));
                obj.setServico(rs.getInt("idServico"));
                obj.setTipoOrdem(rs.getInt("idTipoOrdem"));
                obj.setCliente(rs.getInt("idCliente"));
                obj.setTecnico(rs.getInt("tecnico"));
                obj.setRelato(rs.getString("relato"));
                obj.setValor(rs.getFloat("valor"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setData_inicio(rs.getDate("data_inicio"));
                obj.setData_finalizacao(rs.getDate("data_finalizacao"));
                obj.setNumero(rs.getString("numero"));
                obj.setPrioridade(rs.getString("prioridade"));
                obj.setSolicitante(rs.getInt("solicitante"));
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
    public ArrayList<Ordem_Servico> localizarNome(String sql) {

        try {
            ArrayList<Ordem_Servico> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Ordem_Servico obj = new Ordem_Servico();
                obj.setIdOS(rs.getInt("idOS"));
                obj.setServico(rs.getInt("idServico"));
                obj.setTipoOrdem(rs.getInt("idTipoOrdem"));
                obj.setCliente(rs.getInt("idCliente"));
                obj.setTecnico(rs.getInt("tecnico"));
                obj.setRelato(rs.getString("relato"));
                obj.setValor(rs.getFloat("valor"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setData_inicio(rs.getDate("data_inicio"));
                obj.setData_finalizacao(rs.getDate("data_finalizacao"));
                obj.setNumero(rs.getString("numero"));
                obj.setPrioridade(rs.getString("prioridade"));
                obj.setSolicitante(rs.getInt("solicitante"));
                result.add(obj);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
