package top.infoservice.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.infoservice.springcourse.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PepleController {

    private final PersonDAO personDAO;

    @Autowired
    public PepleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        // Получаем всех из DAO и передаем в представление
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        // Получаем одного по id из DAO и передаем на представление
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }

}
