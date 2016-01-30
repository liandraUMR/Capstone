package environmentalDataLogging.repositories;

import environmentalDataLogging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID>
{
    User findByEmail(String email);
}