package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;


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
}



//  public void setStateDAO(StateDAO stateDAO) {
//    this.stateDAO = stateDAO;
//  }
//
//  @RequestMapping(path = "GetStateData.do", params = "name", method = RequestMethod.GET)
//  public ModelAndView getStateByName(String name) {
//    ModelAndView mv = new ModelAndView();
//    State s = stateDAO.getStateByName(name);
//    mv.addObject("state", s);
//    mv.setViewName("result");
//    return mv;
//  }
//
//  @RequestMapping(path = "GetStateData.do", params = "abbr", method = RequestMethod.GET)
//  public ModelAndView getStateByAbbreviation(String abbr) {
//    ModelAndView mv = new ModelAndView();
//    State s = stateDAO.getStateByAbbreviation(abbr);
//    mv.addObject("state", s);
//    mv.setViewName("result");
//    return mv;
//  }
//
//  @RequestMapping(path = "NewState.do", method = RequestMethod.POST)
//  public ModelAndView newState(State state) {
//    stateDAO.addState(state);
//    ModelAndView mv = new ModelAndView();
//    mv.setViewName("result");
//    return mv;
//  }

  // TODO : Implement another request handler for:
  // path "NewState.do"
  // method POST
  // command object : State
  // return : ModelAndView
  // view : "redirect:stateAdded.do"
  // behavior : add state to dao, add state to flashAttributes
  // Note: Comment out the other "NewState.do" request handler method
//  @RequestMapping(path = "NewState.do", method = RequestMethod.POST)
//  public String newState(State state, RedirectAttributes redir) {
//    stateDAO.addState(state);
//    redir.addFlashAttribute("state", state);
//    return "redirect:stateAdded.do";
//  }
//
//  // TODO : Implement a request handler for:
//  // path "stateAdded.do"
//  // method GET
//  // command object : State
//  // return : ModelAndView
//  // view : "WEB-INF/result.jsp", "WEB-INF/result", or "result" if using
//  // InternalResourceViewResolver
//  // Note: fix other request handler methods to use InternalResourceViewResolver
//  @RequestMapping("stateAdded.do")
//  public ModelAndView stateAdded() {
//    ModelAndView mv = new ModelAndView();
//    // This uses InternalResourceViewResolver with WEB-INF and .jsp as the prefix
//    // and suffix
//    mv.setViewName("result");
//    return mv;
//  }


