/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gamezone.controller;

import com.gamezone.dao.BookingDAO;
import com.gamezone.dao.GamesDAO;
import com.gamezone.dao.UniversityDAO;
import com.gamezone.pojo.Booking;
import com.gamezone.pojo.Gamer;
import com.gamezone.pojo.Games;
import com.gamezone.pojo.University;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.*;

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
public class BookingController {

//	@GetMapping("/booking/add.htm")
//	public String addBookingGET(ModelMap model, Booking booking) throws Exception {
//		model.addAttribute("booking", booking);
//		return "game-booking";
//	}

	@GetMapping("/booking/bookgames.htm")
	public ModelAndView addBookingGET(ModelAndView mv, HttpServletRequest request, UniversityDAO univDAO,
			GamesDAO gamesDAO) throws Exception {

		List<University> univList = univDAO.getAllUniv();
		List<Games> gamesList = gamesDAO.getAllGames();

		mv.addObject("univList", univList);
		mv.addObject("gamesList", gamesList);
//		mv.setViewName("game-booking");
		mv.setViewName("univ-list");
		return mv;
	}

	@PostMapping("/booking/add.htm")
	public String addBookingPOST( 
			GamesDAO gamesDAO,
			BookingDAO bookingDAO,
			BindingResult result, HttpServletRequest request, 
			ModelAndView mv,
			Model model,
			SessionStatus status) throws Exception {

		HttpSession session = request.getSession(false);

		Booking booking = new Booking();
		if (session != null) {
		Gamer	gamer = (Gamer) session.getAttribute("loggedGamer");
		booking.setGamer(gamer);
		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		System.out.println("Game ID----------------------------------");
		System.out.println(gameId);
		System.out.println("-----------------------------------");
	
		Games games = gamesDAO.getGame(gameId);
		int availSlots = games.getAvailSlots();
		if(availSlots<1) {
			model.addAttribute("message","cannot make the booking as no slots available");
			
		}
		else {
		
		games.setAvailSlots(games.getAvailSlots()-1);
		gamesDAO.updateGamesByObj(games);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bookingDate = request.getParameter("bookDate");
		
		Date bookDate = new Date();
		bookDate = dateFormat.parse(bookingDate);
		System.out.println("Date -----------------------------------");
		System.out.println(bookDate);
		System.out.println("-----------------------------------");
		
		booking.setGames(games);
		booking.setBookDate(bookDate);
		booking.setSlot(request.getParameter("slot"));
		
		bookingDAO.saveBooking(booking);
		model.addAttribute("message","Booking success");
		System.out.println();

		}
//		mv.setViewName("games-list");
		return "redirect:/gamer/getAllGames.htm";
	}

}
