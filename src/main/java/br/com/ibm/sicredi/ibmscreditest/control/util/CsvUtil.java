/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibm.sicredi.ibmscreditest.control.util;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @titulo Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java
 * @author Harley Nogueira Monteiro
 * @e-mail harleymonteiro@gmail.com
 */
public class CsvUtil {
    
    CsvToBean csv;
    CSVReader csvReader;
    List<CsvRegistro> lstCsv;

    public CsvUtil(String pathcsv, String[][] stringArray) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(pathcsv),
                                    ';',
                                    CSVWriter.NO_QUOTE_CHARACTER,
                                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                    CSVWriter.DEFAULT_LINE_END);
        for (String[] array : stringArray) {
            writer.writeNext(array);
        }
	    
	writer.close();
        //System.out.println("# O arquivo do resposta CSV foi salvo com sucesso no caminho: "+pathcsv);
    }
    public CsvUtil(String urlcsv) throws FileNotFoundException, ParseException, IOException {
        CSVReader reader = new CSVReader(new FileReader(urlcsv), ';');
        lstCsv = new ArrayList<CsvRegistro>();
        
	 
        // read line by line
        String[] record = null;

        boolean primeiralinha = true;
        while ((record = reader.readNext()) != null) {
                if(primeiralinha){
                    primeiralinha = false;
                    continue;
                } 
                CsvRegistro oobj = new CsvRegistro();
                oobj.setAgencia(record[0]);
                oobj.setConta(record[1]);
                oobj.setSaldo(record[2]);
                oobj.setStatus(record[3]);
                lstCsv.add(oobj);
        }
        
        reader.close();
       
        System.out.println("# O app realizou a leitura de todos os dados do arquvio CSV\n\n");
        System.out.println("# O app vai iniciar a atualizacao das informacoes \n\n");
//        for(CsvRegistro obj : lstCsv)
//        System.out.println(obj);
        
    }
    public List<CsvRegistro> getLstCsv() {
        return lstCsv;
    }
    public class CsvRegistro implements Serializable{
        
        private static final long serialVersionUID = 1L;
         
        @CsvBindByName(column = "agencia")
        String agencia;
        @CsvBindByName(column = "conta")
        String conta;
        @CsvBindByName(column = "saldo")
        String saldo;
        @CsvBindByName(column = "status")
        String status;

        public CsvRegistro() {
        }

        public CsvRegistro(String agencia, String conta, String saldo, String status) {
            this.agencia = agencia;
            this.conta = conta;
            this.saldo = saldo;
            this.status = status;
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

        @Override
        public String toString() {
            return "CsvRegistro{" + "agencia=" + agencia + ", conta=" + conta + ", saldo=" + saldo + ", status=" + status + '}';
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 59 * hash + Objects.hashCode(this.agencia);
            hash = 59 * hash + Objects.hashCode(this.conta);
            hash = 59 * hash + Objects.hashCode(this.saldo);
            hash = 59 * hash + Objects.hashCode(this.status);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CsvRegistro other = (CsvRegistro) obj;
            if (!Objects.equals(this.agencia, other.agencia)) {
                return false;
            }
            if (!Objects.equals(this.conta, other.conta)) {
                return false;
            }
            if (!Objects.equals(this.saldo, other.saldo)) {
                return false;
            }
            if (!Objects.equals(this.status, other.status)) {
                return false;
            }
            return true;
        }
        
        
        
    }
    
}
