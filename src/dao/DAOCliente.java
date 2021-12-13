/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOCliente {

    /**
     * Método que busca no banco de dados a lista de clientes
     *
     * @return A lista de clientes
     */
    public List<Cliente> getLista() {
        String sql = "select * from cliente";
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCPF(rs.getString("cpf_cnpj"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cliente.setData_nasc(rs.getDate("data_nasc"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRg(rs.getString("rg_ie"));
                cliente.setMatricula(rs.getInt("matricula"));
                lista.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Método que inclui um objeto na banco de dados
     *
     * @param cliente O objeto desajado que vai ser adicionado
     * @return True se der certo e false se não der certo
     */
    public boolean incluir(Cliente cliente) {
        String sql = "insert into cliente (nome,cpf_cnpj,rg_ie,endereco,bairro,cidade,cep,numero,telefone,celular,email,sexo,data_nasc,matricula) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, cliente.getNome());
            cod.setString(2, cliente.getCPF());
            cod.setString(3, cliente.getRg());
            cod.setString(4, cliente.getEndereco());
            cod.setString(5, cliente.getBairro());
            cod.setString(6, cliente.getCidade());
            cod.setString(7, cliente.getCep());
            cod.setInt(8, cliente.getNumero());
            cod.setString(9, cliente.getTelefone());
            cod.setString(10, cliente.getCelular());
            cod.setString(11, cliente.getEmail());
            cod.setString(12, cliente.getSexo());
            cod.setDate(13, cliente.getData_nasc());
            cod.setInt(14, cliente.getMatricula());
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
     * @param cliente O objeto desajado que vai ser atualizado
     * @return True se der certo e false se não der certo
     */
    public boolean alterar(Cliente cliente) {
        String sql = "update cliente set nome =?, cpf_cnpj=?,rg_ie=?,endereco=?,bairro=?,cidade=?,cep=?,numero=?,telefone=?,celular=?,email=?,sexo=?,data_nasc=?,matricula=? where idCliente = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, cliente.getNome());
            cod.setString(2, cliente.getCPF());
            cod.setString(3, cliente.getRg());
            cod.setString(4, cliente.getEndereco());
            cod.setString(5, cliente.getBairro());
            cod.setString(6, cliente.getCidade());
            cod.setString(7, cliente.getCep());
            cod.setInt(8, cliente.getNumero());
            cod.setString(9, cliente.getTelefone());
            cod.setString(10, cliente.getCelular());
            cod.setString(11, cliente.getEmail());
            cod.setString(12, cliente.getSexo());
            cod.setDate(13, cliente.getData_nasc());
            cod.setInt(14, cliente.getMatricula());
            cod.setInt(15,cliente.getIdCliente());
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
     * @param cliente O objeto desajado que vai ser removido
     * @return True se der certo e false se não der certo
     */
    public boolean remover(Cliente cliente) {
        String sql = "delete from cliente where idCliente = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, cliente.getIdCliente());
            if (cod.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não excluido por possuír vinculos ativos.");
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
    public Cliente localizar(int id) {
        String sql = "select * from cliente where idCliente = ?";
        Cliente cliente = new Cliente();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCPF(rs.getString("cpf_cnpj"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cliente.setData_nasc(rs.getDate("data_nasc"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRg(rs.getString("rg_ie"));
                cliente.setMatricula(rs.getInt("matricula"));
                return cliente;
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
    public ArrayList<Cliente> localizarNome(String sql) {

        try {
            ArrayList<Cliente> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCPF(rs.getString("cpf_cnpj"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cliente.setData_nasc(rs.getDate("data_nasc"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRg(rs.getString("rg_ie"));
                cliente.setMatricula(rs.getInt("matricula"));
                result.add(cliente);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }

}
