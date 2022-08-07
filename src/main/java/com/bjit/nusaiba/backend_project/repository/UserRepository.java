package com.bjit.nusaiba.backend_project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.bjit.nusaiba.backend_project.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
