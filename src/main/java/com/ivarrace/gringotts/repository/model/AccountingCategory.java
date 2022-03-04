package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * {
 *  *  *             id: "gasolina-coche",
 *  *  *             creationDate: "01/01/1970",
 *  *  *             name: "Gasolina",
 *  *  *             records: [
 *  *  *               {
 *  *  *                 fecha: "01/01/1970",
 *  *  *                 cantidad: 100.00,
 *  *  *                 info: "3L de gasolina",
 *  *  *               }
 *  *  *             ]
 *  *  *           },
 */

@Document(collection = "categorias")
public class AccountingCategory {

    @Id
    private String id;
    @CreatedDate
    private Date createdDate;
    private String name;
    private List<Records> records = Collections.emptyList();;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }
}
