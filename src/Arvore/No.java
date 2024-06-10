package Arvore;

import Lexicos.tipos;

public class No {

    int valor;
    char tipo;
    int id;

    No[] filho = new No[3];


    // CONSTRUTOR
    public No(char tipoR)
    {
        tipo = tipoR;
    }

    public No(Object tipo){
        if(tipo.toString().equals("DIG")){
            this.tipo = 'd';
        }

    }

    public void setValor(int valorRec){
        this.valor = valorRec;
    }
    public void setId(int valorRec){
        this.id = valorRec;
    }
    public void setTipo(char valorRec){
        this.tipo = valorRec;
    }
    public void setFilho(No valorRec, int n){
        this.filho[n] = valorRec;
    }

    public char getTipo(){
        return this.tipo;
    }
    public int getValor(){
        return this.valor;
    }
    public int getId(){
        return this.id;
    }
    public No getFilho(int n){
        return this.filho[n];
    }
}