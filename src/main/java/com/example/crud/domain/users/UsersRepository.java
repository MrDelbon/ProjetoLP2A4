package com.example.crud.domain.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, String> {
    List<Users> findAllByActiveTrue();
    List<Users> findAllByType(String type);

    @Query("SELECT u FROM users u WHERE u.location_region = :region")
    List<Users> findAllByLocationRegion(String region);
}
