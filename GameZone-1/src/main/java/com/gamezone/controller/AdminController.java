package com.gamezone.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gamezone.dao.GamerDAO;
import com.gamezone.dao.GamesDAO;
import com.gamezone.dao.ScoresDAO;
import com.gamezone.dao.UniversityDAO;
import com.gamezone.pojo.Games;
import com.gamezone.pojo.Scores;
import com.gamezone.pojo.University;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String handleGet() {
		return "qwerty";
	}

	@GetMapping("/admin/getuniv")
	public ModelAndView getAllUniversities(ModelMap model, UniversityDAO univDAO, ModelAndView mv) throws Exception {
		List<University> univList = univDAO.getAllUniv();
		mv.addObject("univList", univList);
		mv.setViewName("univ-games");
		return mv;
	}

	@PostMapping("/admin/adduniv.htm")
	public ModelAndView addUnivPOST(@ModelAttribute("university") University university, UniversityDAO univDAO,
			BindingResult result, SessionStatus status, ModelMap model, HttpServletRequest request, ModelAndView mv)
			throws Exception {
		university.setUnivName(request.getParameter("universityName"));
		univDAO.saveUniversity(university);
		List<University> univList = univDAO.getAllUniv();

		mv.addObject("univList", univList);
		mv.setViewName("univ-list");

		return mv;
	}

	@GetMapping("/admin/getGamesList")
	@ResponseBody
	public List<Games> getGamesList(@RequestParam("univId") int univId, GamesDAO gamesDAO) {
		return gamesDAO.getGamesByUniv(univId);
	}

	@PostMapping("/admin/addgames.htm")
	public String addGamesPOST(@ModelAttribute("games") Games games, UniversityDAO univDAO, GamesDAO gamesDao,
//    		@RequestParam("game") String game, 
			BindingResult result, HttpServletRequest request, 
			SessionStatus status, ModelAndView mv) throws Exception {

		games.setGameName(request.getParameter("gameName"));
		games.setAvailSlots(Integer.parseInt(request.getParameter("availSlots")));
		games.setLocation(request.getParameter("location"));
//    	games.setUnivId(Integer.parseInt(request.getParameter("univId")));
		int univId = Integer.parseInt(request.getParameter("univId"));
		games.setUniversity(univDAO.getUniversity(univId));
		gamesDao.saveGames(games);

		if (result.hasErrors()) {
			return "addGamesView";
		}
		// instantiate Hibernate objects, and save user
		try {
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		// mark it complete
		status.setComplete();
		return "home";
	}
	
	@GetMapping("/games/getScoresForGame.htm")
	public void getScoresForGame(ModelAndView mv, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			ScoresDAO scoresDAO,
			GamesDAO gamesDAO) throws Exception {

		int gameId = Integer.parseInt(request.getParameter("gameId"));

		List<Scores> scoreList = scoresDAO.getScoresByGame(gameId);

		request.setAttribute("scoreList", scoreList);
//		mv.setViewName("game-booking");
//		return "redirect:/gamer/getAllGames.htm";
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gamer/getAllGames.htm");
	    dispatcher.forward(request, response);

	}


}
