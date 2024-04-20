package com.example.santa.domain.userchallenge.repository;

import com.example.santa.domain.challege.entity.Challenge;
import com.example.santa.domain.user.entity.User;
import com.example.santa.domain.userchallenge.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserChallengeRepository extends JpaRepository<UserChallenge,Long> {
    Optional<UserChallenge> findByUserAndChallenge(User user, Challenge challenge);
}
