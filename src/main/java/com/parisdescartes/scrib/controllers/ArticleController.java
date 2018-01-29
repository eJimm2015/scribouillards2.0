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
import com.parisdescartes.scrib.entities.Theme;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.exceptions.ArticleInexistantException;
import com.parisdescartes.scrib.exceptions.DejaVoteException;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.tools.Constante;

@Controller
public class ArticleController {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	HttpSession session;
	
	@PostMapping(value="theme")
	public ModelAndView ajouterArticle(@RequestParam("tId") int id, @ModelAttribute("article") @Valid Article article ,BindingResult result) {
		ModelAndView model = new ModelAndView();
		if(result.hasErrors()) {
			model.setViewName(Constante.THEME_SELECT_ERREUR+id);
			return model;
		}
		article
		.setAuteur((User)session.getAttribute(Constante.USER))
		.setTheme(new Theme())
		.getTheme().setId(id);
		blogService.addArticle(article);
		model.setViewName(Constante.THEME_SELECT+id);
		return model;
	}
	
	@GetMapping(value="deleteArticle")
	public ModelAndView supprimerArticle(@RequestParam("tId") int tId, @RequestParam("aId") int aId) {
		ModelAndView model = new ModelAndView();
		blogService.supprimerArticle(aId);
		model.setViewName(Constante.THEME_SELECT+tId);
		return model;
	}
	
	@GetMapping(value="evalArticle")
	public ModelAndView evaluerArticle(@RequestParam("tId") int tId, @RequestParam("aId") int aId, @RequestParam int val) {
		ModelAndView model = new ModelAndView();
		try {
			blogService.evaluerArticle(((User)session.getAttribute("user")).getId(), aId, val);
		} catch (ArticleInexistantException e) {
			model.setViewName(Constante.THEME_SELECT+tId);
			return model;
		} catch (DejaVoteException e) {
		model.setViewName(Constante.THEME_SELECT+tId+"#"+aId);
		return model;
		}
		model.setViewName(Constante.THEME_SELECT+tId+"#"+aId);
		return model;
	}

}
