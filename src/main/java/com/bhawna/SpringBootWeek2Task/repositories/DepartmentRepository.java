package com.bhawna.SpringBootWeek2Task.repositories;

import com.bhawna.SpringBootWeek2Task.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
