package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {

    private final LoggedUser loggedUser;

    private final WordService wordService;

    public WordController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/add-word")
    public String addWord() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "word-add";
    }

    @PostMapping("/add-word")
    public String userAddWord(@Valid AddWordDTO addWordDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addWordDTO", addWordDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addWordDTO", result);
            return "redirect:/words/add-word";
        }

        this.wordService.addWord(addWordDTO, loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public AddWordDTO addWordDTO() {
        return new AddWordDTO();
    }
}
