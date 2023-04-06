package bakery.core;

import bakery.common.enums.Commands;
import bakery.core.interfaces.Controller;
import bakery.core.interfaces.Engine;
import bakery.io.ConsoleReader;
import bakery.io.ConsoleWriter;

import java.io.IOException;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private ConsoleReader reader;
    private ConsoleWriter writer;
    private Controller controller;

    public EngineImpl(ConsoleReader reader, ConsoleWriter writer, Controller controller) {
        this.reader = reader;
        this.writer = writer;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals(Commands.END.name())) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }

    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Commands command = Commands.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case AddDrink:
                result = this.controller.addDrink(
                        data[0], data[1], Integer.parseInt(data[2]), data[3]
                );
                break;
            case AddFood:
                result = this.controller.addFood(
                        data[0], data[1], Double.parseDouble(data[2])
                );
                break;
            case AddTable:
                result = this.controller.addTable(
                        data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2])
                        );
                break;
            case ReserveTable:
                result = this.controller.reserveTable(Integer.parseInt(data[0]));
                break;
            case OrderDrink:
                result = this.controller.orderDrink(Integer.parseInt(data[0]),data[1],data[2]);
                break;
            case OrderFood:
                result = this.controller.orderFood(Integer.parseInt(data[0]),data[1]);
                break;
            case LeaveTable:
                result = this.controller.leaveTable(Integer.parseInt(data[0]));
                break;
            case GetFreeTablesInfo:
                result = this.controller.getFreeTablesInfo();
                break;
            case GetTotalIncome:
                result = this.controller.getTotalIncome();
                break;
            case END:
                result = Commands.END.name();
                break;
        }


        return result.trim();
    }

}
