package ptit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping
    public String test() {
        System.out.println("get");
        return "Hello world";
    }

    @GetMapping("/abc")
    public String abc() {
        System.out.println("get abc");
        return "Hello world";
    }
}
