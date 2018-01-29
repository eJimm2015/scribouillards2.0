package com.parisdescartes.scrib.controllers;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import com.parisdescartes.scrib.controllers.ArticleController;
import com.parisdescartes.scrib.entities.Article;
import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.exceptions.ArticleInexistantException;
import com.parisdescartes.scrib.exceptions.DejaVoteException;
import com.parisdescartes.scrib.service.BlogService;

public class ArticleControllerTest {
	
	@InjectMocks
	private ArticleController articleController;
	
	@Mock
	private BlogService bs;
	
	@Mock
	private BindingResult br;
	
	@Mock
	private Article article;
	
	@Mock
	private HttpSession session;
	
	@Mock
	User u;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(session.getAttribute("user")).thenReturn(u);
		Mockito.when(u.getId()).thenReturn(1);
	}

	@Test
	public void ajouterArticleTest_HasErrors() {
		Mockito.when(br.hasErrors()).thenReturn(true);
		assertEquals("redirect:theme?error=true&id=1",articleController.ajouterArticle(1, article, br).getViewName());
	}
	
	@Test
	public void ajouterArticleTest_NoError() {
		Theme th = new Theme();
		th.setId(2);
		Article article = new Article();
		article.setAuteur(new User());
		article.setTheme(th);
		
		Mockito.when(br.hasErrors()).thenReturn(false);
		
		assertEquals("redirect:theme?id=2",articleController.ajouterArticle(2, article, br).getViewName());
	}
	
	public void supprimerArticleTest() {
		assertNotNull(articleController.supprimerArticle(1, 1));
		
		assertEquals("redirect:theme?id=1",articleController.supprimerArticle(1, 1).getViewName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void evaluerArticleTest_ThrowArticleInexistantException() throws ArticleInexistantException, DejaVoteException {
		Mockito.when(bs.evaluerArticle(1, 1, 1)).thenThrow(ArticleInexistantException.class);
		assertEquals("redirect:theme?id=1",articleController.evaluerArticle(1, 1, 1).getViewName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void evaluerArticleTest_ThrowDejaVoteException() throws ArticleInexistantException, DejaVoteException {
		Mockito.when(bs.evaluerArticle(1, 1, 1)).thenThrow(DejaVoteException.class);
		assertEquals("redirect:theme?id=1#1",articleController.evaluerArticle(1, 1, 1).getViewName());
	}

}
