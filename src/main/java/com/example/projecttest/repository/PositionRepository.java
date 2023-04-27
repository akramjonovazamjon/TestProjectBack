package com.example.projecttest.repository;

import com.example.projecttest.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    boolean existsByName(String name);
    Optional<Position> findByName(String name);
}
