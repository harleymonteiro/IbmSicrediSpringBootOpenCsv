/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibm.sicredi.ibmscreditest.model;

/**
 * @titulo Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java
 * @author Harley Nogueira Monteiro
 * @e-mail harleymonteiro@gmail.com
 */
public class Retaguarda {
    
    String agencia;
    String conta;
    String saldo;
    String status;
    String resultado;
    String tempo;
    String descricao;

    public Retaguarda() {
    }   

    public Retaguarda(String agencia, String conta, String saldo, String status,  String tempo, String resultado, String descricao) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
        this.resultado = resultado;
        this.tempo = tempo;
        this.descricao = descricao;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] converteObjToArray(){
        String[] objarray = {this.resultado,this.agencia,this.conta,this.saldo,this.status,this.tempo,this.descricao};
        return objarray;
    }

    @Override
    public String toString() {
        return resultado + ": agencia=" + agencia + ", conta=" + conta + ", saldo=" + saldo + ", status=" + status + ", tempo=" + tempo + ", descricao=" + descricao;
    }
    
    

}
