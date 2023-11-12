package com.rft.neptun.web.transformer;

import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.web.domain.CreateExamRequest;
import com.rft.neptun.web.domain.ExamView;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transformer class that transforms {@link ExamEntity} objects to {@link ExamView}.
 */
@Component
public class ExamTransformer {

    /**
     * Transforms a {@link Collection} of {@link ExamEntity} to a {@link List} of {@link ExamView}.
     *
     * @param collection The {@link Collection} of {@link ExamEntity} to be transformed
     * @return The transformed {@link List} of {@link ExamView} or null if the given collection is null.
     */
    public List<ExamView> transform(Collection<ExamEntity> collection){
        List<ExamView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Transforms a single {@link ExamEntity} to a {@link ExamView}.
     *
     * @param entity The {@link ExamEntity} to be transformed.
     * @return The transformed {@link ExamView} or null if the given entity is null.
     */
    public ExamView transform(ExamEntity entity){
        ExamView result = null;
        if(entity != null){
            System.out.println(entity.toString());
            result = ExamView.builder()
                    .id(entity.getId())
                    .course(entity.getCourse())
                    .examDate(entity.getExamDate())
                    .location(entity.getLocation())
                    .build();
        }
        return result;
    }
    
    /**
     * Transforms a single {@link ExamView} to a {@link ExamEntity}.
     *
     * @param request The {@link ExamView} to be transformed.
     * @return The transformed {@link ExamEntity} or null if the given entity is null.
     */
    public ExamEntity transform(CreateExamRequest request){
        ExamEntity result = null;
        if(request != null){
            LocalDateTime examDate = LocalDateTime.parse(request.getExamDate()+":00");
            System.out.println(examDate);
            result = new ExamEntity();            
            result.setCourse(request.getCourse());
            result.setExamDate(examDate);
            result.setLocation(request.getLocation());
        }
        return result;
    }
}