package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.CarRepository;
import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepo;
    @Autowired
    private UserRepository userRepo;


    @GetMapping("/thisIsTheURLCar")
    public String car(Model model){

        ArrayList<Car> cars = new ArrayList<>();

        model.addAttribute("carsToBeSendToView", cars);
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
            String userName){

        User u = userRepo.findByName(userName);
        if(u==null){
            u = new User(userName,null,null);
            userRepo.save(u);
        }

        Car newCar = new Car(brand, color, doors, speed, u);


        carRepo.save(newCar);

        return "OK";
    }

}
