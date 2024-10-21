package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.service.impl.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.createFeedback(feedbackDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.updateFeedback(id, feedbackDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/customers/{customerId}")
    public List<FeedbackDTO> getFeedbacksByCustomerId(@PathVariable Long customerId) {
        return feedbackService.getFeedbacksByCustomerId(customerId);
    }
}