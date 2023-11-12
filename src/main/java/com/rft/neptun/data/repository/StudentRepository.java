package com.rft.neptun.data.repository;

import com.rft.neptun.data.domain.StudentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to get {@link StudentEntity} objects.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findStudentsByExamsId(Long examId);
}
