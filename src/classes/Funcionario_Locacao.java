/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAOFuncionario;
import dao.DAOLocacao;

/**
 *
 * @author Leon Jr
 */
public class Funcionario_Locacao {

    private int funcionario,locacao,idFuncionario_Locacao;
    private Funcionario funcObj;
    private Locacoes locObj;

    public Funcionario getFuncObj() {
        DAOFuncionario daof = new DAOFuncionario();
        setFuncObj(daof.localizar(funcionario));
        return funcObj;
    }

    public void setFuncObj(Funcionario funcObj) {
        this.funcObj = funcObj;
    }

    public Locacoes getLocObj() {
        DAOLocacao daol = new DAOLocacao();
        setLocObj(daol.localizar(locacao));
        return locObj;
    }

    public void setLocObj(Locacoes locObj) {
        this.locObj = locObj;
    }

    public int getLocacao() {
        return locacao;
    }

    public void setLocacao(int locacao) {
        this.locacao = locacao;
    }

    public int getIdFuncionario_locacao() {
        return idFuncionario_Locacao;
    }

    public void setIdFuncionario_locacao(int idProduto_locacao) {
        this.idFuncionario_Locacao = idProduto_locacao;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

}
