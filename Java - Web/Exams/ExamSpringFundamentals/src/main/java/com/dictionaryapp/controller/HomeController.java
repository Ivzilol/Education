package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.FrenchWordsDTO;
import com.dictionaryapp.model.dto.GermanWordsDTO;
import com.dictionaryapp.model.dto.ItalianWordsDTO;
import com.dictionaryapp.model.dto.SpanishWordsDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {

    private final LoggedUser loggedUser;

    private final WordService wordService;
    private long size = 0;


    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("")
    public String home() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        size = 0;
        Set<GermanWordsDTO> germanWordsDTO = this.wordService.getGermanWords();
        model.addAttribute("germanWordsDTO", germanWordsDTO);
        size += germanWordsDTO.size();
        Set<SpanishWordsDTO> spanishWordsDTO = this.wordService.getSpanishWords();
        size += spanishWordsDTO.size();
        model.addAttribute("spanishWordsDTO", spanishWordsDTO);
        Set<FrenchWordsDTO> frenchWordsDTO = this.wordService.getFrenchWords();
        size += frenchWordsDTO.size();
        model.addAttribute("frenchWordsDTO", frenchWordsDTO);
        Set<ItalianWordsDTO> italianWordsDTO = this.wordService.getItalianWords();
        size += italianWordsDTO.size();
        model.addAttribute("italianWordsDTO", italianWordsDTO);
        model.addAttribute("size", size);
        return "home";
    }

    @GetMapping("/remove-word/{id}")
    public String removeSong(@PathVariable Long id){
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        this.wordService.removeSong(id);
        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        this.wordService.removeAll();
        return "redirect:/home";
    }
}
