package main.java;

public class Peticion extends Data{
    private double instante_peticion;
    private double tiempo_servicio;

    public Peticion(){

    }
    public Peticion(double instante_peticion, double tiempo_servicio){
        this.instante_peticion = instante_peticion;
        this.tiempo_servicio = tiempo_servicio;
    }

    public double getInstante_peticion() {
        return instante_peticion;
    }
    public double getTiempo_servicio() {
        return tiempo_servicio;
    }

    public void setInstante_peticion(double instante_peticion) {
        this.instante_peticion = instante_peticion;
    }
    public void setTiempo_servicio(double tiempo_servicio) {
        this.tiempo_servicio = tiempo_servicio;
    }
}
