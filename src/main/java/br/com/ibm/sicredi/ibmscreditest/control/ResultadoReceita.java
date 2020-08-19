/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibm.sicredi.ibmscreditest.control;

import br.com.ibm.sicredi.ibmscreditest.model.Retaguarda;
import java.util.ArrayList;
import java.util.List;

/**
 * @titulo Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java
 * @author Harley Nogueira Monteiro
 * @e-mail harleymonteiro@gmail.com
 */
public class ResultadoReceita {
    
    List<Retaguarda> lstRetarguarda;

    public ResultadoReceita() {
        lstRetarguarda = new ArrayList<Retaguarda>();
    }

    public List<Retaguarda> getLstRetarguarda() {
        return lstRetarguarda;
    }

    public void setLstRetarguarda(List<Retaguarda> lstRetarguarda) {
        this.lstRetarguarda = lstRetarguarda;
    }
    
    public void addRegistro(Retaguarda objretaguarda){
        this.lstRetarguarda.add(objretaguarda);
    }

    public String[][] toFileCsv(){
        String[][] arrayresultado = new String[this.lstRetarguarda.size()+1][7];
        arrayresultado[0][0]="resultado";
        arrayresultado[0][1]="agencia";
        arrayresultado[0][2]="conta";
        arrayresultado[0][3]="saldo";
        arrayresultado[0][4]="status";
        arrayresultado[0][5]="tempo";
        arrayresultado[0][6]="descricao";
        
        for(int i=0; i<this.lstRetarguarda.size(); i++){       
            Retaguarda obj = this.lstRetarguarda.get(i);
            arrayresultado[i+1] = obj.converteObjToArray();
        }    
        
        return arrayresultado;
    }
    
    public String toCsv() {
        String registrocsv = "agencia;conta;saldo;status;tempo;resultado\n"; 
        for(Retaguarda objretaguarda : lstRetarguarda)
            registrocsv += objretaguarda+"\n";
        return registrocsv;
    }
    
    
    
    
    
}
