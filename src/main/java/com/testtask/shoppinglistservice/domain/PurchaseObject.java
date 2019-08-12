package com.testtask.shoppinglistservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }

    public PurchaseObject(String name, String category) {
        this.name = name;
        this.category = category;
        this.purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public PurchaseObject(String name, String category, User user) {
        this.author = user;
        this.name = name;
        this.category = category;
        this.purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }
}
