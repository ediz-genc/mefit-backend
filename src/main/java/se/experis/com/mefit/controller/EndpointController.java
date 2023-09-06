package se.experis.com.mefit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
public class EndpointController {

    @GetMapping("public")
    public ResponseEntity<String> publicEndpoint() {
        return new ResponseEntity<>(
                "A public endpoint, accessible for anyone.",
                HttpStatus.OK);
    }

    @GetMapping("restricted")
    public ResponseEntity<String> restrictedEndpoint() {
        return new ResponseEntity<>(
                "A restricted endpoint that is authenticated but not authorized (accessible only when logged in).",
                HttpStatus.OK);
    }

    @GetMapping("admin")
    public ResponseEntity<String> adminEndpoint() {
        return new ResponseEntity<>(
                "Admin endpoint that is authorized and authenticated (accessible only for admins).",
                HttpStatus.OK);
    }
}
