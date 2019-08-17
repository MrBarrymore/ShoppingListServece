package com.testtask.shoppinglistservice.controller;

import com.testtask.shoppinglistservice.domain.Purchase;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import com.testtask.shoppinglistservice.service.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User currentUser,
            @Valid Purchase purchase,
            BindingResult bindingResult,
            Model model)
    {
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

    @GetMapping("/user-purchase/{user}")
    public String updatePurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam Purchase purchase
    ) {

        Set<Purchase> purchases = user.getPurchases();

        model.addAttribute("purchases", purchases);
        model.addAttribute("purchase", purchase);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "updatePurchase";
    }

    @PostMapping("/updatePurchase")
    public String updatePurchase(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("id") Purchase purchase,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("cost") String cost,
            @RequestParam("purchaseDate") String date,
            Model model
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

        return "updatePurchase";
    }

    @GetMapping("/delete-purchase/{user}")
    public String deletePurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam Purchase purchase,
            Model model
    ) {

        purchaseRepository.deleteById(purchase.getId());

        List<Purchase> purchases = purchaseRepository.findByAuthor(currentUser);

        model.addAttribute("purchases", purchases);

        return "main";
    }



}
