package com.example.santa.domain.mountain.repository;

import com.example.santa.domain.mountain.entity.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Mountain, Long> {
}
