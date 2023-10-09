package com.plannerapp.controller;

import com.plannerapp.model.entity.dto.AllAvailableTasksDTO;
import com.plannerapp.model.entity.dto.CurrentUserDTO;
import com.plannerapp.model.entity.dto.UserTasksDTO;
import com.plannerapp.service.TaskService;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(name = "/")
public class HomeController {


    private final LoggedUser loggedUser;

    private final UserService userService;

    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser, UserService userService, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("")
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
        Optional<CurrentUserDTO> currentUserDTO = this.userService.findCurrentUser(loggedUser.getId());
        model.addAttribute("currentUserDTO", currentUserDTO);
        Set<AllAvailableTasksDTO> allAvailableTasksDTO = this.taskService.findAllAvailableTasks(loggedUser.getId());
        model.addAttribute("allAvailableTasksDTO", allAvailableTasksDTO);
        Set<UserTasksDTO> userTasksDTO = this.taskService.getTasksUser(loggedUser.getId());
        model.addAttribute("userTasksDTO", userTasksDTO);
        return "home";
    }

    @GetMapping("add-task/{id}")
    public String addTask(@PathVariable Long id) {
        this.taskService.addTaskToUser(id, loggedUser.getId());
        return "redirect:/home";
    }

    @GetMapping("return-task/{id}")
    public String returnTask(@PathVariable Long id) {
        this.taskService.returnTask(id, loggedUser.getId());
        return "redirect:/home";
    }

    @GetMapping("finish-task/{id}")
    public String finishTask(@PathVariable Long id) {
        this.taskService.finishTask(id, loggedUser.getId());
        return "redirect:/home";
    }
}
