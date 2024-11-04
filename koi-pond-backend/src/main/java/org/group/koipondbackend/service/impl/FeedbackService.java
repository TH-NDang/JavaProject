package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.entity.Feedback;
import org.group.koipondbackend.mapper.FeedbackMapper;
import org.group.koipondbackend.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public List<FeedbackDTO> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbackMapper.toDtoList(feedbacks);
    }

    public Optional<FeedbackDTO> getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .map(feedbackMapper::toDto);
    }

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toEntity(feedbackDTO);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return feedbackMapper.toDto(savedFeedback);
    }

    public Optional<FeedbackDTO> updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        return feedbackRepository.findById(id)
                .map(feedback -> {
                    feedback.setContent(feedbackDTO.getContent());
                    feedback.setRating(feedbackDTO.getRating());
                    return feedbackMapper.toDto(feedbackRepository.save(feedback));
                });
    }

    public boolean deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Truy xuất phản hồi của khách hàng cụ thể mà không liên quan đến User
    public List<FeedbackDTO> getFeedbacksByCustomerId(Long customerId) {
        List<Feedback> feedbacks = feedbackRepository.findByCustomerId(customerId);
        return feedbackMapper.toDtoList(feedbacks);
    }
}
