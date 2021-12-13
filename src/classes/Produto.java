/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.DAOCategoria_Produto;
import dao.DAOFornecedor;


/**
 *
 * @author Leon Jr
 */
public class Produto {
    private int idProduto;
    private String nome;
    private float custo;
    private String custoTxt;
    private String codigo;
    private String unidade,marca,patrimonio,localizacao,info;
    private int quantidade,categoria,fornecedor; 
    private String serie;
    private String of;
    private Fornecedor fornObj;

    public int getFornecedor() {
        return fornecedor;
    }
    
    public Fornecedor getFornObj() {
        DAOFornecedor dao = new DAOFornecedor();
        fornObj = dao.localizar(getFornecedor());
        if(fornObj!=null){
        return fornObj;
        }else{
            Fornecedor f = new Fornecedor();
            f.setNome("sem info");
            return f;
        }
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getOf() {
        return of;
    }

    public void setOf(String of) {
        this.of = of;
    }
    SecureDate sc = new SecureDate();
    
    
    public String getCustoTxt() {
        String txt = sc.dinheiroConverter(custo);
        setCustoTxt(txt);
        return custoTxt;
    }

    public void setCustoTxt(String custoTxt) {
        this.custoTxt = custoTxt;
    }
    
    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getMarca() {
        if(marca!=null){
            return marca;
        }else{
            return "";
        }   
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPatrimonio() {
        if(patrimonio!=null){
            return patrimonio;
        }else{
            return "";
        } 
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getLocalizacao() {
       if(localizacao!=null){
            return localizacao;
        }else{
            return "";
        } 
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getInfo() {
       if(info!=null){
            return info;
        }else{
            return "";
        } 
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCategoria() {
        return categoria;
    }
    
    public Categoria_Produto getCategoriaObj() {
        DAOCategoria_Produto dao = new DAOCategoria_Produto();
        return dao.localizar(categoria);
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getSerie() {
        if(serie!=null){
            return serie;
        }else{
            return "";
        } 
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return nome; 
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
       if(codigo!=null){
            return codigo;
        }else{
            return "";
        } 
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
