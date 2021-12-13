/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAODepartamento;
import dao.DAOFuncionario;

/**
 *
 * @author Leon Jr
 */
public class Tecnico {

    private int idTecnico, idDepartamento, func;

    public int getFunc() {
        return func;
    }
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public void setFunc(int func) {
        this.func = func;
    }
    private String nome;
    private Funcionario funcObj;
    private Departamento departamentoObj;

    @Override
    public String toString() {
        return nome;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public Departamento getDepartamentoObj() {
        DAODepartamento dao = new DAODepartamento();
        departamentoObj = dao.localizar(idDepartamento);
        return departamentoObj;
    }

    public Funcionario getFuncObj() {
        DAOFuncionario dao = new DAOFuncionario();
        funcObj = dao.localizar(func);
        return funcObj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
