package com.example.webfluxrestdocssample;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HelloResponse get() {
        return new HelloResponse("foo", "bar");
    }

    @GetMapping("header")
    @ResponseStatus(HttpStatus.OK)
    public HelloResponse getBody(@RequestHeader(value = "name") String name) {
        return new HelloResponse(name, name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HelloResponse create() {
        return new HelloResponse("foo", "bar");
    }
}
