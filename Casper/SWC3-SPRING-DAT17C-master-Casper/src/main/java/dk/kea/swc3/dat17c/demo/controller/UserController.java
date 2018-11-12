package dk.kea.swc3.dat17c.demo.controller;

import dk.kea.swc3.dat17c.demo.UserRepository;
import dk.kea.swc3.dat17c.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public ModelAndView index(
            @RequestParam(defaultValue = "{{user}}")
            String name,
            @RequestParam(defaultValue = "-1")
            Integer age,
            @RequestParam(defaultValue = "F")
            Character gender){

        User u = new User(name, age, gender);

        ModelAndView mv = new ModelAndView("index");
        mv.getModel().put("user",u);

        return mv;
    }

    @GetMapping("/user/add")
    @ResponseBody
    public User saveUser(
            @RequestParam(defaultValue = "NO_NAME")
            String name,
            @RequestParam(defaultValue = "-1")
            Integer age,
            @RequestParam(defaultValue = "N")
            Character gender){

        User newUser = new User(name, age, gender);

        userRepo.save(newUser);
        return newUser;
    }

}
