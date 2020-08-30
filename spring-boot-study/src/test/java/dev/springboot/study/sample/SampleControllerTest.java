package dev.springboot.study.sample;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Rule
    public OutputCaptureRule outputCaptureRule = new OutputCaptureRule();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("wooody92");
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("hello wooody92"));

        assertThat(outputCaptureRule.toString())
            .contains("OutputCapture test")
            .contains("skip");
    }
}