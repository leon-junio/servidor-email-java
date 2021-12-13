/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAOFuncionario {

    /**
     * Método que busca no banco de dados a lista de funcionarios
     *
     * @return A lista de funcionarios
     */
    public ArrayList<Funcionario> getLista() {
        String sql = "select * from Funcionario";
        ArrayList<Funcionario> lista = new ArrayList<>();
        ArrayList<Funcionario> aux = new ArrayList<>();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setIdFunc(rs.getInt("idFuncionario"));
                obj.setTipo(rs.getInt("tipo"));
                obj.setCPF(rs.getString("CPF"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNome(rs.getString("nome"));
                lista.add(obj);
                for (Funcionario func : lista) {
                    if (func.getCPF() != null) {
                        if (func.getCPF().equals("desativado")) {
                            aux.add(func);
                        }
                    }
                }
                for (Funcionario func : aux) {
                    lista.remove(func);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("ERRO NO SISTEMA DE DATA ACESS OBJECT " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Método que inclui um objeto na banco de dados
     *
     * @param obj O objeto desajado que vai ser adicionado
     * @return True se der certo e false se não der certo
     */
    public boolean incluir(Funcionario obj) {
        String sql = "call newFunc(?,?,?,?,?,?);";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(3, obj.getNome());
            cod.setString(1, obj.getCPF());
            cod.setString(2, obj.getEndereco());
            cod.setInt(6, obj.getTipo());
            String senha = "";
            String user = "";
            String txt = "";
            int count = 0;
            for (int i = 0; i < obj.getNome().length(); i++) {
                if (obj.getNome().charAt(i) == ' ') {
                    count++;
                    if (count == 1) {
                        senha = txt;
                        txt = "";
                    }
                    txt = "";
                }
                txt += obj.getNome().charAt(i);
                if (i + 1 == obj.getNome().length()) {
                    user = senha + "" + txt;
                    if (count == 0) {
                        senha = user;
                    }
                    break;
                }
            }
            int a = localizarNome("select * from funcionario where nome like '" + obj.getNome() + "';").size();
            if (a > 0) {
                user += "" + a;
            }
            user = user.replace(' ', '.');
            user = user.toLowerCase();
            senha = senha.toLowerCase();
            cod.setString(4, user);
            cod.setString(5, senha);
            if (cod.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuário: " + user + "@sms\n"
                        + "Senha: " + senha + "123", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
    public boolean alterar(Funcionario obj) {
        String sql = "update Funcionario set nome = ?, CPF=?, endereco = ?, tipo=? where idFuncionario = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1, obj.getNome());
            cod.setString(2, obj.getCPF());
            cod.setString(3, obj.getEndereco());
            cod.setInt(4, obj.getTipo());
            cod.setInt(5, obj.getIdFunc());
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
    public boolean remover(Funcionario obj) {
        String sql = "delete from Funcionario where idFuncionario = ?";
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, obj.getIdFunc());
            if (cod.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "excluido com sucesso");
                return true;
            }
        } catch (SQLException ex) {
            try {
                System.out.println("Erro de SQL " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "não foi excluido\n"
                        + "porem foi desativado");
                PreparedStatement cod = Conexao.getPreparedStatement(sql);
                cod.setInt(1, obj.getIdFunc());
                cod = Conexao.getPreparedStatement("call desativarFunc(" + obj.getIdFunc() + ");");
                cod.executeUpdate();
                return false;
            } catch (SQLException e) {
                System.out.println("Erro de SQL ao desativar" + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Método que localiza um objeto dentro do banco de dados
     *
     * @param id A primary key do objeto
     * @return O objeto pronto para uso
     */
    public Funcionario localizar(int id) {
        String sql = "select * from Funcionario where idFuncionario = ?";
        Funcionario obj = new Funcionario();
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, id);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                obj.setIdFunc(rs.getInt("idFuncionario"));
                obj.setNome(rs.getString("nome"));
                obj.setCPF(rs.getString("CPF"));
                obj.setEndereco(rs.getString("Endereco"));
                obj.setTipo(rs.getInt("tipo"));
                return obj;
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
        return null;
    }

    public int doLogin(String sql) {
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("idFuncionario");
            }
            return id;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL ao dologin" + ex.getMessage() + " " + ex.getSQLState());
            return 0;
        }
    }

    public boolean doUpdate(String sql) {
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.executeQuery();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso.", "Senha alterada", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL ao doupdate" + ex.getMessage() + " " + ex.getSQLState());
            return false;
        }
    }

    public boolean online(int id, int num) {
        try {
            String sql = "update usersOnline set ativo = ? where func like ? ;";
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1, num);
            cod.setInt(2, id);
            cod.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL ao dizer que estou online" + ex.getMessage() + " " + ex.getSQLState());
            return false;
        }
    }

    public boolean broadcast(String sql) {
        try {
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL ao BROADCAST" + ex.getMessage() + " " + ex.getSQLState());
            return false;
        }
    }

    /**
     * Método que localiza uma lista de objetos no banco de dados por base no
     * nome
     *
     * @param sql Comando em sql que vai ser executado para localizar a lista
     * @return A lista pronta com todos os nomes localizados
     */
    public ArrayList<Funcionario> localizarNome(String sql) {
        try {
            ArrayList<Funcionario> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setIdFunc(rs.getInt("idFuncionario"));
                obj.setTipo(rs.getInt("tipo"));
                obj.setCPF(rs.getString("CPF"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNome(rs.getString("nome"));
                result.add(obj);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
