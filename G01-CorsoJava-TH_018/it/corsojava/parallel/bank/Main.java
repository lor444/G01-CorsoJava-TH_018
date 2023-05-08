package it.corsojava.parallel.bank;

public class Main {

    public static void main(String[] args) {
        Banca banca = new BancaNoFido(10,10,1000,50,90);
        Thread bancaThread=new Thread(banca);
        bancaThread.start();
    }

}


























// Banca banca = new BancaNoFido(10,10,1000,50,90);