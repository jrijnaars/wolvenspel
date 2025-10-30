package nl.wolvenspel.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hallo van de backend!";
    }

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "wereld") String name) {
        return "Hoi " + name + ", groeten van de Java backend!";
    }
}
