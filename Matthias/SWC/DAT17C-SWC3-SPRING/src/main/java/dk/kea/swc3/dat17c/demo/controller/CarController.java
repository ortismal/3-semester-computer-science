package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class CarController {

    @GetMapping("/cars")
    public String cars(Model model){

        // HTML: th:classappend="${user.gender} == 'M' ? 'bg-primary' : 'bg-danger'

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Opel Cadet", 110, "red"));
        cars.add(new Car("Volvo", 120, "blue"));
        cars.add(new Car("Mercedes", 130, "green"));
        cars.add(new Car("Fiat", 140, "yellow"));
        cars.add(new Car("Skoda", 150, "violet"));

        model.addAttribute("cars", cars);

        return "/cars";
    }

}
