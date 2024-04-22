import java.util.Scanner;

import Lexicos.Lexical;

public class Main{
    public static void main(String[] args){
        Lexical lex = new Lexical();

        Scanner leitor = new Scanner(System.in);
        System.out.print("<Compilador Fenix> ");

        String frase = leitor.nextLine();

        lex.leituraLexico(frase);
    }
}
