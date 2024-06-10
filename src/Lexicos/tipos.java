package Lexicos;

public enum tipos {
    //Tokens para estruturas arquiteturais
    EOF, //Para indicar o fim
    EOI, //Para indicar ponto e vírgula
    DIG, //Para indicar que um número matemático foi recebido
    MAIS, //Para indicar a presença de + em uma atribuição
    MENOS, //Para indicar a presença de - em uma atribuição
    VEZES, //Para indicar a presença de * em uma atribuição
    DIVIDIDO, //Para indicar a presença de / em uma atribuição
    TRUE, //Para indicar a presença de uma atribuição verídica a uma variavel ou condicao
    FALSE, //Para indicar a presença de uma atribuição falsa a uma variavel ou condicao
    CARACTER, //Para indicar que um caracter foi recebido

    //Tokens para tipos
    INTEGER, //Para valores do tipo inteiro
    BOOL, //Para valores do tipo booleano
    CHAR, //Para valores do tipo caracter
    IDENTIFICADOR, //Reconhece o nome da variavel ali presente
    VAZIO //Para quando não atribuir nenhum valor a variavel
}
