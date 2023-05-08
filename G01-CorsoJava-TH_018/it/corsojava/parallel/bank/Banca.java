package it.corsojava.parallel.bank;

import it.corsojava.parallel.CJTools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banca implements Runnable{

    private int nrConti;
    private int nrFiliali;
    private int saldoInizialeConto;
    private int maxImportoMovimento;
    private int delay;
    protected int checkEveryNrMovements=10;

    protected int transferCount=0;
    private boolean running=false;

    private List<Conto> conti;

    public Banca(int nrConti, int nrFiliali, int saldoInizialeConto, int maxImportoMovimento, int delay) {
        this.nrConti = nrConti;
        this.nrFiliali = nrFiliali;
        this.saldoInizialeConto = saldoInizialeConto;
        this.maxImportoMovimento=maxImportoMovimento;
        this.delay=delay;

        conti=new ArrayList<Conto>();
        for(int i=0;i<nrConti;i++){
            conti.add(new Conto("C"+i,this.saldoInizialeConto));
        }
        System.out.println("Ad apertura filiali il saldo complessivo della banca e' "+getSaldoComplessivo());
    }

    public void run(){
        this.running=true;
        System.out.println("Avvio attività filiali");
        for (int i = 0; i < this.nrFiliali ; i++) {
            Filiale f=new Filiale("F"+i,this);
            f.start();
        }
        System.out.println("Sono state avviate le attività su "+nrFiliali+" filiali");
    }

    public synchronized void trasferisci(Conto sorgente, Conto destinazione, int importo){
        sorgente.preleva(importo);
        destinazione.versa(importo);
        // -------------------------------------
        transferCount++;
        if(transferCount % checkEveryNrMovements == 0){
            System.out.println("Saldo dopo "+transferCount+" movimenti: "+getSaldoComplessivo());
            checkAccounts();
        }
    }

    public void checkAccounts(){
        String accounts=this.conti.stream().filter(c -> c.getSaldo()<0).map(c -> c.getId()).collect(Collectors.joining(", "));
        if(accounts.length()>0)
            System.out.println("Conti con saldo negativo: "+accounts);
    }
    public int getSaldoComplessivo(){
        int sum=this.conti.stream().mapToInt(f -> f.getSaldo()).sum();
        return sum;
    }

    // -------------- GETTERS --------------------
    public Conto getContoCasuale(Conto escluso){
        Conto c = conti.get(CJTools.randomNumber(0,conti.size()));
        if(escluso == null || c.getId().compareTo(escluso.getId()) != 0)
            return c;
        return getContoCasuale(escluso);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSaldoInizialeConto() {
        return saldoInizialeConto;
    }

    public int getMaxImportoMovimento() {
        return maxImportoMovimento;
    }

    public int getDelay() {
        return delay;
    }

}

