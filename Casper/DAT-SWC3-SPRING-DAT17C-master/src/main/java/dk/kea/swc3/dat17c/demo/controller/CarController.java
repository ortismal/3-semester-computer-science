package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepo;
    @Autowired
    private UserRepository userRepo;


//    @GetMapping("/thisIsTheURLCar")
//    public String car(Model model){
//
//        ArrayList<Car> cars = new ArrayList<>();
//        User user = userRepo.findOne(1L);
//        cars.add(new Car("BMW", "pink", 5, 200, user));
//        cars.add(new Car("Opel", "blue", 3, 200, user));
//        cars.add(new Car("Fiat", "red", 5, 180, user));
//        cars.add(new Car("KIA", "yellow", 7, 200, user));
//        cars.add(new Car("Ford", "black", 1, 150, user));
//
//        model.addAttribute("carsToBeSendToView", cars);
//        return "car";
//    }

    @GetMapping("/car/show")
    public String showCars(Model m) {
        List<Car> cars = carRepo.findAll();
        m.addAttribute("carsToBeSendToView", cars);
        return "car";
    }

    @GetMapping("/car/add")
    @ResponseBody
    public String saveCar(
            @RequestParam(defaultValue = "NO_BRAND")
                    String brand,
            @RequestParam(defaultValue = "NO_COLOR")
                    String color,
            @RequestParam(defaultValue = "-1")
                    Integer doors,
            @RequestParam(defaultValue = "0")
                    Integer speed,
            @RequestParam(defaultValue = "NO_USER")
                    String userName) {

        User u = userRepo.findByName(userName);
        if (u == null) {
            u = new User(userName, null, null);
            userRepo.save(u);
        }

        Car newCar = new Car(brand, color, doors, speed, u);


        carRepo.save(newCar);

        return "OK";
    }


    @GetMapping("/car/create")
    public String newCarView(Model m) {
        m.addAttribute("car", new Car());
        List<User> users = userRepo.findAll();
        m.addAttribute("users", users);
        return "carNew";
    }

    @GetMapping("/car/edit/{id}")
    public String carEditView(Model m, @PathVariable Long id) {
        Car c = carRepo.findById(id);
        m.addAttribute("car", c);
        List<User> users = userRepo.findAll();
        m.addAttribute("users", users);
        return "carEdit";
    }

}
