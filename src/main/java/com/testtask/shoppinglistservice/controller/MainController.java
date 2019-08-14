package com.testtask.shoppinglistservice.controller;

import com.testtask.shoppinglistservice.domain.PurchaseObject;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String GetList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<PurchaseObject> purchases = purchaseRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            purchases = purchaseRepository.findByCategory(filter);
        } else {
            purchases = purchaseRepository.findAll();
        }

        model.addAttribute("purchases", purchases);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid PurchaseObject purchaseObject,
            BindingResult bindingResult,
            Model model)
    {
        purchaseObject.setAuthor(user);

        if (bindingResult.hasErrors()) {

            Map<String, String> erorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(erorsMap);
            model.addAttribute("purchaseObject", purchaseObject);

        } else {
            purchaseRepository.save(purchaseObject);
        }

        model.addAttribute("purchaseObject", null);

        Iterable<PurchaseObject> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);

        return "main";
    }



}
