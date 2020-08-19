/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibm.sicredi.ibmscreditest;



import br.com.ibm.sicredi.ibmscreditest.control.util.CsvUtil;
import br.com.ibm.sicredi.ibmscreditest.service.ReceitaService;
import br.com.ibm.sicredi.ibmscreditest.service.ReceitaServiceImpl;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * * @titulo Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java
 * 
 * @obs: Esse app foi feito baseado no código do @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 *
 * 
 * @version  1.0.0
 * @author Harley Nogueira Monteiro
 * @e-mail harleymonteiro@gmail.com
 * @descrição: Essa classe é a principal, pois o app é iniciado por ela.
 * A classe foi configurado pelo Maven para controlar os repositórios e dependêcias necessária para o funcionamento do app.
 * As operações que ela controla são: chamar as configurações do SprintBoot standalone, Service Spring, chamar operações de 
 *ler e escrever no arquivo CSV e além de mostrar todas informações dos processos para o usuário.
 * 
 */
@SpringBootApplication
public class SincronizacaoReceita implements CommandLineRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Iniciando as configurações e execução do SpringBoot standlone 
        SpringApplication.run(SincronizacaoReceita.class, args);
    }
    
    
    // Esse Método é responsável por criar uma conexão com a classe de serviço do springboot standalone
    // Aqui terá as seguintes operações:
    // String toCsv(String pathcsv, String[][] stringArray) - Resposável por criar um arquivo csv de resposta
    // void   lerCsv(String urlcsv)                         - Responsável por ler um arquivo csv que o usuário colocou no padrão solicitado no teste.
    // List<CsvUtil.CsvRegistro> listaRegistoCsv()          - Responsável por tranformar o arquivo csv do usuário por uma lista de objetos para atualizar os dados.
    // ResultadoReceita getResultadoReceita()               - Responsável por atualizar e realizar o tratamento dos dados informando se o processo foi concluido com sucesso ou com falha.
    @Bean
    public ReceitaService getReceitaService(){
        return  new ReceitaServiceImpl();
    }
    

    @Override
    public void run(String... args) throws Exception {
        System.out.println("#########################################################################"); 
        System.out.println("# Processo Seletivo IBM - Desafio Técnico para Desenvolvedor Java       #");
        System.out.println("#_______________________________________________________________________#");
        System.out.println("# @Auth:   Harley Nogueira Monteiro                                     #");
        System.out.println("# @e-mail: harleymonteiro@gmail.com                                     #");
        System.out.println("#-----------------------------------------------------------------------#");
        System.out.println("# Cenário de Negócio:                                                   #");
        System.out.println("# Todo dia útil por volta das 6 horas da manhã um colaborador da        #\n"
                          +"#retaguarda do Sicredi recebe e organiza as informações de contas para  #\n"
                         + "#enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos #\n"
                         + "#Excel à Retaguarda. Hoje o Sicredi já possiu mais de 4 milhões de      #\n"
                         + "#contas ativas.                                                         #\n" +
                           "#Esse usuário da retaguarda exporta manualmente os dados em um arquivo  #\n"
                         + "#CSV para ser enviada para a Receita Federal, antes as 10:00 da manhã   #\n"
                         + "#na abertura das agências.                                              #\n"
                         + "#                                                                       #");
        System.out.println("# Requisito:                                                            #\n" +
                           "# Usar o \"serviço da receita\" (fake) para processamento automático do   #\n"
                         + "#arquivo.                                                               #");
        System.out.println("#                                                                       #");
        System.out.println("# Funcionalidade:                                                       #\n" +
                           "#0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar       #\n"
                         + "#SincronizacaoReceita <input-file>                                      #\n" +
                           "#1. Processa um arquivo CSV de entrada com o formato abaixo.            #\n" +
                           "#2. Envia a atualização para a Receita através do serviço (SIMULADO     #\n"
                         + "#pela classe ReceitaService).                                           #\n" +
                           "#3. Retorna um arquivo com o resultado do envio da atualização da       #\n"
                         + "Receita. Mesmo formato adicionando o resultado em uma nova coluna.      #");
        System.out.println("#-----------------------------------------------------------------------#");
        System.out.println("#                                                                       #");
        System.out.println("# Estrutura do App Java springBoot standalone                           #");
        System.out.println("# A classe principal está no pacote br.com.ibm.scredi.ibmscreditest com #\n"
                          +"#nome de SincronizacaoReceita.                                          #\n"
                         + "# A classe service está divida em dois arquivos uma interface e classe  #\n"
                         + "#esses arquivos estão localizados no pacote                             #\n"
                         + "#br.com.ibm.scredi.ibmscreditest.service com os nomes                   #\n"
                         + "#interface: ReceitaService e a classe: ReceitaServiceImpl.              #\n"
                         + "# O processo para ler e gravar o arquivo CSV está no pacote             #\n"
                         + "#br.com.ibm.scredi.ibmscreditest.control.util com o nome CsvUtil.       #\n"
                         + "# Foi criado um objeto com as informações necessárias da retaguarda do  #\n"
                         + "#Sicredi para controlar a situação de cada conta atualizada.            #");
        System.out.println("#########################################################################");
        System.out.println("");
        
        /*
         * Esse bloco tem a finalidade de validar se o arquivo csv foi passado correto para o app.
         */
        File f = null;
        int statusfilecsv = args.length;
        String urlcsv = "";
        if(statusfilecsv == 1){
            urlcsv = args[0];
            //urlcsv = "C:\\Users\\threadtec\\Documents\\NetBeansProjects\\IbmScrediTest\\IBMSicredi.csv";
            f = new File(urlcsv);
            if(f.isFile()) { 
                System.out.println("Iniciando a transmissao dos dados para atualizacao!\n\n...\n\n...\n\n...\n\n");
            }else{
                System.out.println("##############################################################"); 
                System.out.println("# Atencao: \n"
                                 + "# O arquivo CSV nao encontrado!"); 
                System.out.println("# Por esse motivo o app vai fechar em alguns segundos!");
                System.out.println("# Execute novamento com o endereco do arquivo CSV valido pelo argumrnto, por favor.");
                System.out.println("##############################################################");
                // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
                long wait = 1500;
                Thread.sleep(wait);
                System.exit(0);
            }
        }else{
           System.out.println("##############################################################"); 
           System.out.println("# Atencao: \n"
                            + "# E necessario colocar no argumento o caminho do arquivo CSV na hora de executar o app."); 
           System.out.println("# Por esse motivo o app vai fechar em alguns segundos!");
           System.out.println("# Execute novamento com o endereco do arquivo CSV valido pelo argumrnto, por favor.");
                System.out.println("##############################################################");
                // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
                long wait = 1500;
                Thread.sleep(wait);
           System.exit(0);
        }
        
        if(f == null){
            System.out.println("##############################################################"); 
            System.out.println("# Atencao: \n"
                            + "# Arquivo CSV não encontrado."); 
            System.out.println("# Por esse motivo o app vai fechar em alguns segundos!");
            System.out.println("# Execute novamento com o endereco do arquivo CSV valido pelo argumrnto, por favor.");
                System.out.println("##############################################################");
                // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
                long wait = 1500;
                Thread.sleep(wait);
            System.exit(0);
        } 
        // Fim da validação do arquivo CSV
        
        
        //Responsável por ler o arquvio CSV e transformar em uma lista de Objetos para atualização
        this.getReceitaService().lerCsv(urlcsv);
        List<CsvUtil.CsvRegistro> lstRegistroCsv = this.getReceitaService().listaRegistoCsv();
        
        System.out.println("\n\n---------------------------------------------------------------\n\n");
        //Responsável por atualizar todos os itens do CSV e além de realizar o tratamento de erros das informações e conexão
        for(CsvUtil.CsvRegistro objcsvregistro : lstRegistroCsv){
            this.getReceitaService().atualizarConta(objcsvregistro.getAgencia(), objcsvregistro.getConta(), objcsvregistro.getSaldo(), objcsvregistro.getStatus());
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
        //Responsável por criar um arquivo CSV de resposta das atualizações de cada item do CSV do usuário
        System.out.println(this.getReceitaService().toCsv(f.getParent()+"\\IbmSicrediRespostaCsv"+dateFormat.format(new Date())+".csv", this.getReceitaService().getResultadoReceita().toFileCsv()));
        
        System.out.println("\n\n\n\n\n\n"
                         + "###############################################################\n"
                         + "# App realizou todos os processos de acordo com a regra de negocio da IBM sicredi... OK\n"
                         + "# 0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>.... OK\n" +
                           "# 1. Processa um arquivo CSV de entrada com o formato abaixo.... OK\n" +
                           "# 2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).... OK\n" +
                           "# 3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.... OK\n"
                        + "# Por esse motivo o App irá fechar em alguns segundos...\n"
                         + "###############################################################");
    }
    
}
