package com.company;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) {
        try {
            String pacote = null;
            String gitRepository = null;

            System.out.println("Buscando pacotes F-DROID");
            System.out.println("Iniciando Busca...");

            //String appDetalhes = "https://f-droid.org/en/packages/";
            //String id = "full-package-list";
            //String div = "<div id=" + '"' + id + '"' + ">";

            URL url = new URL("https://f-droid.org/en/packages/");
            //URI uri = url.toURI();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s;
            int cont = 1;
            while ((s = br.readLine()) != null) {
                if(cont == 207 || cont == 223 || cont == 238 || cont == 253 || cont == 268 || cont == 283 || cont == 298 || cont == 313 || cont == 328 || cont == 343|| cont == 358 || cont == 373 || cont == 388 || cont == 403 || cont == 418 || cont == 433 || cont == 448 || cont == 463 || cont == 478 || cont == 493 || cont == 508 || cont == 523 || cont == 538 || cont == 553 || cont == 568 || cont == 583 || cont == 598 || cont == 613 || cont == 628 || cont == 643){
                    pacote = s.replace("<a class=\"package-header\" href=\"","").replace("\">","");
                    System.out.println("Baixando pacote " + pacote);

                    String tempUrl = "https://f-droid.org/" + pacote.trim();

                    URL urlDetalhes = new URL(tempUrl);
                    //URI uriDetalhes = urlDetalhes.toURI();
                    BufferedReader brDetalhes = new BufferedReader(new InputStreamReader(urlDetalhes.openStream()));
                    String linha;
                    int contInterno = 1;
                    while ((linha = brDetalhes.readLine()) != null) {
                        String dados = linha.toString().trim();
                            if(dados.endsWith("Source Code</a>")){
                                gitRepository = dados.replace("\">Source Code</a>","");
                                gitRepository = gitRepository.replace("<a href=\"","");
                                System.out.println(gitRepository);
                            }

                        //System.out.println(contInterno + "|" + dados);
                        contInterno++;
                    }
                    brDetalhes.close();
                }
                cont++;
            }
            br.close();
            System.out.println("Finalizando Busca...");
        } catch (Exception excecao) {
            excecao.printStackTrace();
        }
    }
}
