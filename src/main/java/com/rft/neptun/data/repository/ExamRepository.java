package com.rft.neptun.data.repository;

import com.rft.neptun.data.domain.ExamEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to get {@link ExamEntity} objects.
 */
@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    List<ExamEntity> findExamsByStudentsId(Long studentId);
}
