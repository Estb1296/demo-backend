package com.pluralsight.demo.internship.controller;

import com.pluralsight.demo.internship.model.Interview;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/interview")
@CrossOrigin(origins = "*")

public class InterviewController {

    private final List<Interview> interviews = new ArrayList<>();


    // Constructor seeds some initial mock data
    public InterviewController() {
        interviews.add(new Interview(1L, "Alice Smith", "Java Developer Intern", "Scheduled"));
        interviews.add(new Interview(2L, "Bob Jones", "React Front-End Intern", "Completed"));
        interviews.add(new Interview(3L, "Charlie Brown", "DevOps Intern", "Scheduled"));
    }


    @GetMapping
    // BONUS Task 6 - Modify this method to filter by an optional "status" query parameter
    public List<Interview> getAllInterviews() {
        return interviews;
    }


    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable Long id) {
        return interviews.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    @PostMapping
    public Interview scheduleInterview(@RequestBody Interview newInterview) {
        // Simple ID generation logic for our in-memory list
        Long nextId = interviews.stream().mapToLong(Interview::getId).max().orElse(0L) + 1;
        newInterview.setId(nextId);
        interviews.add(newInterview);
        return newInterview;
    }


    @DeleteMapping("/{id}")
    public String cancelInterview(@PathVariable Long id) {
        boolean removed = interviews.removeIf(i -> i.getId().equals(id));
        return removed ? "Interview canceled and removed successfully." : "Interview not found.";
    }

}