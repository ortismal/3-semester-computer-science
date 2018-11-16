package dk.kea.swc3.dat17c.demo.controller;
// By C. Strunge 12-11-2018

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
public class CarApiController {


    @Autowired
    private CarRepository carRepo;

    @GetMapping("/car/get/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id){

        if (id <= 0){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        Car c = carRepo.findById(id);
//        Car c = new Car("BMW", "Pink", 3, 280, new User());
        return new ResponseEntity(c, HttpStatus.OK);
    }

    @DeleteMapping("car/delete/{id}")
    public ResponseEntity<Car> deleteCar(){
        return new ResponseEntity(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/car/get")
    public ResponseEntity<List> getAllCars(){
        List<Car> cars = carRepo.findAll();
        return new ResponseEntity(cars, HttpStatus.OK);

//        Car c = carRepo.findAllByIdIsNotNull();
//        return new ResponseEntity(c, HttpStatus.OK);
    }

    @PostMapping("/car/new")
    public ResponseEntity<Car> saveCar(@PathVariable Car car){
        Car newCar = carRepo.save(car);
        return new ResponseEntity(newCar, HttpStatus.OK);

    }
    @PutMapping("/car/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,
                                         @RequestParam String brand,
                                         @RequestParam String color,
                                         @RequestParam Integer doors,
                                         @RequestParam Integer speed){


        Car carToBeUpdated = carRepo.findById(id);
//        carToBeUpdated.setBrand(car.getBrand());
//        carToBeUpdated.setColor(car.getColor());
//        carToBeUpdated.setDoors(car.getDoors());
//        carToBeUpdated.setSpeed(car.getSpeed());
        carRepo.save(carToBeUpdated);
        return new ResponseEntity<>(carToBeUpdated, HttpStatus.OK);

    }
    @DeleteMapping("/car/delete/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        Car car = carRepo.findById(id);
        carRepo.delete(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

}
