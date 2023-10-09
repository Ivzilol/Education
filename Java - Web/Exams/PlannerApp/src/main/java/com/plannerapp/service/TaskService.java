package com.plannerapp.service;

import com.plannerapp.model.entity.dto.AddTaskDTO;
import com.plannerapp.model.entity.dto.AllAvailableTasksDTO;
import com.plannerapp.model.entity.dto.UserTasksDTO;
import com.plannerapp.model.entity.entity.Priority;
import com.plannerapp.model.entity.entity.Task;
import com.plannerapp.model.entity.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final PriorityRepository priorityRepository;

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(PriorityRepository priorityRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.priorityRepository = priorityRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void addTask(AddTaskDTO addTaskDTO) {
        Task task = new Task();
        task.setDescription(addTaskDTO.getDescription());
        task.setDueDate(addTaskDTO.getDueDate());
        Priority priority = this.priorityRepository.findPriority(addTaskDTO.getPriority());
        task.setPriority(priority);
        Optional<User> user = this.userRepository.findById(addTaskDTO.getId());
        task.setUser(user.get());
        this.taskRepository.save(task);
    }

    public Set<AllAvailableTasksDTO> findAllAvailableTasks(Long id) {
        List<Task> allTask = this.taskRepository.findAll();
        Optional<User> currentUser = this.userRepository.findById(id);
        Set<Task> assignedTasks = new HashSet<>();
        if (!currentUser.get().getAssignedTasks().isEmpty()) {
            assignedTasks.addAll(currentUser.get().getAssignedTasks());
        }

        Set<Task> notAssignedTask = new HashSet<>();
        for (Task task : allTask) {
            boolean isAssigned = false;
            for (Task userAssTask : assignedTasks) {
                if (Objects.equals(userAssTask.getId(), task.getId())) {
                    isAssigned = true;
                    break;
                }
            }
            if (!isAssigned) {
                notAssignedTask.add(task);
            }
        }
        Set<AllAvailableTasksDTO> returnTask = new HashSet<>();
        for (Task finalTask : notAssignedTask) {
            AllAvailableTasksDTO allAvailableTasksDTO = new AllAvailableTasksDTO();
            allAvailableTasksDTO.setId(finalTask.getId());
            allAvailableTasksDTO.setPriority(finalTask.getPriority());
            allAvailableTasksDTO.setDueDate(finalTask.getDueDate());
            allAvailableTasksDTO.setDescription(finalTask.getDescription());
            returnTask.add(allAvailableTasksDTO);
        }
        return returnTask;
    }

    public void addTaskToUser(Long id, Long loggedUserId) {
        Set<Task> task = this.taskRepository.findTaskById(id);
        Optional<User> user = this.userRepository.findById(loggedUserId);
        task.addAll(user.get().getAssignedTasks());
        user.get().setAssignedTasks(task);
        this.userRepository.save(user.get());
    }

    public Set<UserTasksDTO> getTasksUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        Set<Task> userTasks = new HashSet<>(user.get().getAssignedTasks());
        Set<UserTasksDTO> returnTask = new HashSet<>();
        for (Task task : userTasks) {
            UserTasksDTO userTasksDTO = new UserTasksDTO();
            userTasksDTO.setId(task.getId());
            userTasksDTO.setDescription(task.getDescription());
            userTasksDTO.setDueDate(task.getDueDate());
            userTasksDTO.setPriority(task.getPriority());
            returnTask.add(userTasksDTO);
        }
        return returnTask;
    }

    public void returnTask(Long id, Long userId) {
        Optional<User> user = returnUserTask(id, userId);
        this.userRepository.save(user.get());
    }

    public void finishTask(Long id, Long userId) {
        Optional<User> user = returnUserTask(id, userId);
        this.userRepository.save(user.get());
        Optional<Task> taskForRemove = this.taskRepository.findById(id);
        this.taskRepository.delete(taskForRemove.get());
    }

    private Optional<User> returnUserTask(Long id, Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        Set<Task> newTasks = new HashSet<>();
        for (Task task : user.get().getAssignedTasks()) {
            if (!Objects.equals(task.getId(), id)) {
                newTasks.add(task);
            }
        }
        user.get().setAssignedTasks(newTasks);
        return user;
    }
}
