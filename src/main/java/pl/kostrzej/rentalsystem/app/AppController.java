package pl.kostrzej.rentalsystem.app;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.kostrzej.rentalsystem.components.category.CategoryController;
import pl.kostrzej.rentalsystem.components.client.ClientController;
import pl.kostrzej.rentalsystem.components.device.DeviceController;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class AppController {

    private Scanner scanner;
    private DeviceController deviceController;
    private ClientController clientController;
    private CategoryController categoryController;

    public void run() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            exit = chooseOption();
        }
    }

    private void printMenu() {
        System.out.println("\nWitaj w wypożyczalni!");
        Arrays.stream(Options.values()).forEach(System.out::println);
    }

    private boolean chooseOption() {
        try {
            switch (Options.chooseOption(option())) {
                case ADD_DEVICE:
                    deviceController.addDevice();
                    break;
                case ADD_CATEGORY:
                    categoryController.addCategory();
                    break;
                case ADD_CLIENT:
                    clientController.addClient();
                    break;
                case RENT_DEVICE:
                    deviceController.rentDevice();
                    break;
                case DELETE_DEVICE:
                    deviceController.deleteDevice();
                    break;
                case DELETE_CATEGORY:
                    categoryController.deleteCategory();
                    break;
                case DELETE_CLIENT:
                    System.out.println("Podaj id klienta:");
                    clientController.deleteClient();
                    break;
                case EXIT:
                    return true;
            }
        } catch (InvalidOptionException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (InputMismatchException e){
            System.err.println("Podaj liczbę!");
            scanner.nextLine();
        }
        return false;
    }

    private int option() {
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }
}
