package com.example.santa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
    @GetMapping("/test1")
    public String test1(){
        return "asdsa";
    }

    @GetMapping("/test")
    public List<Mountain> test(){
        return testService.getMountains();
    }

    @PostMapping("/add")
    public Mountain add(@RequestBody TestDto testDto){
        return testService.add(testDto);
    }

}
