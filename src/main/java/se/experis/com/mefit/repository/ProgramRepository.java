package se.experis.com.mefit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.experis.com.mefit.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

}
