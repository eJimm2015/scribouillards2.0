package com.parisdescartes.scrib.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer>{
	List<Theme> findByAuteur(User user);
}
