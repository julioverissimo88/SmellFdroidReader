package com.company;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class Main {

    private static  long appsLidos = 0;

    public static void nextPage(String urlSite,long qtdEscolhida ){
        try{
            URL url = new URL(urlSite);
            //URI uri = url.toURI();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s;
            String pacote = null;
            String gitRepository = null;
            long qtdApps = 0;

            long cont = 1;

            while ((s = br.readLine()) != null) {
                if (qtdApps < qtdEscolhida) {

                    //if (s.startsWith("<a class=\"package-header\" href=\"")) {
                    if (cont == 207 || cont == 223 || cont == 238 || cont == 253 || cont == 268 || cont == 283 || cont == 298 || cont == 313 || cont == 328 || cont == 343 || cont == 358 || cont == 373 || cont == 388 || cont == 403 || cont == 418 || cont == 433 || cont == 448 || cont == 463 || cont == 478 || cont == 493 || cont == 508 || cont == 523 || cont == 538 || cont == 553 || cont == 568 || cont == 583 || cont == 598 || cont == 613 || cont == 628 || cont == 643) {
                        pacote = s.replace("<a class=\"package-header\" href=\"", "").replace("\">", "");
                        System.out.println("Baixando pacote " + pacote);

                        String tempUrl = "https://f-droid.org/" + pacote.trim();

                        URL urlDetalhes = new URL(tempUrl);
                        //URI uriDetalhes = urlDetalhes.toURI();
                        BufferedReader brDetalhes = new BufferedReader(new InputStreamReader(urlDetalhes.openStream()));
                        String linha;
                        int contInterno = 1;
                        while ((linha = brDetalhes.readLine()) != null) {
                            String dados = linha.toString().trim();
                            if (dados.endsWith("Source Code</a>")) {
                                gitRepository = dados.replace("\">Source Code</a>", "");
                                gitRepository = gitRepository.replace("<a href=\"", "");
                                System.out.println(gitRepository);
                                GitRepository.CloneRepostory(gitRepository + ".git");
                                qtdApps++;
                            }

                            //System.out.println(contInterno + "|" + dados);
                            contInterno++;
                        }
                        brDetalhes.close();
                        appsLidos++;
                    }
                    cont++;
                } else {
                    break;
                }
            }
            br.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        try {
            System.out.println("Quantidade de aplicativos a baixar");
            Scanner sc = new Scanner(System.in);
            long numApps =  sc.nextInt();
            //FdroidMiner fdroidApps = new FdroidMiner();
            //ArrayList<String> lApps = fdroidApps.getListRepository(numApps);

            long qtdEscolhida = numApps;


            System.out.println("Buscando pacotes F-DROID");
            System.out.println("Iniciando Busca...");

            int cont = 1;
            while (appsLidos < qtdEscolhida) {
                if(appsLidos == 0){
                    nextPage("https://f-droid.org/en/packages/",qtdEscolhida);
                }
                else{
                    nextPage("https://f-droid.org/en/packages/" + cont + "/index.html",qtdEscolhida);
                    //System.out.println("https://f-droid.org/en/packages/" + cont + "/index.html");
                }
                cont++;
            }


            System.out.println("Finalizando Busca...");
            System.out.println("Quantidade de aplicativos baixados: " + appsLidos);


        } catch (Exception excecao) {
            excecao.printStackTrace();
        }
    }
}
