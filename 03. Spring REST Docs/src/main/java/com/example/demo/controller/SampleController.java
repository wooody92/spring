package com.example.demo.controller;

import com.example.demo.model.Sample;
import com.example.demo.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/sample/{id}")
    public ResponseEntity<Sample> sample(@PathVariable Long id) {
        return new ResponseEntity<>(sampleService.getSample(id), HttpStatus.OK);
    }
}
