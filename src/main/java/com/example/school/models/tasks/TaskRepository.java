package com.example.school.models.tasks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM tasks t WHERE " +
            "(:type IS NULL OR t.type = :type) AND " +
            "(:level IS NULL OR t.level = :level) AND " +
            "(:subject IS NULL OR t.subject = :subject)")
    List<Task> findByTypeAndLevelAndSubject(@Param("type") String type, @Param("level") String level, @Param("subject") String subject);
}
