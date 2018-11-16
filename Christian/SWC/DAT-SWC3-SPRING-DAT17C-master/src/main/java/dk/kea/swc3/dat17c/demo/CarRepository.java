package dk.kea.swc3.dat17c.demo;

import dk.kea.swc3.dat17c.demo.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    Car findById(Long id);
    List<Car> findAll();
    Car deleteById(Long id);
}
