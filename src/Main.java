import java.util.Scanner;

import Lexicos.Lexical;
import BinaryTree.Arvore;

public class Main{
    public static void main(String[] args){
        //Instanciação de itens basicos para o funcionamento do compilador
        Lexical lex = new Lexical();
        Arvore av;

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
                    afterEqual = frase.split("(?<==)");
                    afterEqual[1] = afterEqual[1].replaceAll(" ", "");
                    System.out.println("Sobra: " + afterEqual[1]);
                    av = new Arvore(afterEqual[1]);

                    //Pega o resultado do sintático e o substitui no lexema para fazer uma instrução adequada para os tokens
                    int resultado = av.getResultado();
                    afterEqual[1].replace(afterEqual[1], Integer.toString(resultado));
                    frase = afterEqual[0] + afterEqual[1];

                    //Faz a leitura léxica já pronta no sintático e gera seus tokens
                    lex.leituraLexico(frase);

                    //Libera o prompt para receber novas instruções
                    System.out.println();
                    System.out.print("<Compilador Fenix> ");
                    frase = leitor.nextLine();
                }else{
                    lex.leituraLexico(frase);
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
