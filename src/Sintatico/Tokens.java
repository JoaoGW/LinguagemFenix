package Sintatico;

import Lexicos.tipos;

import java.util.ArrayList;

public class Tokens {
    //Estruturas que contem os dados do lexico
    ArrayList<ArrayList> lexico = new ArrayList<ArrayList>(); //O que armazena os dois resultados abaixo
    ArrayList<Object> dadosLexico = new ArrayList<Object>(); //O que contem a lista real identificando os tokens e o EOI
    ArrayList<Object> finalLexico = new ArrayList<Object>(); //O que contem a formalidade final do EOF com a contagem de tokens

    //Controladores da classe
    int controle = 0;

    public ArrayList atribuicaoTokens(int idLexico){
        switch (idLexico){
            case 0:
                //Calculo dos tokens caso tenha ou não tenha um valor atribuido a ele
                if(dadosLexico.get(2) == tipos.VAZIO){
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

            case 1:
        }

        mostrarTokens(lexico);

        return lexico;
    }

    //Mostra a lista com todos os tokens atuais
    public void mostrarTokens(ArrayList al){
        int tam = al.size(); //Captura o tamanho total da lista atual de tokens

        for(int i = controle; i <= tam - 1; i++){
            System.out.print(al.get(i) + " ");
        }
        System.out.println();

        controle += 1;
    }

    public ArrayList getDadosLexico(){
        return this.dadosLexico;
    }
}