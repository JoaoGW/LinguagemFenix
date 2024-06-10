import Lexicos.Lexical;
import Lexicos.Tokens;
import Semantica.AcaoSemantica;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        //Instanciação de itens basicos para o funcionamento do compilador
        Lexical lex = new Lexical();
        AcaoSemantica av;

        boolean exitPrompt = false; //Finaliza o prompt e não aceita mais comandos de instruções no console de reprodução

        //Exibição da interface básica do compilador no estilo do jShell
        Scanner leitor = new Scanner(System.in);
        System.out.print("<Compilador Fenix> ");
        String frase = leitor.nextLine();

        //Retoma e prepara para receber um novo comando no prompt, é possível finalizá-lo inserindo o comando exit
        do{
            if(!frase.equals("exit")){
                //Verificando se é necessária uma análise sintática na instrução
                if(frase.contains("+") || frase.contains("-") || frase.contains("*") || frase.contains("/")) {
                    //Prepara a String para receber uma equação e faz seu cálculo na regra do Sintático
                    String[] afterEqual = {};
                    afterEqual = frase.split(":");

                    afterEqual[1] = afterEqual[1].replaceAll(" ", "");

                    //Faz a leitura léxica já pronta no sintático e gera seus tokens
                    lex.leituraLexico(frase);

                    //Libera o prompt para receber novas instruções
                    System.out.println();
                    System.out.print("<Compilador Fenix> ");
                    frase = leitor.nextLine();
                }else{
                    lex.leituraLexico(frase);
                    //System.out.println(lex.toString());
                    System.out.println();
                    System.out.print("<Compilador Fenix> ");
                    frase = leitor.nextLine();
                }
            }else{
                exitPrompt = true;
            }
        }while(!exitPrompt);
    }
}