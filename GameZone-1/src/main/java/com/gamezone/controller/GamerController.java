/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gamezone.controller;

import com.gamezone.dao.BookingDAO;
import com.gamezone.dao.GamerDAO;
import com.gamezone.dao.GamesDAO;
import com.gamezone.dao.ScoresDAO;
import com.gamezone.dao.UniversityDAO;
import com.gamezone.pojo.Booking;
import com.gamezone.pojo.Gamer;
import com.gamezone.pojo.Games;
import com.gamezone.pojo.Scores;
import com.gamezone.pojo.University;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ritvikparamkusham
 */

@Controller
public class GamerController {

//     @Autowired
//    private UserValidator uservalidator;

	// Gamer Registration
//	@GetMapping("/gamerGet/registration.htm")
//	public String addGamerGET(ModelMap model, Gamer gamer, GamerDAO gamerDao, UniversityDAO univDAO) throws Exception {
//		List<University> univList = univDAO.getAllUniv();
//		model.addAttribute("univList", univList);
//		gamer = gamerDao.getGamer(1);
//		model.addAttribute("gamer", gamer);
//		return "gamer-registration";
//	}

	@GetMapping("/gamer/login.htm")
	public ModelAndView displayLogin(@ModelAttribute("gamer") Gamer gamer, ModelMap model, GamerDAO gamerDao,
			UniversityDAO univDAO, HttpServletRequest request, ModelAndView mv) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		gamer = (Gamer) session.getAttribute("loggedGamer");
		if(gamer!=null) {
			mv.addObject("gamer", gamer);
			mv.setViewName("home");
		}else {
			
		
		List<University> univList = univDAO.getAllUniv();
		model.addAttribute("univList", univList);
//		mv.addObject("gamer", gamer);
		mv.setViewName("login");
		}

		return mv;
	}

	@PostMapping("/gamer/home.htm")
	public ModelAndView showHome(@ModelAttribute("gamer") Gamer gamer, ModelMap model, GamerDAO gamerDao,
			UniversityDAO univDAO, HttpServletRequest request, ModelAndView mv) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		gamer = gamerDao.getGamerAtLogin(email, password);
		if (gamer != null) {
			session.setAttribute("loggedGamer", gamer);
			mv.setViewName("home");
		} else {
			mv.addObject("error", "No GAMER found");
			mv.setViewName("login");
		}

		return mv;
	}

	// on gamer registration
	@PostMapping("/gamer/register.htm")
	public String addGamerPOST(@ModelAttribute("gamer") Gamer gamer, UniversityDAO univDAO, BindingResult result,
			SessionStatus status, GamerDAO gamerDao, ModelAndView mv, HttpServletRequest request) throws Exception {
		gamer.setEmail(request.getParameter("email"));
		gamer.setPassword(request.getParameter("password"));
		gamer.setGamerName(request.getParameter("gamerName"));
		int univId = Integer.parseInt(request.getParameter("univId"));

		gamer.setUniversity(univDAO.getUniversity(univId));

		gamerDao.saveGamer(gamer);
		mv.setViewName("login");
		if (result.hasErrors())
//            return "gamer";
			// instantiate Hibernate objects, and save user
			try {
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
		// mark it complete
		status.setComplete();
//		return mv;
		return "redirect:/gamer/login.htm";
	}
	
    @GetMapping(value = "/gamer/logout.htm")
	public String logout(HttpServletRequest request, ModelAndView mv,
			@ModelAttribute("gamer") Gamer gamer) {
		request.getSession(false).invalidate();
//		HttpSession session = request.getSession(false);

//		if (session != null) {
//			gamer  = (Gamer) session.getAttribute("loggedGamer");
//			if (gamer == null) {
//				mv.addObject("message", "You Logged out!!!");
//			} else {
//				request.getSession(false).invalidate();
//			}
//
//		}
		return "redirect:/gamer/login.htm";

	}
    

    
	@GetMapping("/gamer/getAllGames.htm")
	public ModelAndView gamesGET(@ModelAttribute("games") Games games, GamesDAO gamesDAO,
			@ModelAttribute("gamer") Gamer gamer,
			BindingResult result, SessionStatus status, ModelMap model, HttpServletRequest request, ModelAndView mv)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		List<Games> gamesList = null;
		if (session != null) {
			gamer  = (Gamer) session.getAttribute("loggedGamer");
			if (gamer != null) {
			 gamesList = gamesDAO.getGamesByUniv(gamer.getUniversity().getUnivId());
			} 

		}
		
		System.out.println("");

		mv.addObject("gamesList", gamesList);
		mv.setViewName("games-list");

		return mv;
	}
	
	@GetMapping("/gamer/gamer-booking-list.htm")
	public ModelAndView viewGamerBookingList(GamesDAO gamesDAO, BookingDAO bookingDAO, BindingResult result,
			HttpServletRequest request, ModelAndView mv, Model model, SessionStatus status) {
		mv = new ModelAndView("gamer-booking-list");
		HttpSession session = request.getSession(false);
		Gamer gamer = (Gamer) session.getAttribute("loggedGamer");

		List<Booking> bookingList = bookingDAO.getBookingByGamer(gamer.getGamerId());
		mv.addObject("bookingList", bookingList);
		mv.setViewName("gamer-booking-list");
		return mv;
	}



	@GetMapping("/gamer/addScores.htm")
	public String addScoresPOST(ScoresDAO scoresDAO,
			GamesDAO gamesDAO,
			Scores score,
			HttpServletRequest request,
			BindingResult result, SessionStatus status) throws Exception {
		
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");
			score.setGamer(gamer);
		}

		int gameId = Integer.parseInt(request.getParameter("gameId"));
		Games games = gamesDAO.getGame(gameId);
		score.setGames(games);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bookingDate = request.getParameter("bookDate");
		Date bookDate = new Date();
		bookDate = dateFormat.parse(bookingDate);
		score.setScoreDate(bookDate);
		
		int scored = Integer.parseInt(request.getParameter("score"));
		score.setScore(scored);
		
		
		
		scoresDAO.saveScores(score);
		
		
		
		
		if (result.hasErrors())
			return "addScoresView";
		// instantiate Hibernate objects, and save user
		try {
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		// mark it complete
		status.setComplete();
		return "redirect:/gamer/gamer-booking-list.htm";
	}

}
