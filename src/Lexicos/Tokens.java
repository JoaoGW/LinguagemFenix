package Lexicos;

import java.util.ArrayList;

public class Tokens {
    //Estruturas que contem os dados do lexico
    ArrayList<ArrayList> todosLexemas = new ArrayList<ArrayList>(); //Armazena todos os array lists com tokens
    ArrayList<ArrayList> lexico = new ArrayList<ArrayList>(); //O que armazena os dois resultados, com EOI e EOF no final
    ArrayList<Object> dadosLexico = new ArrayList<Object>(); //O que contem a lista real identificando os tokens e o EOI
    ArrayList<Object> finalLexico = new ArrayList<Object>(); //O que contem a formalidade final do EOF com a contagem de tokens

    //Controladores da classe
    int controle = 0;

    //Mostra a lista com todos os tokens atuais
    public void mostrarTokens(ArrayList al){
        int tam = al.size(); //Captura o tamanho total da lista atual de tokens

        for(int i = 0; i < tam ; i++){
            System.out.print(al.get(i) + " ");
        }
        System.out.println();

        controle += 1;
    }

    public ArrayList atribuicaoTokens(){
        finalLexico.add("tokens = " + dadosLexico.size());
        finalLexico.add(tipos.EOF);

        // Adiciona a lista de tokens e as informacoes estruturais a estrutura de armazenamento de compilação
        lexico.add(dadosLexico);
        lexico.add(finalLexico);
        mostrarTokens(lexico); 

        todosLexemas.add(lexico); 

        dadosLexico.clear();
        finalLexico.clear();

        return lexico;
    }

    public ArrayList getTodosLexemas(){
        return this.todosLexemas;
    }

    public ArrayList getDadosLexico(){
        return this.dadosLexico;
    }

    public ArrayList getLexico(){
        return this.lexico;
    }        
}