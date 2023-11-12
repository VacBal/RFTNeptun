package com.rft.neptun.web.transformer;

import com.rft.neptun.data.domain.StudentEntity;
import com.rft.neptun.web.domain.StudentView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transformer class that transforms {@link StudentEntity} and {@link StudentEntity} objects.
 */
@Component
public class StudentTransformer {
    
    @Autowired
    public StudentTransformer() {}

    /**
     * Transforms a {@link Collection} of {@link StudentEntity} to a {@link List} of {@link StudentView}.
     *
     * @param collection The {@link Collection} of {@link StudentEntity} to be transformed
     * @return The transformed {@link List} of {@link StudentView} or null if the given collection is null.
     */
    public List<StudentView> transform(Collection<StudentEntity> collection){
        List<StudentView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Transforms a single {@link StudentEntity} to a {@link StudentView}.
     *
     * @param entity The {@link StudentEntity} to be transformed.
     * @return The transformed {@link StudentView} or null if the given entity is null.
     */
    public StudentView transform(StudentEntity entity){
        StudentView result = null;
        if(entity != null){
            result = StudentView.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .emailAddress(entity.getEmailAddress())
                    .build();
        }
        return result;
    }

    /**
     * Transforms a single {@link StudentView} to a {@link StudentEntity}.
     *
     * @param request The {@link StudentView} to be transformed.
     * @return The transformed {@link StudentEntity} or null if the given entity is null.
     */
    /*public StudentEntity transform(CreateStudentRequest request){
        StudentEntity result = null;
        if(request != null){
            result = new StudentEntity();
            result.setEmailAddress(request.getEmailAddress());
            result.setStudentName(request.getStudentName());
            //result.setShoppingList(transformer.transformToEntity(request.getShoppingList()));
        }
        return result;
    }*/
}
