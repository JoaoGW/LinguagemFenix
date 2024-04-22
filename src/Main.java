import java.util.ArrayList;
import java.util.Scanner;

import Lexicos.Lexical;

public class Main{
    public static void main(String[] args){
        //Instanciação de itens basicos para o funcionamento do compilador
        Lexical lex = new Lexical();

        boolean exitPrompt = false; //

        //Exibição da interface básica do compilador no estilo do jShell
        Scanner leitor = new Scanner(System.in);
        System.out.print("<Compilador Fenix> ");
        String frase = leitor.nextLine();

        //Retoma e prepara para receber um novo comando no prompt
        do{
            if(!frase.equals("exit")){
                lex.leituraLexico(frase);
                System.out.print("<Compilador Fenix> ");
                frase = leitor.nextLine();
            }else{
                exitPrompt = true;
            }
        }while(!exitPrompt);
    }
}
