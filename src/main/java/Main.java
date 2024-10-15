package main.java;

public class Main {
    public static void main(String[] args) {

        final double MIN_T_INTER = 0.05;
        final double MAX_T_INTER = 0.5;
        final double MIN_T_SERV = 0.05;
        final double MAX_T_SERV = 0.5;
        final int MAX_LLEGADAS = 10;

        Fuente fuente = new Fuente(MIN_T_INTER,MAX_T_INTER,MIN_T_SERV,MAX_T_SERV,MAX_LLEGADAS);
        CPD cpd = new CPD();
        Sumidero sumidero = new Sumidero();
        Thread threadFuente = new Thread(fuente);
        Thread threadCPD = new Thread(cpd);
        Thread threadSumidero = new Thread(sumidero);
        
        fuente.conectar(cpd);
        fuente.conectar(sumidero);
        cpd.conectar(sumidero);
        cpd.conectar(fuente);

        threadFuente.start();
        threadCPD.start();
        threadSumidero.start();
    }
}