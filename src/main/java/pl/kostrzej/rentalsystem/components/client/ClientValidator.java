package pl.kostrzej.rentalsystem.components.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientValidator {

    private ClientRepository clientRepository;

    public void validate(Client client) {
        if (!client.getPesel().isEmpty() && clientRepository.existsByPesel(client.getPesel()))
            throw new ClientAlreadyExistsException("Klient o podanym numerze PESEL istnieje!");
        if (!client.getIdDocumentNumber().isEmpty() && clientRepository.existsByIdDocumentNumber(client.getIdDocumentNumber()))
            throw new ClientAlreadyExistsException("Klient o podanym nr dowodu istnieje!");
        if (client.getFirstName().isEmpty())
            throw new NullPointerException("Pole Imię nie może być puste!");
        if (client.getIdDocumentNumber().isEmpty() && client.getPesel().isEmpty())
            throw new InvalidClientException("Należy podać PESEL lub nr dokumentu klienta!");
    }
}
