package it.corsojava.parallel;

public class CJTools {

    public static long randomNumber(long min, long max){
        return (long)(Math.random()*(max-min))+min;
    }

    public static int randomNumber(int min, int max){
        return (int)(Math.random()*(max-min))+min;
    }

    public static double randomNumber(double min, double max){
        return (Math.random()*(max-min))+min;
    }

}
