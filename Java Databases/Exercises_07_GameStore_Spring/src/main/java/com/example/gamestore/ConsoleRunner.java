package com.example.gamestore;

import com.example.gamestore.entities.users.User;
import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.exeptions.UserNotLoggedInExeption;
import com.example.gamestore.exeptions.ValidationException;
import com.example.gamestore.services.ExecuteService;
import com.example.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private ExecuteService executorService;

    @Autowired
    public ConsoleRunner(ExecuteService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        String commandData = scanner.nextLine();

        String result;

        try {
            result = executorService.execute(command, commandData);
        } catch (ValidationException | UserNotLoggedInExeption ex){
            result = ex.getMessage();
        }

        System.out.println(result);

    }
}
