/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAOCliente;
import dao.DAOFuncionario;
import dao.DAOServico;
import dao.DAOTecnico;
import dao.DAOTipo_Ordem;
import java.sql.Date;

/**
 *
 * @author Leon Jr
 */
public class Ordem_Servico {

    private int idOS, servico, tipoOrdem, tecnico, cliente,solicitante;
    private String relato, descricao, numero,prioridade;
    

    public int getSolicitante() {
        return solicitante;
    }
    
    public Funcionario getSolicitanteObj() {
        DAOFuncionario dao = new DAOFuncionario();
        return dao.localizar(getSolicitante());
    }

    public void setSolicitante(int solicitante) {
        this.solicitante = solicitante;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public Cliente getClienteObj() {
        DAOCliente dao = new DAOCliente();
        return dao.localizar(cliente);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    private float valor;
    private Date data_inicio, data_finalizacao;

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_finalizacao() {
        return data_finalizacao;
    }

    public void setData_finalizacao(Date data_finalizacao) {
        this.data_finalizacao = data_finalizacao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public int getIdOS() {
        return idOS;
    }

    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    public int getServico() {
        return servico;
    }

    public Servico getServicoObj() {
        DAOServico dao = new DAOServico();
        return dao.localizar(servico);
    }

    public void setServico(int servico) {
        this.servico = servico;
    }

    public int getTipoOrdem() {
        return tipoOrdem;
    }

    public Tipo_Ordem getTipo_OrdemObj() {
        DAOTipo_Ordem dao = new DAOTipo_Ordem();
        return dao.localizar(tipoOrdem);
    }

    public void setTipoOrdem(int tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

    public int getTecnico() {
        return tecnico;
    }

    public Tecnico getTecnicoObj() {
        DAOTecnico dao = new DAOTecnico();
        return dao.localizar(tecnico);
    }

    public void setTecnico(int tecnico) {
        this.tecnico = tecnico;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
