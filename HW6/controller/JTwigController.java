package ru.kpfu.formsvalidation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.formsvalidation.model.NewUser;
import ru.kpfu.formsvalidation.service.NewUserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class JTwigController {

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(ModelMap modelMap){
//        List<String> list = new ArrayList<>();
        NewUserService userService = new NewUserService();
        List<NewUser> listUsers = userService.getAll();
//        list.add("Hello");
        modelMap.put("users",listUsers);
        return "main";
    }
}
