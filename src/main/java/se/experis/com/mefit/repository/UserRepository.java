package se.experis.com.mefit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.experis.com.mefit.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
