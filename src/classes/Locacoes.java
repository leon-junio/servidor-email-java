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
public class Locacoes {

    @Override
    public String toString() {
        return nome;
    }
    
    private String endereco,telefone,celular,email,descricao,nome;
    private int responsavel,departamento;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getResponsavel() {
        return responsavel;
    }
    
    public Funcionario getResponsavelObj() {
        DAOFuncionario dao = new DAOFuncionario();
        return dao.localizar(responsavel);
    }

    public void setResponsavel(int responsavel) {
        this.responsavel = responsavel;
    }

    public int getDepartamento() {
        return departamento;
    }
    
    public Departamento getDepartamentoObj() {
        DAODepartamento dao = new DAODepartamento();
        return dao.localizar(departamento);
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    private int idLocacao;
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
