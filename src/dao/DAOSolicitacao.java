/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.SecureDate;
import classes.Solicitacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOSolicitacao {

    private SecureDate scd = new SecureDate();
    Solicitacao obj;
    ArrayList<Solicitacao> lista = new ArrayList<>();

    public void limpar() {
        obj = null;
        if (lista != null) {
            lista.clear();
            lista = null;
        }
    }

    /**
     * Método que busca no banco de dados a lista de Solicitacaos
     *
     * @return A lista de Solicitacaos
     */
    public ArrayList<Solicitacao> getLista() {
        String sql = "select * from solicitacoes";
        lista = new ArrayList<>();
         lista.clear();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj = new Solicitacao();
                obj.setIdSolicitacoes(rs.getInt("idSolicitacoes"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setCliente(rs.getInt("cliente"));
                obj.setLocacao(rs.getInt("locacao"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setNumero(rs.getString("numero"));
                obj.setData_limite(rs.getDate("dataLimite"));
                obj.setData_solicitacao(rs.getDate("dataSolicitacao"));
                obj.setSituacao(rs.getInt("situacao"));
                obj.setJustificativa(rs.getString("justificativa"));
                lista.add(obj);
            }
            rs = null;
            cod = null;
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
    public boolean incluir(Solicitacao obj) {
        String sql = "insert into Solicitacoes (funcionario,cliente,locacao,justificativa,dataSolicitacao,situacao,departamento,numero,dataLimite) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getFuncionario());
            cod.setInt(2, obj.getCliente());
            cod.setInt(3, obj.getLocacao());
            cod.setString(4, obj.getJustificativa());
            cod.setDate(5, obj.getData_solicitacao());
            cod.setInt(6, obj.getSituacao());
            cod.setInt(7, obj.getDepartamento());
            cod.setString(8, obj.getNumero());
            cod.setDate(9, obj.getData_limite());
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
    public boolean alterar(Solicitacao obj) {
        String sql = "update solicitacoes set funcionario=?,cliente=?,locacao=?,justificativa=?,dataSolicitacao=?,situacao=?,departamento=?,numero=?,dataLimite=? where idSolicitacoes = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getFuncionario());
            cod.setInt(2, obj.getCliente());
            cod.setInt(3, obj.getLocacao());
            cod.setString(4, obj.getJustificativa());
            cod.setDate(5, obj.getData_solicitacao());
            cod.setInt(6, obj.getSituacao());
            cod.setInt(7, obj.getDepartamento());
            cod.setString(8, obj.getNumero());
            cod.setDate(9, obj.getData_limite());
            cod.setInt(10, obj.getIdSolicitacoes());
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
    public boolean remover(Solicitacao obj) {
        String sql = "delete from Solicitacoes where idSolicitacoes = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdSolicitacoes());
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
    public Solicitacao localizar(int id) {
        String sql = "select * from Solicitacao where idSolicitacao = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj = new Solicitacao();
                obj.setIdSolicitacoes(rs.getInt("idSolicitacoes"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setCliente(rs.getInt("cliente"));
                obj.setLocacao(rs.getInt("locacao"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setNumero(rs.getString("numero"));
                obj.setData_limite(rs.getDate("dataLimite"));
                obj.setData_solicitacao(rs.getDate("dataSolicitacao"));
                obj.setSituacao(rs.getInt("situacao"));
                obj.setJustificativa(rs.getString("justificativa"));
                cod = null;
                rs = null;
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
    public ArrayList<Solicitacao> localizarNome(String sql) {

        try {
            lista.clear();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj = new Solicitacao();
                obj.setIdSolicitacoes(rs.getInt("idSolicitacoes"));
                obj.setFuncionario(rs.getInt("funcionario"));
                obj.setCliente(rs.getInt("cliente"));
                obj.setLocacao(rs.getInt("locacao"));
                obj.setDepartamento(rs.getInt("departamento"));
                obj.setNumero(rs.getString("numero"));
                obj.setData_limite(rs.getDate("dataLimite"));
                obj.setData_solicitacao(rs.getDate("dataSolicitacao"));
                obj.setSituacao(rs.getInt("situacao"));
                obj.setJustificativa(rs.getString("justificativa"));
                lista.add(obj);
            }
            cod = null;
            rs = null;
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
