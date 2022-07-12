package com.example.practicafinal.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservas")
public class ReservaEntity {

    @PrimaryKey(autoGenerate = true)
    protected int idReserva;
    protected String fecha;
    protected String hora;
    protected String pista;


    public ReservaEntity(String date, String hour, String court){
        this.fecha = date;
        this.hora = hour;
        this.pista = court;
    }

    public ReservaEntity() {

    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }
}
