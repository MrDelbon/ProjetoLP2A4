package com.example.crud.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {
    List<Users> findAllByActiveTrue();
    List<Users> findAllByType(String type);

}
