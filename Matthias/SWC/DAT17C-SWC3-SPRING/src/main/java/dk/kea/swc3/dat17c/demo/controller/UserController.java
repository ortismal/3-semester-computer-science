package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.model.Car;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class UserController {

    @GetMapping("/")
    @ResponseBody
    public ModelAndView index(
            @RequestParam(defaultValue = "{{user}}")
            String name,
            @RequestParam(defaultValue = "-1")
            Integer age,
            @RequestParam(defaultValue = "F")
            Character gender){

        User u = new User(name, age, gender);

        ModelAndView mv = new ModelAndView("index");
        mv.getModel().put("user", u);
        return mv;
    }

    @GetMapping("/cars")
    public String cars(Model model){

        // HTML: th:classappend="${user.gender} == 'M' ? 'bg-primary' : 'bg-danger'

        Car c1 = new Car("Opel Cadet", 110, "red");
        Car c2 = new Car("Volvo", 120, "blue");
        Car c3 = new Car("Mercedes", 130, "green");
        Car c4 = new Car("Fiat", 140, "yellow");
        Car c5 = new Car("Skoda", 150, "violet");
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.add(c4);
        cars.add(c5);
        model.addAttribute("cars", cars);

        return "/cars";
    }

}
