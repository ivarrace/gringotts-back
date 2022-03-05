package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * {
 *  *  *  *                 fecha: "01/01/1970",
 *  *  *  *                 cantidad: 100.00,
 *  *  *  *                 info: "3L de gasolina",
 *  *  *  *               }
 */
@Document(collection = "movimientos")
public class Record {

    @Id
    private String id;
    private Date fecha;
    private double cantidad;
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
