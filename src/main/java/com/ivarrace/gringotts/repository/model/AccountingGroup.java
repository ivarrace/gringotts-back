package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * {
 *  *         id: "coche",
 *  *         creationDate: "01/01/1970",
 *  *         name: "Coche",
 *  *         accountingCategories: [
 *  *           {
 *  *             id: "gasolina-coche",
 *  *             creationDate: "01/01/1970",
 *  *             name: "Gasolina",
 *  *             movimientos: [
 *  *               {
 *  *                 fecha: "01/01/1970",
 *  *                 cantidad: 100.00,
 *  *                 info: "3L de gasolina",
 *  *               }
 *  *             ]
 *  *           },
 *  *         ],
 *  *       },
 */
public class AccountingGroup {

    private String id;
    @CreatedDate
    private Date createdDate;
    private String name;
    private List<AccountingCategory> categories = Collections.emptyList();

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

    public List<AccountingCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<AccountingCategory> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
