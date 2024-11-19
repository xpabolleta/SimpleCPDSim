package main.java;

public class Respuesta extends Data{
    private double instante_peticion;
    private double tiempo_servicio;
    boolean estado;

    public Respuesta(){

    }
    public Respuesta(double instante_peticion, double tiempo_servicio, boolean estado){
        this.instante_peticion = instante_peticion;
        this.tiempo_servicio = tiempo_servicio;
        this. estado = estado;
    }

    public double getInstante_peticion() {
        return instante_peticion;
    }
    public double getTiempo_servicio() {
        return tiempo_servicio;
    }
    public boolean isEstado() {
        return estado;
    }

    public void setInstante_peticion(double instante_peticion) {
        this.instante_peticion = instante_peticion;
    }
    public void setTiempo_servicio(double tiempo_servicio) {
        this.tiempo_servicio = tiempo_servicio;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
