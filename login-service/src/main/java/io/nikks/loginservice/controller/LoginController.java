package io.nikks.loginservice.controller;

import io.nikks.loginservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserType(@PathVariable Long userId ){
        return new ResponseEntity<>(loginService.getUserType(userId),HttpStatus.OK);
    }
    @GetMapping()
    public  String login(){
        return "login";
    }
    @PostMapping()
    public Object loginCheck(@RequestParam Long username, @RequestParam String password, Model model){
        String result = loginService.loginCheck(username, password);

        Map<String, String> redirectUrlMap = Map.of(
                "admin", "http://localhost:9090/api/assessment",
                "employee", "http://localhost:9090/api/employee/home"
        );

        if (redirectUrlMap.containsKey(result)) {
            String redirectUrl = redirectUrlMap.get(result);
            return "redirect:" + redirectUrl + "?username=" + username;
        } else {
            model.addAttribute("alertMessage", result);
            return new ModelAndView("login");
        }
    }
}
