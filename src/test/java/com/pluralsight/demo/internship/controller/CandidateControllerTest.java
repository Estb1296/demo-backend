package com.pluralsight.demo.internship.controller;

import com.pluralsight.demo.internship.model.Candidate;
import com.pluralsight.demo.internship.service.CandidateService;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidateController.class)
class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CandidateService candidateService;

    @Test
    void getAllCandidates_shouldReturnListOfCandidates()throws Exception {
        Candidate candidate = new Candidate("Ezra","et@gmail.com","Data Science");

        List<Candidate> candidates = Arrays.asList(candidate);

        when(candidateService.getAllCandidates()).thenReturn(candidates);

        mockMvc.perform(get("/api/candidates")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ezra"))
                .andExpect(jsonPath("$[0].email").value("et@gmail.com"))
                .andExpect(jsonPath("$[0].fieldOfStudy").value("Data Science"))
                .andExpect(jsonPath("$.length()").value(1));//1 item
    }

    @Test
    void getCandidateById() {
    }

    @Test
    void createCandidate() {
    }

    @Test
    void updateCandidate() {
    }

    @Test
    void deleteCandidate() {
    }

    @Test
    void searchByName() {
    }
}