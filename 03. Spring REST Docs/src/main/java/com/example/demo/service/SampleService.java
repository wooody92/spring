package com.example.demo.service;

import com.example.demo.model.Sample;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class SampleService {

    public Sample getSample(Long id) {
        return new Sample(1L, "Henry", 30);
    }
}
