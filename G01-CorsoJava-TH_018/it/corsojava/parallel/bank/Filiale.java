package it.corsojava.parallel.bank;

import it.corsojava.parallel.CJTools;

public class Filiale  extends Thread{

    private Banca banca;
    private String nome;

    public Filiale (String nome, Banca banca){
        this.nome =nome;
        this.banca = banca;
    }

    public void run(){
        System.out.println("Filiale "+this.nome +" ha avviato l'attivita' ");
        while(getBanca().isRunning()) {
            Conto sorgente = this.getBanca().getContoCasuale(null);
            Conto destinazione = this.getBanca().getContoCasuale(sorgente);
            int importo = CJTools.randomNumber(1, this.getBanca().getMaxImportoMovimento());

            this.getBanca().trasferisci(sorgente, destinazione, importo);
            // ------------------------------------------------
            try {
                Thread.sleep(this.getBanca().getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    //-------------------------------------

    public Banca getBanca() {
        return banca;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }

    public String getNome() {
        return nome;
    }

    public void setId(String id) {
        this.nome = id;
    }
}

