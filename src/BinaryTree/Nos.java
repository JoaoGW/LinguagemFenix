package BinaryTree;

public class Nos {
    static class No
    {
        No esq;
        No dir;
        int valor;

        public No(int value)
        {
            this.valor = value;
        }
    }

    public void inserirNo(No no, int value)
    {
        if (value < no.valor)
        {
            if (no.esq != null)
            {
                inserirNo(no.esq, value);
            } else
            {
                System.out.println("Inserido o valor: " + value + " a esquerda do no" + no.valor);
                no.esq = new No(value);
            }
        }
        else if (value > no.valor)
        {
            if (no.dir != null)
            {
                inserirNo(no.dir, value);
            } else
            {
                System.out.println("Inserido o valor: " + value + " a direita do no" + no.valor);
                no.dir = new No(value);
            }
        }
    }
}

