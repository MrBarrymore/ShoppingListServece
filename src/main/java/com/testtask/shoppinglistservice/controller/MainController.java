package com.testtask.shoppinglistservice.controller;

import com.testtask.shoppinglistservice.domain.Purchase;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import com.testtask.shoppinglistservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/")
    public String greeting(
    ) {
        return "greeting";
    }

    @GetMapping("/main")
    public String GetList(
            @AuthenticationPrincipal User currentUser,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false) boolean relevant,
            Model model
            ) {

        List<Purchase> purchases = purchaseRepository.findByAuthor(currentUser);

        if (filter != null && !filter.isEmpty()) {
            purchases = purchaseService.filterPurchases(purchases, filter);
        }
        if (relevant) {
            purchases =  purchaseService.filterPurchases(purchases, relevant);
        }

        model.addAttribute("purchases", purchases);
        model.addAttribute("filter", filter);

        return "main";
    }


    @GetMapping("/confirm-purchase/{user}")
    public String confirmPurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam Purchase purchase,
            Model model
    ) {

        if (purchase.getAuthor().equals(currentUser)) {
            purchase.setIsBought(true);
            purchaseRepository.save(purchase);
            List<Purchase> purchases = purchaseRepository.findByAuthor(currentUser);
            model.addAttribute("purchases", purchases);
        }

        return "main";
    }




}
