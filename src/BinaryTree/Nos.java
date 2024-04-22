package BinaryTree;

public class Nos {

    int valor;
    char tipo;
    int id;
    Nos esq;
    Nos dir;

    // CONSTRUTOR
    public Nos(char tipoR)
    {
        tipo = tipoR;
    }

    // SETTERS
    public void setEsq(Nos sla){
        this.esq = sla;
    }
    public void setDir(Nos sla){
        this.dir = sla;
    }
    public void setValor(int sla){
        this.valor = sla;
    }
    public void setId(int sla){
        this.id = sla;
    }

    // GETTERS
    public Nos getEsq(){
        return this.esq;
    }
    public Nos getDir(){
        return this.dir;
    }
    public char getTipo(){
        return this.tipo;
    }
    public int getValor(){
        return this.valor;
    }

    public int getId(){
        return this.id;
    }
}