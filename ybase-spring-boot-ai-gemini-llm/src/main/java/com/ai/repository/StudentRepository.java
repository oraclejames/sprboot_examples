package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {}