package main.java;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Sumidero implements Salida, Entrada, Runnable{

    private ArrayList<Entrada> output = new ArrayList<>();
    private ArrayList<Data> input = new ArrayList<>();
    private File outputPeticiones;
    private File outputRespuestas;
    private void writeString(File file, String string){
        try {
            FileWriter myWriter = new FileWriter(file,true);
            myWriter.write(string + "\n");
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public Sumidero(){
        try {
            outputPeticiones = new File("output/outputPeticiones.txt");
            outputRespuestas = new File("output/outputRespuestas.txt");

            if(outputPeticiones.exists()){
                outputPeticiones.delete();
            }
            outputPeticiones.createNewFile();

            if(outputRespuestas.exists()){
                outputRespuestas.delete();
            }
            outputRespuestas.createNewFile();
        } catch (Exception e) {
            System.out.println("Error");
        }  
    }

    public void procesarPeticion(Data data){
        if(data instanceof Peticion){
            Peticion peticion = (Peticion) data;
            double instante_peticion = peticion.getInstante_peticion();
            double tiempo_servicio = peticion.getTiempo_servicio();
            System.out.print("Inst. peticion: " + instante_peticion);
            System.out.println(" | T. servicio: " + tiempo_servicio);
            writeString(outputPeticiones,
            "Inst. peticion: " + instante_peticion + 
            " | T. servicio: " + tiempo_servicio);
        }

        else if(data instanceof Respuesta){
            Respuesta respuesta = (Respuesta) data;
            double instante_peticion = respuesta.getInstante_peticion();
            double tiempo_servicio = respuesta.getTiempo_servicio();
            boolean estado = respuesta.isEstado();
            System.out.print("Inst. peticion: " + instante_peticion);
            System.out.print(" | T. servicio: " + tiempo_servicio);
            System.out.println(" | Estado: " + estado);
            writeString(outputRespuestas,
            "Inst. peticion: " + instante_peticion + 
            " | T. servicio: " + tiempo_servicio + 
            " | Estado: " + estado);
        }

        else{
            return;
        }
    }

    @Override
    public void conectar(Entrada entrada) {
        this.output.add(entrada);
    }
    @Override
    public void enviar(Data data) {
        input.add(data);
    }
    @Override
    public void run() {
        while(true){
            if(!input.isEmpty()){
                procesarPeticion(input.get(0));
                input.remove(0);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
