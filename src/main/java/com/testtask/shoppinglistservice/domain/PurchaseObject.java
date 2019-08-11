package com.testtask.shoppinglistservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Calendar;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class PurchaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String category;
    private Date purchaseDate;

    public PurchaseObject(String name, String category) {
        this.name = name;
        this.category = category;
        this.purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }
}
