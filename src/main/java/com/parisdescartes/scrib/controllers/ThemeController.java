package com.parisdescartes.scrib.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.parisdescartes.scrib.entities.Article;
import com.parisdescartes.scrib.entities.Categorie;
import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.exceptions.ThemeInexistantException;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.tools.Constante;

@Controller
public class ThemeController {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	HttpSession session;

	@ModelAttribute
	@GetMapping(value="theme")
	public ModelAndView consulterTheme(@RequestParam("id") int id, @RequestParam(value = "date", defaultValue="newest") String date, @RequestParam(value = "voted", defaultValue="most") String voted) {
		
		ModelAndView model = new ModelAndView();
		try {
			Theme theme = blogService.findTheme(id);
			if(!Constante.OLDEST.equals(date)) {
				theme.getArticles().sort((t1, t2) -> 
					 t2.getId() - t1.getId()
				);
			}
			if(!Constante.LEAST.equals(voted)) {
				theme.getArticles().sort((t1, t2) -> 
					 t2.getVotes() - t1.getVotes()
				);
			}
			model.addObject(Constante.THEME, theme);
			Article article = new Article();
			model.addObject(Constante.ARTICLE, article);
			model.addObject(Constante.USER, (User)session.getAttribute(Constante.USER));
			model.setViewName(Constante.THEME);
			return model;
		} catch (ThemeInexistantException e) {
			model.setViewName(Constante.THEMES_ACCUEIL);
			return model;
		}
	}
	
	@PostMapping(value="/themes")
	public ModelAndView ajouterTheme(@Valid Theme theme, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if(result.hasErrors()) {
			model.setViewName(Constante.ACCUEIL_ERREUR);
			return model;
		}
		theme
		.setAuteur((User)session.getAttribute(Constante.USER))
		.setCategorieColor(Categorie.getCategoryColor(theme.getCategorie()));
		blogService.addTheme(theme);
		model.setViewName(Constante.ACCUEIL);
		return model;
	}

}
