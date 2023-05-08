package it.corsojava.parallel;

public class Catapulta implements Runnable {

    public static final long MAX_DELAY_TIME=750;

    private char icon;
    private int colpi;
    private int nrLancio=0;

    public Catapulta(char icon,int colpi){
        this.icon=icon;
        this.colpi=colpi;
    }

    /**
     * Esempio:    Catapulta X: lancio 1 -------------------- X
     * Esempio:    Catapulta X: lancio 2 ---------- X
     * Esempio:    Catapulta X: lancio 3 --------------------------------- X
     */
    private void lancia(){
        nrLancio++;
        int distanza=CJTools.randomNumber(1,100);
        String gittata = "-".repeat(distanza);
        String intro="Catapulta "+icon+": lancio "+nrLancio+" ";
        System.out.println(intro+gittata+icon);
    }

    public void run(){
        for(int i = 0; i<colpi ; i++) {
            lancia();
            pausa();
        }
    }

    private void pausa(){
        try {
            long delay=CJTools.randomNumber(0L,MAX_DELAY_TIME);
            Thread.sleep(delay);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

}


