package com.parisdescartes.scrib.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.tools.Constante;

@Controller
@SessionAttributes("theme")
public class Signin {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/")
	public ModelAndView signin() {
		ModelAndView model = new ModelAndView();
		model.addObject(Constante.USER, new User());
		model.setViewName(Constante.CONNEXION);
		return model;
	}
	
	@GetMapping(value="/themes")
	public ModelAndView afficherAccueil(@RequestParam(value = "fetch", defaultValue="ALL") String fetch) {
		ModelAndView model = new ModelAndView();
		User user = (User)session.getAttribute(Constante.USER);
		List<Theme> themes = new ArrayList<>();
		if(fetch.equalsIgnoreCase(Constante.FETCH_ALL)) {
			themes = blogService.getThemes();
		}
		else if(fetch.equalsIgnoreCase(Constante.FETCH_SELF)) {
			themes = blogService.findThemesByAuteur(user);
		}
		model.addObject(Constante.USER,user);
		model.addObject(Constante.THEMES_ACCUEIL, themes);
		model.addObject(Constante.THEME, new Theme());
		model.setViewName(Constante.THEMES_ACCUEIL);
		return model;
	}

}
