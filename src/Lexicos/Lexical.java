package Lexicos;

import java.util.ArrayList;
import Lexicos.tipos;

public class Lexical {

    //Definicoes Regulares
    String numeros = "\\d";
    String letras = "[a-zA-Z]+";
    String caracteres = "[a-zA-Z]";
    String variaveis = "[a-zA-Z]*[0-9]*";

    //Estrutura que contem os dados do lexico
    ArrayList<ArrayList> lexico = new ArrayList<ArrayList>();
    ArrayList<Object> dadosLexico = new ArrayList<Object>();
    ArrayList<Object> finalLexico = new ArrayList<Object>();

    //Fazendo a verificação do nome atribuido a variavel
    public void verificarNomeVariavel(String[] arr){
        if(arr[1].matches(variaveis)){ dadosLexico.add(arr[1]); }//Nome da Variavel inserida
        else{ System.out.println("Erro lexico encontrado. " + arr[1] + " nao e um nome valido de variavel"); }
    }

    //Mostra a lista com todos os tokens atuais
    public void mostrarTokens(ArrayList<ArrayList> al){
        int tam = al.size();

        for(int i = 0; i <= tam - 1; i++){
            System.out.println(al.get(i));
        }
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
            dadosLexico.add(tipos.INTEGER); //Tipo da Variavel inserida

            verificarNomeVariavel(arrOfStr);

            //Removendo sinais que não servem como tokens
            arrOfStr[2] = arrOfStr[2].replace("= ", "");
            //Fazendo a verificação do valor atribuido a variavel
            if(arrOfStr[2].matches(numeros)){ dadosLexico.add(arrOfStr[2]); } //Valor da Variavel inserida
            else{ System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varI"); }
        }else if(arrOfStr[0].equals("varC")){
            dadosLexico.add(tipos.CHAR);

            verificarNomeVariavel(arrOfStr);

            //Removendo sinais que não servem como tokens
            arrOfStr[2] = arrOfStr[2].replace("= ", "");
            //Fazendo a verificação do valor atribuido a variavel
            if(arrOfStr[2].matches(caracteres)){ dadosLexico.add(arrOfStr[2]); }//Valor da Variavel inserida
            else{ System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varC"); }
        }else if(arrOfStr[0].equals("varB")){
            dadosLexico.add(tipos.BOOL);

            verificarNomeVariavel(arrOfStr);

            //Removendo sinais que não servem como tokens
            arrOfStr[2] = arrOfStr[2].replace("= ", "");
            //Fazendo a verificação do valor atribuido a variavel
            if(arrOfStr[2].matches(letras) && (arrOfStr[2].equals("true") || arrOfStr[2].equals("false"))){
                dadosLexico.add(arrOfStr[2]);
            }else{ System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varB"); }
        }

        finalLexico.add("n = " + dadosLexico.size());
        finalLexico.add(tipos.EOF);

        lexico.add(dadosLexico);
        lexico.add(finalLexico);

        mostrarTokens(lexico);

        return lexico;
    }


}
