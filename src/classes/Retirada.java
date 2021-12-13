/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAOCliente;
import dao.DAODepartamento;
import dao.DAOFuncionario;
import dao.DAOSetor;
import java.sql.Date;

/**
 *
 * @author Leon Jr
 */
public class Retirada {

    private int idRetirada, funcionario, cliente, departamento, setor;
    private Date data_dev, data_atual;
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdRetirada() {
        return idRetirada;
    }

    public void setIdRetirada(int idRetirada) {
        this.idRetirada = idRetirada;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public Funcionario getFuncionarioObj() {
        DAOFuncionario daof = new DAOFuncionario();
        return daof.localizar(funcionario);
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public int getCliente() {
        return cliente;
    }

    public Cliente getClienteObj() {
        DAOCliente dao = new DAOCliente();
        return dao.localizar(cliente);
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getDepartamento() {
        return departamento;
    }

    public Departamento getDepartamentoObj() {
        DAODepartamento dao = new DAODepartamento();
        if (departamento > 0) {
            return dao.localizar(departamento);
        } else {
            Departamento dp = new Departamento();
            dp.setNome(getClienteObj().getNome());
            return dp;
        }
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getSetor() {
        return setor;
    }

    public Setor getSetorObj() {
        DAOSetor dao = new DAOSetor();
        if (setor > 0) {
            return dao.localizar(setor);
        } else {
            Setor obj = new Setor();
            obj.setNome(getClienteObj().getNome());
            return obj;
        }
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public Date getData_dev() {
        return data_dev;
    }

    public void setData_dev(Date data_dev) {
        this.data_dev = data_dev;
    }

    public Date getData_atual() {
        return data_atual;
    }

    public void setData_atual(Date data_atual) {
        this.data_atual = data_atual;
    }

}
