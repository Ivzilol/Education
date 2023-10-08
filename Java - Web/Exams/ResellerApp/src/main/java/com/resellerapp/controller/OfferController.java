package com.resellerapp.controller;


import com.resellerapp.model.entity.dto.AddOfferDTO;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final LoggedUser loggedUser;

    private final OfferService offerService;

    public OfferController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @GetMapping("")
    public String offers() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/add-offer")
    public String getAddOffer() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "offer-add";
    }

    @PostMapping("/add-offer")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOfferDTO", addOfferDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", result);
            return "redirect:/offers/add-offer";
        }
        addOfferDTO.setId(loggedUser.getId());
        this.offerService.addOffer(addOfferDTO);
        return "redirect:/home";
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }
}
