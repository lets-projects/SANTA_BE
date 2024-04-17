package com.example.santa.test;

import com.example.santa.Mountain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Mountain> getMountains() {
        return testRepository.findAll();
    }

    public Mountain add(TestDto testDto){
        Mountain mountain = new Mountain();
        mountain.setName(testDto.getName());
        mountain.setLocation(testDto.getLocation());
        mountain.setHeight(testDto.getHeight());
        mountain.setLatitude(testDto.getLatitude());
        mountain.setLongitude(testDto.getLongitude());

        return testRepository.save(mountain);
    }
}
