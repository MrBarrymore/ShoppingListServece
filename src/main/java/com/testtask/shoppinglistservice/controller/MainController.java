package com.testtask.shoppinglistservice.controller;

import com.testtask.shoppinglistservice.domain.Purchase;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
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

    @GetMapping("/")
    public String greeting(
    ) {
        return "greeting";
    }

    @GetMapping("/main")
    public String GetList(
            @AuthenticationPrincipal User currentUser,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
            ) {

        Iterable<Purchase> purchases;

        if (currentUser != null) {

            purchases = purchaseRepository.findByAuthor(currentUser);

//            if (filter != null && !filter.isEmpty()) {
//                purchases = purchaseRepository.findByCategory(filter);
//            } else {
//                purchases = purchaseRepository.findAll();
//            }

        }
        else {
            purchases = purchaseRepository.findAll();
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
