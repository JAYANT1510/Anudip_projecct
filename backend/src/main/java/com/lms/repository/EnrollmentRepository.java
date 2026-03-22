package com.lms.repository;

import com.lms.model.Enrollment;
import com.lms.model.User;
import com.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
    Optional<Enrollment> findByUserAndCourse(User user, Course course);
}
