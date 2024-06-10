package Lexicos;

import Sintatico.AnaliseSintatica;
import java.util.ArrayList;
import Semantica.AcaoSemantica;

public class Lexical {

    // Definicoes Regulares
    String numeros = "(\\d)*";
    String letras = "[a-zA-Z]+";
    String caracteres = "[a-zA-Z]";
    String variaveis = "([a-zA-Z]*[0-9]*)+";

    // Variaveis de controle
    boolean jaEncontrado = false; // Verificador se a variavel possui um valor vazio atribuido a ele e contem somente o nome
    boolean invalidName = false; // Verificador se alguma anormalidade de nomeclatura ocorreu

    Tokens tkn = new Tokens();
    AnaliseSintatica as = new AnaliseSintatica();

    // Fazendo a verificação do nome atribuido a variavel
    public void verificarNomeVariavel(String[] arr, ArrayList<Object> data) {
        if (arr[1].indexOf(';') != -1) {
            arr[1] = arr[1].replace(";", "");
            jaEncontrado = true;
        }
        if (arr[1].matches(variaveis) && (!arr[1].equals("varI") && !arr[1].equals("varC") && !arr[1].equals("varB"))) {
            data.add(tipos.IDENTIFICADOR);
        } // Nome da Variavel inserida
        else {
            invalidName = true;
            System.out.println("Erro lexico encontrado. " + arr[1] + " nao e um nome valido de variavel");
        }
    }

    // Analisa a estrutura lexica inserida
    public ArrayList leituraLexico(String frase) {
        String[] arrOfStr = {};
        try {
            arrOfStr = frase.split(" ", 3);
        } catch (Exception e) {
            System.out.println("Erro lexico encontrado. A sentença está mal construida");
        }

        // Para os tipos primitivos
        if(arrOfStr[0].equals("varI")){
            tkn.getDadosLexico().add(tipos.INTEGER); //Tipo da Variavel inserida (int)

            verificarNomeVariavel(arrOfStr, tkn.getDadosLexico());

            //Verificação do valor atribuido a variavel em seu respectivo tipo
            if(!jaEncontrado && !invalidName) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    //Removendo sinais que não servem como tokens
                    arrOfStr[2] = arrOfStr[2].replace(": ", "");
                    arrOfStr[2] = arrOfStr[2].replace(" ", "");
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    
                    //Tratamento caso o valor atribuido seja vazio
                    //Fazendo a verificação do valor atribuido a variavel
                    if (arrOfStr[2].matches(numeros)) {
                        tkn.getDadosLexico().add(tipos.DIG);
                    } //Valor da Variavel inserida
                    else {
                        try {
                            if(arrOfStr[2].contains("+") || arrOfStr[2].contains("-") || arrOfStr[2].contains("*") || arrOfStr[2].contains("/")){
                                ArrayList<tipos> analise = new ArrayList<tipos>();
                                for(int i = 0; i < arrOfStr[2].length(); i++){
                                    if(String.valueOf(arrOfStr[2].charAt(i)).matches(numeros)){ analise.add(tipos.DIG); }
                                    else{
                                        if(String.valueOf(arrOfStr[2].charAt(i)).equals("+")){ analise.add(tipos.MAIS); }
                                        else if(String.valueOf(arrOfStr[2].charAt(i)).equals("-")){ analise.add(tipos.MENOS); }
                                        else if(String.valueOf(arrOfStr[2].charAt(i)).equals("*")){ analise.add(tipos.VEZES); }
                                        else if(String.valueOf(arrOfStr[2].charAt(i)).equals("/")){ analise.add(tipos.DIVIDIDO); }
                                    }
                                }
                                boolean aceito = as.criaArv(analise);
                                if(aceito){ 
                                    AcaoSemantica acao = new AcaoSemantica(arrOfStr[2]); 
                                    tkn.getDadosLexico().add(tipos.DIG);
                                    arrOfStr[2] = String.valueOf(acao.getResultado());
                                }
                            }else{
                                System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varI");
                            }
                        } catch (Exception e) {
                            tkn.getDadosLexico().add(tipos.VAZIO);
                        }
                    }
                    tkn.getDadosLexico().add(tipos.EOI);                    
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            }else{
                if(!invalidName){ tkn.getDadosLexico().add(tipos.VAZIO); tkn.getDadosLexico().add(tipos.EOI); }
            }

            //Atribui definitivamente os tokens
            tkn.atribuicaoTokens();
            tkn.getLexico().clear();
        } else if (arrOfStr[0].equals("varC")) {
            tkn.getDadosLexico().add(tipos.CHAR); // Tipo da Variavel inserida (char)

            verificarNomeVariavel(arrOfStr, tkn.getDadosLexico());

            // Verificação do valor atribuido a variavel em seu respectivo tipo
            if (!jaEncontrado && !invalidName) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    // Tratamento caso o valor atribuido seja vazio
                    try {
                        // Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace(": ", "");
                        // Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(caracteres)) {
                            tkn.getDadosLexico().add(tipos.CARACTER);
                        } // Valor da Variavel inserida
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
            } else {
                if (!invalidName) {
                    tkn.getDadosLexico().add(tipos.VAZIO);
                    tkn.getDadosLexico().add(tipos.EOI);
                }
            }
            // Atribui definitivamente os tokens
            tkn.atribuicaoTokens();
            tkn.getLexico().clear();
        } else if (arrOfStr[0].equals("varB")) {
            boolean erro = false;
            tkn.getDadosLexico().add(tipos.BOOL); // Tipo da Variavel inserida (boolean)

            verificarNomeVariavel(arrOfStr, tkn.getDadosLexico());

            // Verificação do valor atribuido a variavel em seu respectivo tipo
            if (!jaEncontrado && !invalidName) {
                if (arrOfStr[2].indexOf(';') != -1) {
                    arrOfStr[2] = arrOfStr[2].replace(";", "");
                    // Tratamento caso o valor atribuido seja vazio
                    try {
                        // Removendo sinais que não servem como tokens
                        arrOfStr[2] = arrOfStr[2].replace(": ", "");
                        // Fazendo a verificação do valor atribuido a variavel
                        if (arrOfStr[2].matches(letras) && (arrOfStr[2].equals("true") || arrOfStr[2].equals("false"))) {
                            if(arrOfStr.equals("true")){ tkn.getDadosLexico().add(tipos.TRUE); }
                            else{ tkn.getDadosLexico().add(tipos.FALSE); }
                        } else {
                            System.out.println("Erro lexico encontrado. " + arrOfStr[2] + " nao e um valor valido para varB");
                            erro = true;
                        }
                    } catch (Exception e) {
                        if (erro == false) {
                            tkn.getDadosLexico().add(tipos.VAZIO);
                        }
                    }
                    if (erro == false) {
                        tkn.getDadosLexico().add(tipos.EOI);
                    }
                } else {
                    System.out.println("Erro lexico encontrado. Não foi encontrado um EOI ';' na instrução");
                }
            } else {
                if (!invalidName) {
                    tkn.getDadosLexico().add(tipos.VAZIO);
                    tkn.getDadosLexico().add(tipos.EOI);
                }
            }
            // Atribui definitivamente os tokens
            tkn.atribuicaoTokens();
            tkn.getLexico().clear();
        }

        // Atribui definitivamente os tokens
        tkn.atribuicaoTokens();

        // Exibição do resultado desejado
        try {
            System.out.println("$Fenix => " + arrOfStr[1] + " = " + arrOfStr[2]);
        } catch (Exception e) {
            System.out.println("$Fenix => " + arrOfStr[1] + " = " + tipos.VAZIO);
        }

        // as.criaArv(tkn.getDadosLexico());

        return tkn.getDadosLexico();
    }
}