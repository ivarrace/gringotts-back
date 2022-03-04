package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * {
 * id: 1,
 * creationDate: "01/01/1970",
 * lastUpdate: "01/01/1970",
 * name: "Personal",
 * gastos: [
 * {
 * id: "coche",
 * creationDate: "01/01/1970",
 * name: "Coche",
 * categorias: [
 * {
 * id: "gasolina-coche",
 * creationDate: "01/01/1970",
 * name: "Gasolina",
 * movimientos: [
 * {
 * fecha: "01/01/1970",
 * cantidad: 100.00,
 * info: "3L de gasolina",
 * }
 * ]
 * },
 * ],
 * },
 * {
 * id: "casa",
 * creationDate: "01/01/1970",
 * name: "Casa",
 * categorias: [
 * {
 * id: "alquiler",
 * creationDate: "01/01/1970",
 * name: "Alquiler",
 * movimientos: [
 * {
 * fecha: "01/01/1970",
 * cantidad: 120.00,
 * info: "",
 * }
 * ]
 * },
 * {
 * id: "servicios",
 * creationDate: "01/01/1970",
 * name: "Servicios",
 * movimientos: [
 * {
 * fecha: "01/01/1970",
 * cantidad: 100.00,
 * info: "Gas",
 * }
 * ]
 * },
 * ],
 * },
 * ],
 * ingresos:  [
 * {
 * id: "trabajo",
 * creationDate: "01/01/1970",
 * name: "Trabajo",
 * categorias: [
 * {
 * id: "nomina",
 * creationDate: "01/01/1970",
 * name: "Nomina",
 * movimientos: [
 * {
 * fecha: "01/01/1970",
 * cantidad: 100.00,
 * info: "",
 * }
 * ]
 * },
 * ],
 * },
 * ],
 * }
 */

@Document(collection = "presupuestos")
public class Accounting {

    @Id
    private String id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModified;
    private String name;
    private List<AccountingGroup> expenses = Collections.emptyList();
    private List<AccountingGroup> income = Collections.emptyList();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountingGroup> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<AccountingGroup> expenses) {
        this.expenses = expenses;
    }

    public List<AccountingGroup> getIncome() {
        return income;
    }

    public void setIncome(List<AccountingGroup> income) {
        this.income = income;
    }
}