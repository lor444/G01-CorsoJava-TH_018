package it.corsojava.parallel;

public class Main {

    public static void main(String[] args) {

        char[] simboli = new char[]{'A','B','C','D','E'};

        for (char simbolo : simboli) {
            Catapulta c = new Catapulta(simbolo, 7);
            Thread t = new Thread(c);
            t.start();
        }
    }

}

































/*

char[] simboli = new char[]{'A','B','C','D','E'};
        for (char simbolo : simboli) {
            Catapulta c = new Catapulta(simbolo, 5);
            c.eseguiLanci();
        }
 */