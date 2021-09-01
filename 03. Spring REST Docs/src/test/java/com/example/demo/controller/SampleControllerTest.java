package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Sample;
import com.example.demo.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(SampleController.class)
class SampleControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint()))
            .build();
    }

    /**
     * MockMvcRestDocumentation.document()
     * - identifier : /build/generated-snippets 하위 adoc 파일이 생성될 디렉토리
     * - snippet : pathParameters, requestFields, responseFields
     */
    @Test
    void getSample() throws Exception {
        // given
        given(sampleService.getSample(anyLong()))
            .willReturn(new Sample(1L, "Henry", 30));

        // when, then
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/sample/{id}", 1))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(
                document(
                    "sample",
                    pathParameters(
                        parameterWithName("id").description("사용자 ID")
                    ),
                    responseFields(
                        PayloadDocumentation.fieldWithPath("id").description("사용자 ID"),
                        PayloadDocumentation.fieldWithPath("name").description("사용자 이름"),
                        PayloadDocumentation.fieldWithPath("age").description("사용자 나이")
                    )
                )
            );
    }
}
