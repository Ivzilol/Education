package com.plannerapp.controller;

import com.plannerapp.model.entity.dto.AddTaskDTO;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final LoggedUser loggedUser;

    private final TaskService taskService;

    public TaskController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }


    @GetMapping("")
    private String tasks() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "/home";
    }

    @GetMapping("/add-task")
    public String getAddTask() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "task-add";
    }

    @PostMapping("/add-task")
    public String addTask(@Valid AddTaskDTO addTaskDTO ,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addTaskDTO", addTaskDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", result);
            return "redirect:/tasks/add-task";
        }
        addTaskDTO.setId(loggedUser.getId());
        this.taskService.addTask(addTaskDTO);
        return "redirect:/home";
    }

    @ModelAttribute
    public AddTaskDTO addTaskDTO() {
        return new AddTaskDTO();
    }
}
