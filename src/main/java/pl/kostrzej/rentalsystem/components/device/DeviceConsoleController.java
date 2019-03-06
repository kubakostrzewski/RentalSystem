package pl.kostrzej.rentalsystem.components.device;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.rentalsystem.components.category.Category;
import pl.kostrzej.rentalsystem.components.category.CategoryService;
import pl.kostrzej.rentalsystem.components.client.Client;
import pl.kostrzej.rentalsystem.components.client.ClientService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class DeviceConsoleController implements DeviceController {

    @Autowired
    private Scanner scanner;
    private DeviceService deviceService;
    private CategoryService categoryService;
    private ClientService clientService;

    @Override
    public void deleteDevice() {
        System.out.println("USUWANIE URZĄDZENIA");
        deviceService.getAllDevices().forEach(System.out::println);
        System.out.println("Podaj id urządzenia do usunięcia:");
        int deviceToDeleteId = scanner.nextInt();
        scanner.nextLine();
        if (deviceService.existsById(deviceToDeleteId)) {
            deviceService.deleteById(deviceToDeleteId);
        } else {
            System.err.println("Urządzenie o podanym id nie istnieje!");
        }
    }

    @Override
    public void addDevice() {
        Device device = new Device();
        System.out.println("\nDODAWANIE NOWEGO URZĄDZENIA");
        readDeviceName(device);
        readDeviceDescription(device);
        readDeviceAmount(device);
        readDevicePrice(device);
        selectDeviceCategory(device);
        deviceService.addDevice(device);
    }

    private void readDeviceDescription(Device device) {
        System.out.println("Podaj opis urządzenia:");
        device.setDescription(scanner.nextLine());
    }

    private void readDevicePrice(Device device) {
        do {
            System.out.println("Podaj cenę urządzenia:");
            try {
                device.setPrice(scanner.nextDouble());
                if (device.getPrice() < 0) System.err.println("Podaj prawidłową cenę!");
            }catch (InputMismatchException e){
                System.err.println("Podaj liczbę!");
            } finally {
                scanner.nextLine();
            }
        } while (device.getPrice() < 0.1);
    }

    private void readDeviceAmount(Device device) {
        do {
            System.out.println("Podaj ilość urządzeń:");
            try {
                device.setAmount(scanner.nextInt());
                if (device.getAmount() < 1) System.err.println("Podaj poprawną ilość urządzeń!");
            } catch (InputMismatchException e){
                System.err.println("Podaj liczbę!");
            } finally {
                scanner.nextLine();
            }
        } while (device.getAmount() < 1 );
    }

    private void readDeviceName(Device device) {
        do {
            System.out.println("Podaj nazwę urządzenia:");
            device.setName(scanner.nextLine());
            if (StringUtils.isBlank(device.getName())) System.err.println("Nazwa urządzenia nie może być pusta!");
        } while (StringUtils.isBlank(device.getName()));
    }

    @Override
    public void rentDevice() {
        System.out.println("WYPOŻYCZANIE URZĄDZENIA");
        System.out.println("Podaj id klienta:");
        Client client = clientService.findById(scanner.nextLong());
        System.out.println(client);
        System.out.println("Podaj id urządzenia:");
        Device device = deviceService.findById(scanner.nextLong());
        client.getDevices().add(device);
        device.setAmount(device.getAmount() - 1);
        clientService.addClient(client);
        deviceService.addDevice(device);

    }

    private void selectDeviceCategory(Device device) {
        System.out.println("Podaj nr kategorii urządzenia:");
        int deviceIndex = -1;
        List<Category> categories = categoryService.getAllCategories();
        do {
            IntStream.range(0, categories.size())
                    .forEach(i -> System.out.println(i + 1 + " " + categories.get(i).getName()));
            try {
                deviceIndex = scanner.nextInt() - 1;
            } catch (InputMismatchException e){
                System.err.println("Podaj liczbę!");
            } finally {
                scanner.nextLine();
            }
        } while (deviceIndex < 0 || deviceIndex >= categories.size());
        device.setCategory(categories.get(deviceIndex));
    }
}
