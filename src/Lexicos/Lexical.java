package Lexicos;

import java.util.ArrayList;
import Lexicos.tipos;

public class Lexical {

    //Definicoes Regulares
    String numeros = "(\\d)*";
    String letras = "[a-zA-Z]+";
    String caracteres = "[a-zA-Z]";
    String variaveis = "([a-zA-Z]*[0-9]*)+";

    //Variaveis de controle
    boolean jaEncontrado = false; //Verificador se a variavel possui um valor vazio atribuido a ele e contem somente o nome

    //Estruturas que contem os dados do lexico
    ArrayList<ArrayList> lexico = new ArrayList<ArrayList>();
    ArrayList<Object> dadosLexico = new ArrayList<Object>();
    ArrayList<Object> finalLexico = new ArrayList<Object>();

    //Fazendo a verificação do nome atribuido a variavel
    public void verificarNomeVariavel(String[] arr){
        if (arr[1].indexOf(';') != -1) {
            arr[1] = arr[1].replace(";", "");
            jaEncontrado = true;
        }
        if (arr[1].matches(variaveis)) {
            dadosLexico.add(arr[1]);
        }//Nome da Variavel inserida
        else {
            System.out.println("Erro lexico encontrado. " + arr[1] + " nao e um nome valido de variavel");
        }
    }

    //Mostra a lista com todos os tokens atuais
    public void mostrarTokens(ArrayList<ArrayList> al){
        int tam = al.size(); //Captura o tamanho total da lista atual de tokens

        for(int i = 0; i <= tam - 1; i++){
            System.out.print(al.get(i) + " ");
        }
        System.out.println();
    }

    //Analisa a estrutura lexica inserida
    public ArrayList leituraLexico(String frase){
        String[] arrOfStr = {};
        try{
            arrOfStr = frase.split(" ", 3);
        }catch(Exception e){
            System.out.println("Erro lexico encontrado. A sentença está mal construida");
        }

        if(arrOfStr[0].equals("varI")){
            dadosLexico.add(tipos.INTEGER); //Tipo da Variavel inserida (int)

            verificarNomeVariavel(arrOfStr);

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    //Tratamento caso o valor atribuido seja vazio
                    try {
                        //Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace("= ", "");
                        //Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(numeros)) {
                            dadosLexico.add(arrOfStr[2]);
                        } //Valor da Variavel inserida
                        else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varI");
                        }
                    } catch (Exception e) {
                        dadosLexico.add(tipos.NULO);
                    }
                    dadosLexico.add(tipos.EOI);
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{ dadosLexico.add(tipos.NULO); dadosLexico.add(tipos.EOI); }
        }else if(arrOfStr[0].equals("varC")){
            dadosLexico.add(tipos.CHAR); //Tipo da Variavel inserida (char)

            verificarNomeVariavel(arrOfStr);

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    //Tratamento caso o valor atribuido seja vazio
                    try {
                        //Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace("= ", "");
                        //Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(caracteres)) {
                            dadosLexico.add(arrOfStr[2]);
                        } //Valor da Variavel inserida
                        else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varC");
                        }
                    } catch (Exception e) {
                        dadosLexico.add(tipos.NULO);
                    }
                    dadosLexico.add(tipos.EOI);
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{ dadosLexico.add(tipos.NULO); dadosLexico.add(tipos.EOI); }
        }else if(arrOfStr[0].equals("varB")) {
            dadosLexico.add(tipos.BOOL); //Tipo da Variavel inserida (boolean)

            verificarNomeVariavel(arrOfStr);

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    //Tratamento caso o valor atribuido seja vazio
                    try {
                        //Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace("= ", "");
                        //Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(letras) && (arrOfStr[2].equals("true") || arrOfStr[2].equals("false"))) {
                            dadosLexico.add(arrOfStr[2]);
                        } else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varB");
                        }
                    } catch (Exception e) {
                        dadosLexico.add(tipos.NULO);
                    }
                    dadosLexico.add(tipos.EOI);
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{ dadosLexico.add(tipos.NULO); dadosLexico.add(tipos.EOI); }
        }

        //Calculo dos tokens caso tenha ou não tenha um valor atribuido a ele
        if(dadosLexico.get(2) == tipos.NULO){
            int tam = dadosLexico.size() - 1; //Não contabiliza o token caso a variável não tenha valor atribuido
            finalLexico.add("tokens = " + tam);
            finalLexico.add(tipos.EOF);
        }else{
            finalLexico.add("tokens = " + dadosLexico.size());
            finalLexico.add(tipos.EOF);
        }

        //Adiciona a lista de tokens e as informacoes estruturais a estrutura de armazenamento de compilação
        lexico.add(dadosLexico);
        lexico.add(finalLexico);

        mostrarTokens(lexico);

        return lexico;
    }
}