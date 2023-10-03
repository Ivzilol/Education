package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.dto.AddSongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final LoggedUser loggedUser;

    private final SongService songService;

    public SongController(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @GetMapping()
    public String addSong() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "song-add";
    }

    @PostMapping("/add-song")
    public String addSong(@Valid AddSongDTO addSongDTO,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addSongDTO", addSongDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", result);
            return "redirect:/songs";
        }
        addSongDTO.setId(loggedUser.getId());
        this.songService.addSong(addSongDTO);

        return "redirect:/home";
    }

    @ModelAttribute
    public AddSongDTO addSongDTO() {
        return new AddSongDTO();
    }

}
