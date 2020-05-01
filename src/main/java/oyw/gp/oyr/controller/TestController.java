package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController
{
    @RequestMapping("/home")
    public String htm() {
        return "/testAJAX";
    }

    @GetMapping(value = "/test/{name}")
    public String test(@PathVariable String name) {
        return new String("/" + name);
    }
}
