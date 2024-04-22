package Lexicos;

public enum tipos {
    //Tokens para estruturas arquiteturais
    EOF, //Para indicar o fim
    IF, //Para indicar if
    EOI, //Para indicar ponto e vírgula
    WHILE, //Para indicar o laço while
    FOR, //Para indicar o laço for

    //Tokens para tipos
    INTEGER, //Para valores do tipo inteiro
    BOOL, //Para valores do tipo booleano
    CHAR, //Para valores do tipo caracter
    VAZIO //Para quando não atribuir nenhum valor a variavel
}
