package BinaryTree;

public class Arvore {

    Nos raiz;

    // CONSTRUTOR
    public Arvore(String sRec){

        // passa tds os chars pra um array de Nos
        // se for Mais, Menos, Mult, Div
        //      pega No anterior e posterior e passa como Esq Dir
        //

        Nos[] arrayNos = new Nos[50];
        int j=0;

        // PASSA TUDO PRA NOS
        for(int i=0; i < sRec.length(); i++){
            int valor = 0;
            // se for numerico
            if (sRec.charAt(i) >= 48 && sRec.charAt(i) <= 57){
                while (i < sRec.length() &&  sRec.charAt(i) >= 48 && sRec.charAt(i) <= 57){
                    // soma ao valor anterior e pula 1 pra frente
                    valor = valor * 10 + sRec.charAt(i)-48;
                    i++;
                }
                Nos novo = new Nos('N');
                novo.setValor(valor);
                novo.setId(j);
                arrayNos[j] = novo;
                j++;
                i--;
                //System.out.println("Criado no[" + (j-1) + "] numerico de valor " + valor);
            }
            // se for operador
            else if (sRec.charAt(i) == '*' || sRec.charAt(i) == '/' || sRec.charAt(i) == '+' || sRec.charAt(i) == '-'){

                Nos novo = new Nos(sRec.charAt(i));
                novo.setId(j);
                arrayNos[j] = novo;
                j++;
                //System.out.println("Criado no[" + (j-1) + "] do tipo " + sRec.charAt(i) + "");
            }
            else if (sRec.charAt(i) == ';'){}
            // se n for nenhum deles
            else{
                System.out.println("VALOR " + sRec.charAt(i) + " NAO EH VALIDO");
            }
        }


        // ASSOCIA OS VALORES DA ESQ E DA DIREITA DAS MULT E DIVIS
        for(int i=0; arrayNos[i] != null; i++){
            if(arrayNos[i].getTipo() == '*' || arrayNos[i].getTipo() == '/' ){
                // define o valor desse No
                if (arrayNos[i].getTipo() == '*'){
                    arrayNos[i].valor = arrayNos[i-1].getValor() * arrayNos[i+1].getValor();
                } else{
                    arrayNos[i].valor = arrayNos[i-1].getValor() / arrayNos[i+1].getValor();
                }
                // associa os valores da esq e dir
                arrayNos[i].setEsq(arrayNos[i-1]);
                arrayNos[i].setDir(arrayNos[i+1]);
                // tira o No esq e dir do array e move todo o restante array 2 pra esq
                arrayNos[i-1] = arrayNos[i];
                int k=i;
                while(arrayNos[k+2] == null){
                    arrayNos[k] = arrayNos[k+2];
                    k++;
                }
            }
        }

        // ASSOCIA OS VALORES DA ESQ E DA DIREITA DAS SOMA E SUB
        for(int i=0; arrayNos[i] != null; i++){
            if(arrayNos[i].getTipo() == '+' || arrayNos[i].getTipo() == '-' ){
                // define o valor desse No
                if (arrayNos[i].getTipo() == '+'){
                    arrayNos[i].valor = arrayNos[i-1].getValor() + arrayNos[i+1].getValor();
                } else{
                    arrayNos[i].valor = arrayNos[i-1].getValor() - arrayNos[i+1].getValor();
                }
                // associa os valores da esq e dir
                arrayNos[i].setEsq(arrayNos[i-1]);
                arrayNos[i].setDir(arrayNos[i+1]);
                // tira o No esq e dir do array e move todo o restante array 2 pra esq
                arrayNos[i-1] = arrayNos[i];
                int k=i;
                while(k < 48 && arrayNos[k+2] == null){
                    arrayNos[k] = arrayNos[k+2];
                    k++;
                }
            }
        }
        // define qual o no raiz
        raiz = arrayNos[0];
    }

    // FAZ O PRINT DA ARVORE
    public void imprimeArvore(){
        imprimeNos(raiz);
    }

    // RETORNA O RESULTADO FINAL
    public int getResultado(){
        return raiz.getValor();
    }

    // FAZ O PRINT DOS NOS
    public void imprimeNos(Nos temp){
        System.out.println(' ');
        System.out.print("No[" + temp.getId() + "]: " + temp.getTipo() + " " + temp.getValor());
        // imprime no esq caso exista
        if (temp.getEsq() != null){
            System.out.print(" | Esq[" + temp.getEsq().getId() + "]: " + temp.getEsq().getTipo() + " " + temp.getEsq().getValor());
        }
        // imprime no dir caso exista
        if (temp.getDir() != null){
            System.out.print(" | Dir[" + temp.getDir().getId() + "]: " + temp.getDir().getTipo() + " " + temp.getDir().getValor());
            imprimeNos(temp = temp.getDir());
        }

        if (temp.getEsq() != null){
            imprimeNos(temp = temp.getEsq());
        }
        if (temp.getDir() != null){
            imprimeNos(temp = temp.getDir());
        }


    }
}