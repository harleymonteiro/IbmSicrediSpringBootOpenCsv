/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibm.sicredi.ibmscreditest.service;

import br.com.ibm.sicredi.ibmscreditest.control.ResultadoReceita;
import br.com.ibm.sicredi.ibmscreditest.control.ResultadoReceita;
import br.com.ibm.sicredi.ibmscreditest.control.util.CsvUtil;
import java.util.List;

/**
 * @titulo Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java
 * @author Harley Nogueira Monteiro
 * @e-mail harleymonteiro@gmail.com
 */
public interface ReceitaService{
    boolean atualizarConta(String agencia, String conta, String saldo, String status) 
            throws RuntimeException, InterruptedException;
    String toCsv(String pathcsv, String[][] stringArray);
    void   lerCsv(String urlcsv);
    List<CsvUtil.CsvRegistro> listaRegistoCsv();
    ResultadoReceita getResultadoReceita();
}
