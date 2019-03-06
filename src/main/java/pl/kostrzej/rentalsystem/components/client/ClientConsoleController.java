package pl.kostrzej.rentalsystem.components.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class ClientConsoleController implements ClientController {

    private Scanner scanner;
    private ClientService clientService;


    @Override
    public void addClient() {
        Client client = new Client();
        System.out.println("\nDODAWANIE NOWEGO KLIENTA");
        System.out.println("Podaj imię klienta:");
        client.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko klienta:");
        client.setLastName(scanner.nextLine());
        System.out.println("Podaj pesel klienta:");
        client.setPesel(scanner.nextLine());
        System.out.println("Podaj nr dokumentu klienta:");
        client.setIdDocumentNumber(scanner.nextLine());
        try {
            clientService.addClient(client);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteClient() {
        System.out.println("\nUSUWANIE KLIENTA PO ID");
        System.out.println("Podaj ID klienta do usunięcia:");
        try {
            clientService.deleteClientById(scanner.nextLong());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
