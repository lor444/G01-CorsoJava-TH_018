package it.corsojava.parallel.bank;

public class BancaNoFido extends Banca{

    public BancaNoFido(int nrConti, int nrFiliali, int saldoInizialeConto, int maxImportoMovimento, int delay) {
        super(nrConti, nrFiliali, saldoInizialeConto, maxImportoMovimento, delay);
    }
    private int waitCount=0;
    @Override
    public synchronized void trasferisci(Conto sorgente, Conto destinazione, int importo) {
        try {
            while (sorgente.getSaldo() < importo) {
                if(waitCount>10){
                    System.out.println("Trasferimento da "+sorgente.getId()+" a "+destinazione.getId()+" per " +importo +" annullato per mancanza di fondi");
                    return;
                }
                waitCount++;
                wait(3000);
            }
        }catch (InterruptedException ioe){
            System.out.println("Trasferimento annullato per mancanza di fondi");
            return;
        }
        sorgente.preleva(importo);
        destinazione.versa(importo);
        transferCount++;
        waitCount=0;
        notifyAll();
        if(transferCount % checkEveryNrMovements == 0){
            System.out.println("Saldo dopo "+transferCount+" movimenti: "+getSaldoComplessivo());
            checkAccounts();
        }
    }
}

