//package dev.springboot.study.security;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(HomeController.class)
//public class HomeControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    @WithMockUser
//    public void hello() throws Exception {
//        mockMvc.perform(get("/hello"))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(view().name("hello"));
//    }
//
//    @Test
//    @WithMockUser
//    public void my() throws Exception {
//        mockMvc.perform(get("/my").accept(MediaType.TEXT_HTML))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(view().name("my"));
//    }
//}