/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAOFuncionario;
import java.sql.Date;

/**
 *
 * @author junio
 */
public class Notificacao {
    int idNotificacao, funcionario, leitura;
    String mensagem;
    Date dataNot;
    Funcionario funcObj;

    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public int getLeitura() {
        return leitura;
    }

    public void setLeitura(int leitura) {
        this.leitura = leitura;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataNot() {
        return dataNot;
    }

    public void setDataNot(Date dataNot) {
        this.dataNot = dataNot;
    }

    public Funcionario getFuncObj() {
        DAOFuncionario dao = new DAOFuncionario();
        setFuncObj(dao.localizar(funcionario));
        return funcObj;
    }

    public void setFuncObj(Funcionario funcObj) {
        this.funcObj = funcObj;
    }
}
