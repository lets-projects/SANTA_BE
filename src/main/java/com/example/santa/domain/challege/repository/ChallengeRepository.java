package com.example.santa.domain.challege.repository;

import com.example.santa.domain.challege.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge,Long> {

//    Page<Challenge> findChallengeById(Long id, Pageable pageable);
}
