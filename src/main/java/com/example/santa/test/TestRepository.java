package com.example.santa.test;

import com.example.santa.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Mountain, Long> {
}
