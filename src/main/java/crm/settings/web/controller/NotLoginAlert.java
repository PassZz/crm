package crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotLoginAlert {

    @RequestMapping("/notLoginAlert")
    public String notLoginAlert(){
        return "notLoginAlert";
    }
}
