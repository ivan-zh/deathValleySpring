package z.ivan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import z.ivan.service.AccountService;
import z.ivan.service.UserService;

@Controller
public class SayHello {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public SayHello(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    /**
     * main page
     */
    @GetMapping("/")
    public ModelAndView root(Model model) {
        return new ModelAndView("show_info");
    }


    /**
     * let's retrieve data from DB using Spring JDBC
     */
    @GetMapping("/info")
    public ModelAndView getInfo(Model model) {
        ModelAndView mov = new ModelAndView("show_info");

        String richestName = userService.getRichest();
        Long allMoney = accountService.getTotal();

        mov.addObject("name", richestName);
        mov.addObject("total", allMoney);

        return mov;
    }

}
