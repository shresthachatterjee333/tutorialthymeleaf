package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

}
