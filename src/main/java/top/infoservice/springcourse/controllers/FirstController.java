package top.infoservice.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(
            //HttpServletRequest request
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "surname",required = false) String surName,
            Model model){
        //String name = request.getParameter("name");
        //String surName = request.getParameter("surname");

        model.addAttribute("message","Hello, " + name + " " + surName);

        System.out.println("Hello, " + name + " " + surName);

        return "first/hello";
    }

    @GetMapping("/calc")
    public String calc(@RequestParam("a") int a,
                       @RequestParam("b") int b,
                       @RequestParam("action") String action,
                       Model model) {
        double result;
        switch (action) {
            case "multiplacation" :
                result = a * b;
                break;
            case "division" :
                result = a / (double) b;
                break;
            case "substraction" :
                result = a - b;
                break;
            case "addition" :
                result = a + b;
                break;
            default :
                result = 0;
        }

        model.addAttribute("result", result);

        return "first/calc";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }
}
