package com.example.spotifyplaylistapp.controller;


import com.example.spotifyplaylistapp.model.entity.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.entity.Song;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    private final LoggedUser loggedUser;

    private final HomeService homeService;

    public HomeController(LoggedUser loggedUser, HomeService homeService) {
        this.loggedUser = loggedUser;
        this.homeService = homeService;
    }

    @GetMapping()
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        List<Song> popSongs = this.homeService.findAllPop();
        List<SongDTO> listPop = getSongs(popSongs);
        model.addAttribute("popSongs", listPop);

        List<Song> rockSongs = this.homeService.findAllRock();
        List<SongDTO> listRock = getSongs(rockSongs);
        model.addAttribute("rockSongs", listRock);

        List<Song> jazzSongs = this.homeService.findAllJazz();
        List<SongDTO> listJazz = getSongs(jazzSongs);
        model.addAttribute("jazzSongs", listJazz);

        return "home";
    }

    private static List<SongDTO> getSongs(List<Song> popSongs) {
        List<SongDTO> list = new ArrayList<>();
        int seconds;
        int minutes;
        int remainSeconds;
        for (Song currentSong : popSongs) {
            SongDTO popSongDTO = new SongDTO();
            seconds = currentSong.getDuration();
            minutes = seconds / 60;
            remainSeconds = seconds % 60;
            popSongDTO.setDuration(minutes + ":" + remainSeconds);
            popSongDTO.setId(currentSong.getId());
            popSongDTO.setTitle(currentSong.getTitle());
            popSongDTO.setPerformer(currentSong.getPerformer());
            list.add(popSongDTO);
        }
        return list;
    }
}
