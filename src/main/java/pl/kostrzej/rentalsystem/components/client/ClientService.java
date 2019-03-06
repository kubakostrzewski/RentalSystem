package pl.kostrzej.rentalsystem.components.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ClientService {


    private ClientRepository clientRepository;
    private ClientValidator clientValidator;

    public void addClient(Client client) {
        clientValidator.validate(client);
        clientRepository.save(client);
    }

    public void deleteClientById(Long id) {
        if (clientRepository.existsById(id))
            clientRepository.deleteById(id);
        else throw new EntityNotFoundException("Klient o podanym ID nie istnieje!");
    }

    public Client findById(long id) {
        return clientRepository.findById(id);
    }
}
