package com.example.santa.domain.mountain.controller;

import com.example.santa.domain.mountain.entity.Mountain;
import com.example.santa.domain.mountain.dto.TestDto;
import com.example.santa.domain.mountain.service.TestService;
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
