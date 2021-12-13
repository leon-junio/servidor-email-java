/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAODepartamento;
import dao.DAOSetor;

/**
 *
 * @author junio
 */
public class Pertence {
    int idPertence,departamento,setor;
    Departamento dptObj;
    Setor setObj;

    public int getIdPertence() {
        return idPertence;
    }

    public void setIdPertence(int idPertence) {
        this.idPertence = idPertence;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public Departamento getDptObj() {
        DAODepartamento dao = new DAODepartamento();
        setDptObj(dao.localizar(departamento));
        return dptObj;
    }

    public void setDptObj(Departamento dptObj) {
        this.dptObj = dptObj;
    }

    public Setor getSetObj() {
        DAOSetor dao = new DAOSetor();
        setSetObj(dao.localizar(setor));
        return setObj;
    }

    public void setSetObj(Setor setObj) {
        this.setObj = setObj;
    }
    
}
