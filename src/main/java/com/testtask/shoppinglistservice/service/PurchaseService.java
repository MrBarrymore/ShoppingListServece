package com.testtask.shoppinglistservice.service;


import com.testtask.shoppinglistservice.controller.ControllerUtils;
import com.testtask.shoppinglistservice.domain.Purchase;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import com.testtask.shoppinglistservice.service.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> filterPurchases(Iterable<Purchase> purchases, String filter) {

        List<Purchase> newPurchases = new LinkedList<>();
        for (Purchase purchase : purchases) {
            if (purchase.getCategory() == filter)
                newPurchases.add(purchase);
        }
        return newPurchases;
    }

    public List<Purchase> filterPurchases(Iterable<Purchase> purchases, boolean filter) {

        List<Purchase> newPurchases = new LinkedList<>();

        for (Purchase purchase : purchases) {
            if (purchase.isBought() == false)
                newPurchases.add(purchase);
        }

        return newPurchases;
    }


    public Model addPurchase(
            User currentUser,
            Purchase purchase,
            BindingResult bindingResult,
            Model model) {

        purchase.setAuthor(currentUser);
        if (bindingResult.hasErrors()) {
            Map<String, String> erorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(erorsMap);
            model.addAttribute("purchase", purchase);

        } else {
            if (purchase.getPurchaseDate() == null )  {
                purchase.setPurchaseDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            }
            purchaseRepository.save(purchase);
        }

        model.addAttribute("purchase", null);
        Iterable<Purchase> purchases = purchaseRepository.findByAuthor(currentUser);
        model.addAttribute("purchases", purchases);

        return model;
    }


    public Model updatePurchase(
            User currentUser, Purchase purchase, String name,
            String category, String description, String cost,
            String date, Model model
    ) {

        if (purchase.getAuthor().equals(currentUser)) {

            if (!StringUtils.isEmpty(name)) purchase.setName(name);
            if (!StringUtils.isEmpty(category)) purchase.setCategory(category);
            if (!StringUtils.isEmpty(description)) purchase.setDescription(description);
            if (!StringUtils.isEmpty(cost)) purchase.setCost(cost);
            if (!StringUtils.isEmpty(date) || Helper.dateIsValid(date))
                purchase.setPurchaseDate(Date.valueOf(date));

            purchaseRepository.save(purchase);

            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Запись успешно изменина");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Введенные данные не корректны");
        }

        return model;
    }




}
