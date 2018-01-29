package com.parisdescartes.scrib.service;

import java.util.List;

import com.parisdescartes.scrib.entities.Article;
import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.exceptions.*;

public interface BlogService {
	User addUser(User user);
	User findUserByEmail(String email);
	User findUserByID(int id);
	void addTheme(Theme theme);
	List<Theme> getThemes();
	List<Theme> findThemesByAuteur(User user);
	Theme findTheme(int id) throws ThemeInexistantException;
	void addArticle(Article article);
	int evaluerArticle(int userId, int aId, int val) throws ArticleInexistantException, DejaVoteException;
	void supprimerArticle(int id);
	void supprimerTheme(int id);	
}
