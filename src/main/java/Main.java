package main.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static double MIN_T_INTER = 0.05;
    private static double MAX_T_INTER = 0.5;
    private static double MIN_T_SERV = 0.05;
    private static double MAX_T_SERV = 0.5;
    private static int MAX_LLEGADAS = 100;
    private static int NUM_PROCESADOR = 1;
    private static int TAMAÑO_COLA = 4; 

    private static void processConfigFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("=");

                if (parts.length != 2) {
                    System.out.println("Formato inválido en la línea: " + line);
                    continue;
                }

                String key = parts[0].trim();
                String value = parts[1].trim();
                System.out.println(value);

                try {
                    double doubValue = Double.parseDouble(value);
                    switch (key) {
                        case "MAX_T_INTER":
                            MAX_T_INTER = doubValue;
                            break;
                        case "MIN_T_INTER":
                            MIN_T_INTER = doubValue;
                            break;
                        case "MAX_T_SERV":
                            MAX_T_SERV = doubValue;
                            break;
                        case "MIN_T_SERV":
                            MIN_T_SERV = doubValue;
                            break;
                        case "MAX_LLEGADAS":
                            MAX_LLEGADAS = (int) doubValue;
                            break;
                        case "NUM_PROCESADOR":
                            NUM_PROCESADOR = (int) doubValue;
                            break;
                        case "TAMAÑO_COLA":
                            TAMAÑO_COLA = (int) doubValue;
                            break;
                        default:
                            System.out.println("Parametro " + key + "desconocido");
                            break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("El parámetro '" + key + "' no es un número entero: " + value);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        if(args.length > 0){
            processConfigFile(args[0]);
        }

        System.out.println("----------------------------------------------");
        System.out.println("Parametros de la simulacion: ");
        System.out.println("----------------------------------------------");
        System.out.println("");
        System.out.println("+ Parametros de la fuente");
        System.out.println("    MIN_T_INTER: " + MIN_T_INTER);
        System.out.println("    MAX_T_INTER: " + MAX_T_INTER);
        System.out.println("    MIN_T_SERV: " + MIN_T_SERV);
        System.out.println("    MAX_T_SERV: " + MAX_T_SERV);
        System.out.println("    MAX_LLEGADAS: " + MAX_LLEGADAS);
        System.out.println("");
        System.out.println("+ Parametros del CPD");
        System.out.println("    NUM_PROCESADOR: " + NUM_PROCESADOR);
        System.out.println("    TAMAÑO_COLA: " + TAMAÑO_COLA);
        System.out.println("\n");

        
        Fuente fuente = new Fuente(MIN_T_INTER,MAX_T_INTER,MIN_T_SERV,MAX_T_SERV,MAX_LLEGADAS);
        CPD cpd = new CPD(NUM_PROCESADOR, TAMAÑO_COLA);
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