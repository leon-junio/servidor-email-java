/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAOCliente;
import dao.DAODepartamento;
import dao.DAOFuncionario;
import dao.DAOLocacao;
import java.sql.Date;

/**
 *
 * @author Leon Jr
 */
public class Solicitacao {

    private int idSolicitacoes, locacao, funcionario, cliente, departamento, situacao;

    public int getSituacao() {
        return situacao;
    }

    public String getSituacaoStr() {
        switch (getSituacao()) {
            case 0:
                return "Não respondida";
            case 1:
                return "Aceita";
            case 2:
                return "Negada";
            default:
                return "Indefinida";
        }
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
    private Date data_limite, data_solicitacao;
    private String numero, justificativa;

    public int getIdSolicitacoes() {
        return idSolicitacoes;
    }

    public void setIdSolicitacoes(int idSolicitacoes) {
        this.idSolicitacoes = idSolicitacoes;
    }

    public int getLocacao() {
        return locacao;
    }

    public Locacoes getLocacaoObj() {
        DAOLocacao dao = new DAOLocacao();
        return dao.localizar(getLocacao());
    }

    public void setLocacao(int locacao) {
        this.locacao = locacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Date getData_limite() {
        return data_limite;
    }

    public void setData_limite(Date data_dev) {
        this.data_limite = data_dev;
    }

    public Date getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(Date data_atual) {
        this.data_solicitacao = data_atual;
    }

}
