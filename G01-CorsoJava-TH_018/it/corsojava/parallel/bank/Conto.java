package it.corsojava.parallel.bank;

public class Conto{

    String id;
    int saldo;

    public Conto(String id,int saldoIniziale){
        this.id=id;
        this.saldo=saldoIniziale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void versa(int importo){
        this.saldo+=importo;
    }

    public void preleva(int importo){
        this.saldo-=importo;
    }
}