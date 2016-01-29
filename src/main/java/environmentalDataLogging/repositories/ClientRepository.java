package environmentalDataLogging.repositories;

import environmentalDataLogging.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID>
{
	Client findByName(String name);
}


