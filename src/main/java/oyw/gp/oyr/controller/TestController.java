package oyw.gp.oyr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController
{
    @GetMapping(value = "/test/{name}")
    public String test(@PathVariable String name) {
        return "/" + name;
    }
}
