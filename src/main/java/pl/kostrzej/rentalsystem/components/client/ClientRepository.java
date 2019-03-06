package pl.kostrzej.rentalsystem.components.client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    boolean existsByPesel(String pesel);

    boolean existsByIdDocumentNumber(String idNumber);

    Client findById(long id);
}
