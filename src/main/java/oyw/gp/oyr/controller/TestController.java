package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController
{
    @RequestMapping("/home")
    public String htm() {
        return "/testAJAX";
    }

    @GetMapping("/rec")
    public String rec(){
        return "/rec";
    }
    @GetMapping("/recycle")
    public String recy(){
        return "/recycle";
    }

}