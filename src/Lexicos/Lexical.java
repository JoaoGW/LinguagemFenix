package Lexicos;

import java.util.ArrayList;
import Lexicos.tipos;
import Sintatico.Tokens;

public class Lexical {

    //Definicoes Regulares
    String numeros = "(\\d)*";
    String letras = "[a-zA-Z]+";
    String caracteres = "[a-zA-Z]";
    String variaveis = "([a-zA-Z]*[0-9]*)+";

    //Variaveis de controle
    boolean jaEncontrado = false; //Verificador se a variavel possui um valor vazio atribuido a ele e contem somente o nome
    boolean invalidName = false; //Verificador se alguma anormalidade de nomeclatura ocorreu

    Tokens tkn = new Tokens();

    //Fazendo a verificação do nome atribuido a variavel
    public void verificarNomeVariavel(String[] arr, ArrayList<Object> data){
        if (arr[1].indexOf(';') != -1) {
            arr[1] = arr[1].replace(";", "");
            jaEncontrado = true;
        }
        if (arr[1].matches(variaveis) && (!arr[1].equals("varI") && !arr[1].equals("varC")  && !arr[1].equals("varB"))) {
            data.add(tipos.IDENTIFICADOR);
        }//Nome da Variavel inserida
        else {
            invalidName = true;
            System.out.println("Erro lexico encontrado. " + arr[1] + " nao e um nome valido de variavel");
        }
    }

    //Analisa a estrutura lexica inserida
    public void leituraLexico(String frase){

        int identificadorLexico = -1;

        String[] arrOfStr = {};
        try{
            arrOfStr = frase.split(" ", 3);
        }catch(Exception e){
            System.out.println("Erro lexico encontrado. A sentença está mal construida");
        }

        //Para os tipos primitivos
        if(arrOfStr[0].equals("varI")){
            tkn.getDadosLexico().add(tipos.INTEGER); //Tipo da Variavel inserida (int)

            verificarNomeVariavel(arrOfStr, tkn.getDadosLexico());

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado && !invalidName) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    //Tratamento caso o valor atribuido seja vazio
                    try {
                        //Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace(": ", "");
                        //Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(numeros)) {
                            tkn.getDadosLexico().add(arrOfStr[2]);
                        } //Valor da Variavel inserida
                        else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varI");
                        }
                    } catch (Exception e) {
                        tkn.getDadosLexico().add(tipos.VAZIO);
                    }
                    tkn.getDadosLexico().add(tipos.EOI);
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{
                if(!invalidName){ tkn.getDadosLexico().add(tipos.VAZIO); tkn.getDadosLexico().add(tipos.EOI); }
            }

            //Atribui definitivamente os tokens
            identificadorLexico = 0;
            tkn.atribuicaoTokens(identificadorLexico);
        }else if(arrOfStr[0].equals("varC")){
            tkn.getDadosLexico().add(tipos.CHAR); //Tipo da Variavel inserida (char)

            verificarNomeVariavel(arrOfStr, tkn.getDadosLexico());

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado && !invalidName) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    //Tratamento caso o valor atribuido seja vazio
                    try {
                        //Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace(": ", "");
                        //Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(caracteres)) {
                            tkn.getDadosLexico().add(arrOfStr[2]);
                        } //Valor da Variavel inserida
                        else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varC");
                        }
                    } catch (Exception e) {
                        tkn.getDadosLexico().add(tipos.VAZIO);
                    }
                    tkn.getDadosLexico().add(tipos.EOI);
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{
                if(!invalidName){ tkn.getDadosLexico().add(tipos.VAZIO); tkn.getDadosLexico().add(tipos.EOI); }
            }

            //Atribui definitivamente os tokens
            identificadorLexico = 0;
            tkn.atribuicaoTokens(identificadorLexico);
        }else if(arrOfStr[0].equals("varB")) {
            boolean erro = false;
            tkn.getDadosLexico().add(tipos.BOOL); //Tipo da Variavel inserida (boolean)

            verificarNomeVariavel(arrOfStr, tkn.getDadosLexico());

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado && !invalidName) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    //Tratamento caso o valor atribuido seja vazio
                    try {
                        //Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace(": ", "");
                        //Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(letras) && (arrOfStr[2].equals("true") || arrOfStr[2].equals("false"))) {
                            tkn.getDadosLexico().add(arrOfStr[2]);
                        } else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varB");
                            erro = true;
                        }
                    } catch (Exception e) {
                        if(erro == false){ tkn.getDadosLexico().add(tipos.VAZIO); }
                    }
                    if(erro == false){ tkn.getDadosLexico().add(tipos.EOI); }
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{
                if(!invalidName){ tkn.getDadosLexico().add(tipos.VAZIO); tkn.getDadosLexico().add(tipos.EOI); }
            }
            //Atribui definitivamente os tokens
            identificadorLexico = 0;
            tkn.atribuicaoTokens(identificadorLexico);
        }

        //Para os laços de controle e repeticao
        else if(arrOfStr[0].contains("if")){
            //Adiciona o tipo a lista de tokens
            tkn.getDadosLexico().add(tipos.IF);

            //Coleta de dados dentro do if
            String[] ifStructure = {};
            try{
                ifStructure = frase.split("\\(", 2);
            }catch(Exception e){
                System.out.println("Erro lexico encontrado. A sentença em if está mal construida");
            }

            //Abstrai os itens e permanece apenas o conteúdo necessário após a identificação do tipo primário do token
            ifStructure[0] = ifStructure[1].replace("if", "");
            ifStructure[1] = ifStructure[1].replace(")", "");

            //Descobre quantas instruções o if possui
            int contador = 0;
            try{
                for (int i=0; i<frase.length(); i++)
                {
                    if (frase.charAt(i) == '&' || frase.charAt(i) == '|') { //Verifica se há existência daquele caracter
                        contador++;
                        i++; //Pula o caracter seguinte, já que o mesmo não deve entrar na contagem 2 vezes
                    }
                }
            }catch (Exception e){
                System.out.println("Erro léxico encontrado na estrutura, verifique o uso de && e/ou ||");
            }

            //Atribui definitivamente os tokens
            identificadorLexico = 1;
            tkn.atribuicaoTokens(identificadorLexico);
        }else if(arrOfStr[0].contains("while")){

            //Atribui definitivamente os tokens
            identificadorLexico = 1;
            tkn.atribuicaoTokens(identificadorLexico);
        }else if(arrOfStr[0].contains("for")){
            //Adiciona o tipo a lista de tokens
            tkn.getDadosLexico().add(tipos.FOR);

            //Coleta de dados dentro do if
            String[] forStructure = {}; //Para o for em si
            String[] forStructureData = {}; //Para as informações que vão na assinatura do for
            String[] forTask = {}; //Para todas as instruções contidas dentro do for

            try{
                forStructure = frase.split("\\(", 2);
            }catch(Exception e){
                System.out.println("Erro lexico encontrado. A sentença em if está mal construida");
            }

            //Abstrai os itens e permanece apenas o conteúdo necessário após a identificação do tipo primário do token
            forStructure[0] = forStructure[1].replace("for", "");
            forStructure[1] = forStructure[1].replace(")", "");

            //Separando e armazenando as instruções
            try{
                forStructureData = frase.split(";", 3);
            }catch(Exception e){
                System.out.println("Erro lexico encontrado. Há um erro nas instruções do for");
            }

            //Separando e armazenando as instruções dentro do for
            try{
                forTask = frase.split("\\{", 21); //Limite de 20 instruções dentro do laço for
            }catch(Exception e){
                System.out.println("Erro lexico encontrado. Há um erro nas tarefas do for");
            }

            //Abstrai a última chave e trata um possível erro do usuário
            try{
                forTask[forTask.length - 1] = forTask[forTask.length - 1].replace("}", "");
            }catch(Exception e){
                System.out.println("Erro lexico encontrado. Encerre o laço for com }");
            }

            //Definindo nomes e fazendo o for funcionar, tratar <, > e == (Obs: tem que ter várias possibilidades de acordo com o forStructureData[1] que for recebido)
            //Se o for contiver <
            //Se o for contiver >
            //Se o for contiver ==

            //Atribui definitivamente os tokens
            identificadorLexico = 1;
            tkn.atribuicaoTokens(identificadorLexico);
        }

        //Exibição do resultado desejado
        try{
            System.out.println("$Fenix => " + arrOfStr[1] + " = " + arrOfStr[2]);
        }catch(Exception e){
            System.out.println("$Fenix => " + arrOfStr[1] + " = " + tipos.VAZIO);
        }
    }
}