package com.testtask.shoppinglistservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Please fill the name")
    @Length(max = 255, message = "Name too long (more then 255)")
    private String name;
    @NotBlank(message = "Please fill the category")
    @Length(max = 255, message = "Name too long (more then 255)")
    private String category;
    private String description;
    private String cost;
    private boolean isBought;
    private Date purchaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "none";
    }


    public PurchaseObject(String name, String category, User user) {
        this.author = user;
        this.name = name;
        this.category = category;
        this.purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }


    public PurchaseObject(String name, String category, String description, String cost,  User author) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.isBought = false;
        this.purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        this.author = author;
    }
}
