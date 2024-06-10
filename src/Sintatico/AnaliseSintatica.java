package Sintatico;

import Arvore.No;
import Lexicos.tipos;
import java.util.ArrayList;

public class AnaliseSintatica {

    No raiz = new No('c');

    // CONSTRUTOR
    public AnaliseSintatica(){
        
    }

    // Cria arvore sintatica para 1 variavel apenas
    public boolean criaArv(ArrayList tkn){
        boolean asValida = true;
        for (int i = 0; i < tkn.size(); i++) {
            if (i % 2 == 0) {
                // Se o token 0, 2, 4, 6... for DIG         5+5+3       + * 2 3 -
                if (tkn.get(i) == tipos.DIG) {  
                    No filho = new No('I');
                    raiz.setFilho(filho, i);
                } else {
                    asValida = false;
                }
            } else {
                // Se o token 1, 3, 5... for MAIS, MENOS, VEZES, DIVIDIDO
                if (tkn.get(i) == tipos.MAIS) {
                    No filho = new No('+');
                    raiz.setFilho(filho, i);
                } else if (tkn.get(i) == tipos.MENOS) {
                    No filho = new No('-');
                    raiz.setFilho(filho, i);
                } else if(tkn.get(i) == tipos.VEZES) {
                    No filho = new No('*');
                    raiz.setFilho(filho, i);
                } else if (tkn.get(i) == tipos.DIVIDIDO) {
                    No filho = new No('/');
                    raiz.setFilho(filho, i);
                } else {
                    asValida = false;
                }
            }
        }
        return asValida;
    }
}