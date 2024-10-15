package main.java;

import java.util.Random;
import java.util.ArrayList;

public class Fuente implements Salida, Entrada{
    private final double MIN_T_INTER;
    private final double MAX_T_INTER;
    private final double MIN_T_SERV;
    private final double MAX_T_SERV;
    private final int MAX_LLEGADAS;
    private ArrayList<Entrada> output = new ArrayList<>();
    private int llegadas = 0;

    public Fuente(double min_t_inter, double max_t_inter, double min_t_serv, double max_t_serv, int max_llegadas){
        this.MIN_T_INTER = min_t_inter;
        this.MAX_T_INTER = max_t_inter;
        this.MIN_T_SERV = min_t_serv;
        this.MAX_T_SERV = max_t_serv;
        this.MAX_LLEGADAS = max_llegadas;
    }
    public void crearPeticion(){
        if(llegadas >= MAX_LLEGADAS){
            return;
        }
        llegadas ++;
        Random random = new Random();
        double instante_peticion;
        double tiempo_servicio;
        Peticion peticion = new Peticion();

        instante_peticion = random.nextDouble(MIN_T_INTER,MAX_T_INTER);
        tiempo_servicio = random.nextDouble(MIN_T_SERV, MAX_T_SERV);
        peticion.setInstante_peticion(instante_peticion);
        peticion.setTiempo_servicio(tiempo_servicio);
        for (Entrada e : output) {
            e.enviar(peticion);
        }
    }

    public void run() {
        llegadas = 0;
        crearPeticion();
    }

    @Override
    public void conectar(Entrada entrada) {
        this.output.add(entrada);
    }
    @Override
    public void enviar(Data data) {
        crearPeticion();
    }
}
