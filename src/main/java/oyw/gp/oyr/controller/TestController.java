package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController
{
    @RequestMapping("/test")
    public String htm() {
        return "historyHtml";
    }
}