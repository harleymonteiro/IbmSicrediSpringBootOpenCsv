/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibm.sicredi.ibmscreditest.service;


import br.com.ibm.sicredi.ibmscreditest.control.ResultadoReceita;
import br.com.ibm.sicredi.ibmscreditest.control.ResultadoReceita;
import br.com.ibm.sicredi.ibmscreditest.control.util.CsvUtil;
import br.com.ibm.sicredi.ibmscreditest.model.Retaguarda;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.util.StringUtils;

/**
 * @titulo Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java
 * @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 * @alterado por Harley Nogueira Monteiro
 */
public class ReceitaServiceImpl implements ReceitaService{

    private ResultadoReceita resultadoReceita = new ResultadoReceita();
    private List<CsvUtil.CsvRegistro> lstRegistroCsv;
    
    @Override
    public String toCsv(String pathcsv, String[][] stringArray){
        try {
            CsvUtil objcsv = new CsvUtil(pathcsv, stringArray);
            
            return "# O arquivo da resposta CSV foi salvo com sucesso no caminho: "+pathcsv;
        } catch (IOException ex) {
            Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
             return "# Falhou ao salvar o arquvio CSV.";
        }
    }
    
    @Override
    public void lerCsv(String urlcsv){
        try {
            //List<CsvServiceImpl.CsvRegistro> lstcsvler = this.getCsvService().lerCsvToJBean(urlcsv);
            
//        for(CsvServiceImpl.CsvRegistro objcsvregistro : lstcsvler)
//            System.out.println(objcsvregistro);
            CsvUtil objcsv = new CsvUtil(urlcsv);
            lstRegistroCsv = objcsv.getLstCsv();
        } catch (FileNotFoundException ex) {
            try {
                Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("##############################################################");
                System.out.println("# Atencao: \n"
                        + "# Erro na leitura do arquvio CSV.");
                System.out.println("# Por esse motivo o app vai fechar em alguns segundos!");
                System.out.println("# Execute novamento com um arquivo CSV valido , por favor.");
                System.out.println("##############################################################");
                // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
                long wait1 = 1500;
                Thread.sleep(wait1);
                System.exit(0);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ParseException ex) {
            try {
                Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("##############################################################");
                System.out.println("# Atencao: \n"
                        + "# Erro na leitura do arquvio CSV.");
                System.out.println("# Por esse motivo o app vai fechar em alguns segundos!");
                System.out.println("# Execute novamento com um arquivo CSV valido , por favor.");
                System.out.println("##############################################################");
                // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
                long wait1 = 1500;
                Thread.sleep(wait1);
                System.exit(0);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            try {
                Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("##############################################################");
                System.out.println("# Atencao: \n"
                                 + "# Erro na leitura do arquvio CSV.");
                System.out.println("# Por esse motivo o app vai fechar em alguns segundos!");
                System.out.println("# Execute novamento com um arquivo CSV valido , por favor.");
                System.out.println("##############################################################");
                // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
                long wait1 = 1500;
                Thread.sleep(wait1);
                System.exit(0);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    @Override
    public List<CsvUtil.CsvRegistro> listaRegistoCsv(){
        return this.lstRegistroCsv;
    }
    
    @Override
    public boolean atualizarConta(String agencia, String conta, String saldo, String status)
            throws RuntimeException, InterruptedException {
        Retaguarda objretaguarda = new Retaguarda(agencia, conta, saldo, status, "00", "", "");
        String resultadoagencia  = "";
        String resultadoconta    = "";
        String resultadosaldo    = "";
        String resultadotipo     = "";
        String resultadotempo    = "";
        
        // Formato agencia: 0000
//        if (agencia == null || agencia.length() != 4) {
//            return false;
//        }
        resultadoagencia = this.validaAgencia(agencia);
        
        // Formato conta: 000000
//        if (conta == null || conta.length() != 6) {
//            return false;
//        }
        resultadoconta = validaConta(conta);
        
        
        // Formato Saldo: ###,###.00
        resultadosaldo = this.validaSaldo(saldo);
        
//        // Tipos de status validos:
//        List tipos = new ArrayList();
//        tipos.add("A");
//        tipos.add("I");
//        tipos.add("B");
//        tipos.add("P");                
//                
//        if (status == null || !tipos.contains(status)) {
//            return false;
//        }

        resultadotipo = this.validaTipoStatus(status);
        
        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 4000) + 1000;
        resultadotempo = wait+"";
        objretaguarda.setTempo(wait+"");
        Thread.sleep(wait);

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) {
            objretaguarda.setResultado("Falhou: Simulando cenario de erro no serviço.");
            resultadoReceita.addRegistro(objretaguarda);
            throw new RuntimeException("Error");
        }
        
        if(resultadoagencia.equals("OK") && 
           resultadoconta.equals("OK")   && 
           resultadosaldo.equals("OK")   && 
           resultadotipo.equals("OK")){
            
            objretaguarda.setResultado("OK");
            objretaguarda.setDescricao("Atualizado com sucesso.\tAgência: OK\tConta: OK\tSaldo: OK\tTipoStatus: OK\tTempo: "+resultadotempo+"\tDescricao: OK");
        }else{
            String resultadofinal = "Falhou: ";
            if(!resultadoagencia.equals("OK")){resultadofinal += resultadoagencia+"\t";}
            if(!resultadoconta.equals("OK")){resultadofinal += resultadoconta+"\t";}
            if(!resultadosaldo.equals("OK")){resultadofinal += resultadosaldo+"\t";}
            if(!resultadotipo.equals("OK")){resultadofinal += resultadotipo+"\t";}
            
            resultadofinal += "Tempo: "+ resultadotempo;
            
            objretaguarda.setResultado("FALHOU");
            objretaguarda.setDescricao(resultadofinal);            
            resultadoReceita.addRegistro(objretaguarda);
            System.out.println("Atualizando dados: "+ objretaguarda.toString()+"\n\n");
            return false;
        }
        
        resultadoReceita.addRegistro(objretaguarda);
        System.out.println("Atualizando dados: "+ objretaguarda.toString()+"\n\n");
        return true;
        
    }

    @Override
    public ResultadoReceita getResultadoReceita() {
        return resultadoReceita;
    }
    
    private String validaAgencia(String objagencia){
    
        // Formato agencia: 0000
        if (objagencia == null) {
            return "Agência está com o valor NULL.";
        }else        
        if (objagencia.length() != 4) {
            return "O número da Agência está diferente de 4 dígitos.";
        }else{
            return "OK";
        }
    }
    
    private String validaConta(String objconta){
        // Formato conta: 000000
        if (objconta == null) {
            return "Conta está com o valor NULL.";
        }else{
            int validahifen = StringUtils.countOccurrencesOf(objconta, "-");
            if(validahifen == 1){
                String[] lstprotocolo = objconta.split("-");
                objconta = lstprotocolo[0]+lstprotocolo[1];
                if (objconta.length() != 6) {
                    return "O Número da conta está diferente de 6 dígitos.";
                }
            }else
            if(validahifen > 1){
                return "O formato do número da conta não está válido.";
            }else{
                if (objconta.length() != 6) {
                    return "O Número da conta está diferente de 6 dígitos.";
                }
            }
        }
        
        return "OK";
    }
    
    private String validaSaldo(String objsaldo){
        
        for (int i = 0; i < objsaldo.length(); i++) { 
            if (!Character.isDigit(objsaldo.charAt(i)) && objsaldo.charAt(i)!=",".charAt(0) && objsaldo.charAt(i)!=".".charAt(0) && objsaldo.charAt(i)!="-".charAt(0) ) 
            { 
                return "O valor do saldo não está válido.";
            } 
        }
        
        
        try {
            double validanumero = toNumberUS(objsaldo);
            return "OK";
        }catch (ParseException ex) {
            Logger.getLogger(ReceitaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "O valor do saldo não está válido.";
        }
    }
    
    private String validaTipoStatus(String objtipostatus){
    
        // Tipos de status validos:
        List tipos = new ArrayList();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");                
                
        // Formato agencia: 0000
        if (objtipostatus == null) {
            return "Tipo status está com o valor NULL.";
        }else        
        if (!tipos.contains(objtipostatus)) {
            return "O tipo de status cadastrado não é válido.";
        }else{
            return "OK";
        }
        
    
    }
    
    
    
    //Converte o número monetário do Brasil para US
    private double toNumberUS(String numero) throws ParseException{        
        String validador = numero.substring(numero.length()-3, numero.length()-2);
        if(validador.equals(".")){
            return Double.parseDouble(numero);
        }else
        if(validador.equals(",")){
            DecimalFormat df = new DecimalFormat("###,###.00");
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();

            //define o caractere separador das casas decimais
            dfs.setDecimalSeparator(',');
            //define o caractere separador dos grupos das milhares
            dfs.setGroupingSeparator('.');
            //seta o formatador de simbolos ao formatador do decimal
            df.setDecimalFormatSymbols(dfs);

            return df.parse(numero).doubleValue();
        }else{
            validador = numero.substring(numero.length()-2, numero.length()-1);
            if(validador.equals(".")){
                return Double.parseDouble(numero);
            }else
            if(validador.equals(",")){
                DecimalFormat df = new DecimalFormat("###,###.00");
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();

                //define o caractere separador das casas decimais
                dfs.setDecimalSeparator(',');
                //define o caractere separador dos grupos das milhares
                dfs.setGroupingSeparator('.');
                //seta o formatador de simbolos ao formatador do decimal
                df.setDecimalFormatSymbols(dfs);

                return df.parse(numero).doubleValue();
            }
        }        
        return Double.parseDouble(numero);
    }
}
