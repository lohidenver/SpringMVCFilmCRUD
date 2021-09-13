package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Category;


@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "home";
	}

	@RequestMapping(path = "GetFilmsId.do", method = RequestMethod.POST)
	public ModelAndView findFilmId(int filmId) {
		Film film = filmDAO.findById(filmId);
		ModelAndView mv = new ModelAndView();
		if (film != null) {
			mv.addObject(film);
		}
		mv.setViewName("getfilmid");
		return mv;
	}
	
//	@RequestMapping(path = "GetFilmsByKeyword.do", method = RequestMethod.POST)
//	public ModelAndView findFilmK(String keyword) {
//		List<Film> filmList = filmDAO.findFilmByKeyword(keyword);
//		ModelAndView mv = new ModelAndView();
//		if (filmList != null) {
//			mv.addObject(filmList);
//		}
//		mv.setViewName("filmkeyword");
//		return mv;
//	}
	
	@RequestMapping(path = "keywordLookup.do")
	public ModelAndView keywordLookup(String keyword) {
		List<Film> films = filmDAO.findFilmByKeyword(keyword);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmkeyword");
		mv.addObject("films", films);
		return mv;
	}
	
	@RequestMapping(path = "AddFilm.do" , method = RequestMethod.POST)
	public ModelAndView createFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		Film checkFilm = filmDAO.createFilm(film);
		redir.addFlashAttribute("film", checkFilm);
		mv.setViewName("redirect:filmCreated.do");
		return mv;
	}
	
	
	@RequestMapping(path = "filmCreated.do", method = RequestMethod.GET)
	public ModelAndView created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmadded");
		return mv;
	}
	
	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film localFilm = null;
		localFilm = filmDAO.findById(filmId);
		if (localFilm != null) {
			filmDAO.deleteFilm(localFilm);
			mv.addObject(localFilm);
		}
		mv.setViewName("filmDeleted");
		return mv;
	}

	@RequestMapping(path = "filmGettingUpdate.do")
	public ModelAndView filmToUpdate(int filmId) {
		Film film = filmDAO.findById(filmId);
		ModelAndView mv = new ModelAndView();
		if (film != null)
			mv.addObject(film);
		mv.setViewName("updatefilmdatabase");
		return mv;
	}

	@RequestMapping(path = "updateFilmForm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(@RequestParam("filmId") int filmID, Film film) {
		ModelAndView mv = new ModelAndView();
		System.out.println(film);
		film.setId(filmID);
		System.out.println(film.getId());
		Film newFilm = filmDAO.updateFilm(film);
		mv.setViewName("updatechosenfilm");
		if (newFilm == null) {
			newFilm = new Film();
			newFilm.setId(-1);
		}
		mv.addObject("film", newFilm);
		return mv;
	}
	@RequestMapping(path = "idLookup.do", method = RequestMethod.POST)
	public ModelAndView idLookup(int filmId) {
		Film film = filmDAO.findById(filmId);
		List<Actor> actors = filmDAO.findActorsByFilmId(filmId);
		List<Category> categories = filmDAO.findCategoriesByFilmId(filmId);
		ModelAndView mv = new ModelAndView();
		if (film != null) {
			mv.addObject(film);
			mv.addObject("actors", actors);
			mv.addObject("categories", categories);
		}
		mv.setViewName("viewfilm");
		return mv;
	}
	
}


