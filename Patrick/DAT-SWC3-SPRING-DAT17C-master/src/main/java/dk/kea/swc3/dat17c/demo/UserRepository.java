package dk.kea.swc3.dat17c.demo;

import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
    List<User> findAll();
}
