package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.entity.Feedback;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackDTO toDto(Feedback feedback);
    Feedback toEntity(FeedbackDTO feedbackDTO);
    List<FeedbackDTO> toDtoList(List<Feedback> feedbacks);
    List<Feedback> toEntityList(List<FeedbackDTO> feedbackDTOs);
}
