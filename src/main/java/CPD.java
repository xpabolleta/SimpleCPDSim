package main.java;

import java.util.ArrayList;
import java.util.Iterator;

public class CPD implements Entrada, Salida, Runnable{
    private ArrayList<Entrada> output = new ArrayList<>();
    private ArrayList<Data> input = new ArrayList<>();
    private ArrayList<ArrayList<Peticion>> colas = new ArrayList<>();
    private double clock = 0;
    private int numeroProcesadores;
    private int tamañoCola;
    private int procesadorActivo = 0;
    private void initialize(){
        clock = 0;
        for(int i = 0;i<numeroProcesadores;i++){
            ArrayList<Peticion> cola = new ArrayList<>();
            colas.add(cola);
        }
    }

    public CPD(){
        initialize();
    }
    public CPD(int numeroProcesadores, int tamañoCola){
        this.numeroProcesadores = numeroProcesadores;
        this.tamañoCola = tamañoCola;
        initialize();
    }

    public int getNumeroProcesadores() {
        return numeroProcesadores;
    }
    public int getTamañoCola() {
        return tamañoCola;
    }
    public double getClock() {
        return clock;
    }

    public void setNumeroProcesadores(int numeroProcesadores) {
        this.numeroProcesadores = numeroProcesadores;
    }
    public void setTamañoCola(int tamañoCola) {
        this.tamañoCola = tamañoCola;
    }
    public void setClock(double clock) {
        this.clock = clock;
    }

    public void procesarPeticion(Data data){
        if( !(data instanceof Peticion) ){
            return;
        }

        if(procesadorActivo >= numeroProcesadores){
            procesadorActivo = 0;
        }

        Peticion peticion = (Peticion) data;
        Respuesta respuesta = new Respuesta();
        ArrayList<Peticion> cola = new ArrayList<>();

        cola = colas.get(procesadorActivo);
        clock += peticion.getInstante_peticion();
        peticion.setInstante_peticion(clock);
        
        Iterator<Peticion> iterador = cola.iterator();
        while (iterador.hasNext()) {
            Peticion p = iterador.next();
            if (clock > p.getInstante_peticion() + p.getTiempo_servicio()) {
                iterador.remove();
            }
        }
        if(cola.size() < tamañoCola){
            cola.add(peticion);
            respuesta.setInstante_peticion(peticion.getInstante_peticion());
            respuesta.setTiempo_servicio(peticion.getTiempo_servicio());
            respuesta.setEstado(true);
        }else{
            respuesta.setInstante_peticion(peticion.getInstante_peticion());
            respuesta.setTiempo_servicio(peticion.getTiempo_servicio());
            respuesta.setEstado(false);
        }
        for (Entrada e : output) {
            e.enviar(respuesta);
        }

        procesadorActivo++;
    }

    @Override
    public void conectar(Entrada entrada) {
        this.output.add(entrada);
    }
    @Override
    public synchronized void enviar(Data data) {
        input.addLast(data);
        notifyAll();
    }
    @Override
    public synchronized void run() {
        while(true){
            if(!input.isEmpty()){
                procesarPeticion(input.get(0));
                input.remove(0);
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
