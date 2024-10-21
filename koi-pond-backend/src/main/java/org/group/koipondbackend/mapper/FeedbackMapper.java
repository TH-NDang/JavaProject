package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.entity.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    public FeedbackDTO toDto(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setCustomerId(feedback.getCustomerId());
        dto.setContent(feedback.getContent());
        dto.setRating(feedback.getRating());
        dto.setCreatedAt(feedback.getCreatedAt());
        return dto;
    }

    public Feedback toEntity(FeedbackDTO dto) {
        if (dto == null) {
            return null;
        }
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setCustomerId(dto.getCustomerId());
        feedback.setContent(dto.getContent());
        feedback.setRating(dto.getRating());
        feedback.setCreatedAt(dto.getCreatedAt());
        return feedback;
    }

    public List<FeedbackDTO> toDtoList(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}