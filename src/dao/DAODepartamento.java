/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Departamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon Jr
 */
public class DAODepartamento {
    /**
     * Método que busca no banco de dados a lista de atendimentos
     *
     * @return A lista de atendimentos
     */
    public ArrayList<Departamento> getLista(){
        String sql = "select * from Departamento";
        ArrayList <Departamento> lista = new ArrayList<>();
        try{
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while(rs.next()){
                Departamento obj  = new Departamento();
                obj.setNome(rs.getString("nome"));
                obj.setIdDepartamento(rs.getInt("idDepartamento"));
                lista.add(obj);
            }
        }catch(SQLException ex){
            System.out.println("Erro de SQL "+ex.getMessage());
        }
        return lista;
    }
    /**
     * Método que inclui um objeto na banco de dados
     * @param obj O objeto desajado que vai ser adicionado
     * @return True se der certo e false se não der certo
     */
    public boolean incluir(Departamento obj){
        String sql = "insert into Departamento (nome) values (?)";
        try{
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1,obj.getNome());;
            if(cod.executeUpdate()>0){
                return true;
            }else{
                 return false;
            }
        }catch(SQLException ex){
            System.out.println("Erro de SQL "+ex.getMessage());
            return false;
        }
    }
    /**
     * Método que atualiza um objeto na banco de dados
     * @param obj O objeto desajado que vai ser atualizado
     * @return True se der certo e false se não der certo
     */
     public boolean alterar(Departamento obj){
        String sql = "update Departamento set nome=? where idDepartamento = ?";
        try{
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setString(1,obj.getNome());
            cod.setInt(2,obj.getIdDepartamento());
            if(cod.executeUpdate()>0){
                return true;
            }else{
                 return false;
            }
        }catch(SQLException ex){
            System.out.println("Erro de SQL "+ex.getMessage());
            return false;
        }
    }
     /**
     * Método que remove um objeto na banco de dados
     * @param obj O objeto desajado que vai ser removido
     * @return True se der certo e false se não der certo
     */
     public boolean remover(Departamento obj){
        String sql = "delete from Departamento where idDepartamento = ?";
        try{
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1,obj.getIdDepartamento());
            if(cod.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"excluido com sucesso");
                return true;
            }else{
                 JOptionPane.showMessageDialog(null,"não excluido com sucesso");
                 return false;
            }
        }catch(SQLException ex){
            System.out.println("Erro de SQL "+ex.getMessage());
            return false;
        }
    }
    
     
     
     /**
      * Método que localiza um objeto dentro do banco de dados 
      * @param id A primary key do objeto
      * @return O objeto pronto para uso
      */
     public Departamento localizar(int id){
         String sql = "select * from Departamento where idDepartamento = ?";
         Departamento obj = new Departamento();
         try{
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            cod.setInt(1,id);
            ResultSet rs = cod.executeQuery();
            while(rs.next()){
                obj.setNome(rs.getString("nome"));
                obj.setIdDepartamento(rs.getInt("idDepartamento"));
                return obj;
            }
        }catch(SQLException ex){
            System.out.println("Erro de SQL "+ex.getMessage());
            return null;
        }
         return null;
     } 
     
        /**
     * Método que localiza um objeto dentro do banco de dados
     *
     * @param sql A primary key do objeto
     * @return O objeto pronto para uso
     */
    public ArrayList<Departamento> localizarNome(String sql) {
        try {
            ArrayList<Departamento> result = new ArrayList<>();
            PreparedStatement cod = Conexao.getPreparedStatement(sql);
            ResultSet rs = cod.executeQuery();
            while (rs.next()) {
                Departamento obj = new Departamento();
                obj.setNome(rs.getString("nome"));
                obj.setIdDepartamento(rs.getInt("idDepartamento"));
                result.add(obj);
            }
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro de SQL " + ex.getMessage());
            return null;
        }
    }
}
