package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class CarController {

    @GetMapping("/thisIsTheURLCar")
    public String car(Model model){

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("BMW", "pink", 5));
        cars.add(new Car("Opel", "blue", 3));
        cars.add(new Car("Fiat", "red", 5));
        cars.add(new Car("KIA", "yellow", 7));
        cars.add(new Car("Ford", "black", 1));

        model.addAttribute("carsToBeSendToView", cars);
        return "car";
    }
}
