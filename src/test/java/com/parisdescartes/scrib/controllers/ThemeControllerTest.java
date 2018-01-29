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

import com.parisdescartes.scrib.controllers.ThemeController;
import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.exceptions.ThemeInexistantException;
import com.parisdescartes.scrib.service.BlogService;

public class ThemeControllerTest {
	
	@InjectMocks
	private ThemeController themeController;
	
	@Mock
	private BlogService bs;
	
	@Mock
	BindingResult br;
	
	@Mock
	private HttpSession session;
	
	@Before 
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(session.getAttribute("user")).thenReturn(new User());
	}
	
	@Test
	public void ajouterThemeTest_HasErrors() {
		Mockito.when(br.hasErrors()).thenReturn(true);
		assertEquals("redirect:themes?error=true",themeController.ajouterTheme(new Theme(),br).getViewName());
	}
	
	@Test()
	public void ajouterThemeTest_NoError() {
		Theme theme = new Theme();
		theme.setCategorie("Environnement");
		Mockito.when(br.hasErrors()).thenReturn(false);
		assertEquals("redirect:themes",themeController.ajouterTheme(theme,br).getViewName());
	}
	
	@Test
	public void consulterThemeTest_ThemeExistant() {	
	assertEquals("theme",themeController.consulterTheme(1,"oldest","least").getViewName());
	}
	
	@Test
	public void consulterThemeTest_ThemeNonExistant() throws ThemeInexistantException {
		Mockito.when(bs.findTheme(1)).thenThrow(new ThemeInexistantException("theme n'existe pas"));
		assertEquals("themes",themeController.consulterTheme(1,"","").getViewName());
	}

}
