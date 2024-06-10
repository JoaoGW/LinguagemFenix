package Arvore;

import Lexicos.tipos;

public class No {

    int valor;
    char tipo;
    int id;
    // No esq;
    // No dir;
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

    // SETTERS
    // public void setEsq(No valorRec){
    //     this.esq = valorRec;
    // }
    // public void setDir(No valorRec){
    //     this.dir = valorRec;
    // }
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

    // GETTERS
    // public No getEsq(){
    //     return this.esq;
    // }
    // public No getDir(){
    //     return this.dir;
    // }
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