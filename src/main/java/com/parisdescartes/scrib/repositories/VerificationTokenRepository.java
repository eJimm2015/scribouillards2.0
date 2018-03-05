package com.parisdescartes.scrib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.entities.VerificationToken;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>{
	VerificationToken findByOwner(User user);
	VerificationToken findByToken(String token);
}
