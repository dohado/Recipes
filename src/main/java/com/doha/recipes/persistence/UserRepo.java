package com.doha.recipes.persistence;

import com.doha.recipes.business.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    public Optional<User> findUserByEmail(String email);
    public boolean existsByEmail(String email);

}
