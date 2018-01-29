package com.parisdescartes.scrib.service.impl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.parisdescartes.scrib.entities.Article;
import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.exceptions.ArticleInexistantException;
import com.parisdescartes.scrib.exceptions.DejaVoteException;
import com.parisdescartes.scrib.exceptions.ThemeInexistantException;
import com.parisdescartes.scrib.repositories.ArticleRepository;
import com.parisdescartes.scrib.repositories.ThemeRepository;
import com.parisdescartes.scrib.repositories.UserRepository;
import com.parisdescartes.scrib.service.impl.BlogServiceImpl;

public class BlogServiceImplTest {
	
	@InjectMocks
	BlogServiceImpl bsi;
	
	@Mock
	private ThemeRepository themeRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ArticleRepository articleRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	
	@Mock
	private User alex;
	
	private User user;
	private Theme theme;
	
	@Mock
	private Article article;
	
	@Mock
	SimpleDateFormat dateFormat;
	
	@Mock
	ArrayList<User> listeVoteurs;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		user = new User();
		user.setId(1);
		theme = new Theme();
		
		ArrayList<Theme> themes = new ArrayList<Theme>();
		
		themes.add(new Theme());
		themes.add(new Theme());

		Mockito.when(themeRepository.findByAuteur(alex))
		.thenReturn(new ArrayList<Theme>());
		
		Mockito.when(alex.getPassword()).thenReturn("password");
		
		Mockito.when(passwordEncoder.encode(alex.getPassword()))
		.thenReturn("password_encoded");
		
		Mockito.when(userRepository.save(alex)).thenReturn(user);
		Mockito.when(userRepository.findByEmail("mail@mail.fr")).thenReturn(alex);
		Mockito.when(themeRepository.save(theme)).thenReturn(theme);
		Mockito.when(userRepository.findOne(1)).thenReturn(user);
		
		Mockito.when(themeRepository.findAll()).thenReturn(themes);
		Mockito.when(articleRepository.save(new Article())).thenReturn(new Article());
		
		

	}
	
	//@Test
	public void addUserTest() {
		User u = new User();
		assertEquals(u,bsi.addUser(alex));
	}
	
	@Test
	public void findUserByEmailTest() {
		assertEquals(alex,bsi.findUserByEmail("mail@mail.fr"));
	}
	
	//@Test 
	public void addThemeTest() {
		bsi.addTheme(theme);
	}
	
	@Test
	public void findUserByIDTest() {
		assertEquals(user,bsi.findUserByID(1));
	}
	
	@Test
	public void getThemesTest() {
		assertEquals(2,bsi.getThemes().size());
	}
	
	
	@Test
	public void findThemesByAuteurTest() {
		assertEquals(new ArrayList<Theme>(),bsi.findThemesByAuteur(alex));
	}
	
	@Test(expected = ThemeInexistantException.class)
	public void findThemeTest_ThrowException() throws ThemeInexistantException {
		Mockito.when(themeRepository.findOne(1)).thenReturn(null);
		assertEquals(null,bsi.findTheme(1));
	}
	
	@Test
	public void findThemeTest() throws ThemeInexistantException {
		Mockito.when(themeRepository.findOne(1)).thenReturn(theme);
		assertEquals(theme,bsi.findTheme(1));
	}
	
	//@Test
	public void addArticleTest() {
		bsi.addArticle(new Article());
	}
	
	@Test
	public void evaluerArticleTest_NoError() throws ArticleInexistantException, DejaVoteException {
		Mockito.when(articleRepository.findOne(1)).thenReturn(article);
		Mockito.when(article.getVotes()).thenReturn(3);
		
		assertEquals(3,bsi.evaluerArticle(1, 1, 1));
	}
	
	@Test(expected = ArticleInexistantException.class)
	public void evaluerArticleTest_ThrowArticleInexistantException() throws ArticleInexistantException, DejaVoteException {
		Mockito.when(articleRepository.findOne(1)).thenReturn(null);
		bsi.evaluerArticle(1, 1, 1);
	}
	
	@Test(expected = DejaVoteException.class)
	public void evaluerArticleTest_ThrowDejaVoteException() throws ArticleInexistantException, DejaVoteException {
		ArrayList<User> list = new ArrayList<User>();
		
		User u = new User();
		u.setId(1);
		
		Mockito.when(articleRepository.findOne(1)).thenReturn(article);
		Mockito.when(article.getVoteurs()).thenReturn(list);
		
		list.add(u);
		
		bsi.evaluerArticle(1, 1, 1);
	}
	
	@Test
	public void supprimerArticleTest() {
		bsi.supprimerArticle(1);
		Mockito.verify(articleRepository,Mockito.times(1)).delete(1);
	}
	
	@Test
	public void supprimerThemeTest() {
		bsi.supprimerTheme(1);
		Mockito.verify(themeRepository, Mockito.times(1)).delete(1);
	}

}
