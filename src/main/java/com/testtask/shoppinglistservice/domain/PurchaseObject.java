package com.testtask.shoppinglistservice.domain;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class PurchaseObject {

    private Long id;
    private String name;
    private Category category;
    private Date purchaseDate;


}
