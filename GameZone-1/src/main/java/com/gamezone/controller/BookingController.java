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

//	@GetMapping("/booking/bookgames.htm")
//	public ModelAndView addBookingGET(ModelAndView mv, HttpServletRequest request, UniversityDAO univDAO,
//			GamesDAO gamesDAO) throws Exception {
//
//		List<University> univList = univDAO.getAllUniv();
//		List<Games> gamesList = gamesDAO.getAllGames();
//
//		mv.addObject("univList", univList);
//		mv.addObject("gamesList", gamesList);
////		mv.setViewName("game-booking");
//		mv.setViewName("univ-list");
//		return mv;
//	}

	@PostMapping("/booking/createBooking.htm")
	public String addBookingPOST(GamesDAO gamesDAO, BookingDAO bookingDAO, BindingResult result,
			HttpServletRequest request, ModelAndView mv, Model model, SessionStatus status) throws Exception {

		Booking booking = new Booking();
		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");
			booking.setGamer(gamer);
		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));

		Games games = gamesDAO.getGame(gameId);
		int availSlots = games.getAvailSlots();
		if (availSlots < 1) {
			session.setAttribute("message", "cannot make the booking as no slots available");

		} else {

			games.setAvailSlots(games.getAvailSlots() - 1);
			booking.setGames(games);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingDate = request.getParameter("bookDate");
			Date bookDate = new Date();
			bookDate = dateFormat.parse(bookingDate);
			booking.setBookDate(bookDate);
			
			booking.setSlot(request.getParameter("slot"));
			
			String zoneBook = request.getParameter("zoneBooking");
			if(zoneBook.equals("on")) {
				boolean zoneBooking = true;
			    booking.setZoneBooking(zoneBooking);
			}
		    
		    

		    String nameOfZone = request.getParameter("nameOfZone");
		    booking.setNameOfZone(nameOfZone);


			gamesDAO.updateGamesByObj(games);
			bookingDAO.saveBooking(booking);
			session.setAttribute("message", "Booking success");
			System.out.println();

		}
//		mv.setViewName("games-list");
		return "redirect:/gamer/getAllGames.htm";
	}

	@PostMapping("/booking/cancelBooking.htm")
	public String cancelBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO,
			GamesDAO gamesDAO) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		Booking booking = bookingDAO.getBooking(bookingId);
		bookingDAO.deleteBooking(booking);
		
		session.setAttribute("message", "Cancelled Booking");
		return "redirect:/gamer/gamer-booking-list.htm";
	}
	
	@GetMapping("/booking/modifyBooking.htm")
	public String modifyBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO,
			GamesDAO gamesDAO) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));

		Booking booking = bookingDAO.getBooking(bookingId);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bookingDate = request.getParameter("bookDate");
		Date bookDate = new Date();
		bookDate = dateFormat.parse(bookingDate);
		booking.setBookDate(bookDate);
		
		bookingDAO.updateBookingByObj(booking);
		
		session.setAttribute("message", "Booking Modified");
		return "redirect:/gamer/gamer-booking-list.htm";
	}
	
	@GetMapping("/booking/joinBooking.htm")
	public String joinBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO,
			GamesDAO gamesDAO,
			Gamer gamer) throws Exception {
		
		

		HttpSession session = request.getSession(false);
		if (session != null) {
			gamer = (Gamer) session.getAttribute("loggedGamer");
			

		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));

		List<Booking> bookingList = bookingDAO.getBookingForZoning(gameId,gamer.getGamerId());
		
//		Set<Gamer> zoners = booking.getZoners();
//		zoners.add(gamer);
//		
//		booking.setZoners(zoners);
//			bookingDAO.updateBookingByObj(booking);
		request.setAttribute("bookingList", bookingList);
		
		return "join-booking";
	}
	
	@GetMapping("/booking/gamer/joinBooking.htm")
	public String gamerJoinBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO,
			GamesDAO gamesDAO,
			Gamer gamer) throws Exception {
		
	
		HttpSession session = request.getSession(false);
		if (session != null) {
			gamer = (Gamer) session.getAttribute("loggedGamer");
			

		}
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));

		Booking booking = bookingDAO.getBooking(bookingId);
		
		List<Gamer> zoners = booking.getZoners();
		zoners.add(gamer);
		
		booking.setZoners(zoners);
			bookingDAO.updateBookingByObj(booking);
			return "redirect:/booking/joinBooking.htm";
	}
	
	@GetMapping("/booking/modifyBookingScore.htm")
	public String modifyBookingScore(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO,
			GamesDAO gamesDAO) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		int scored = Integer.parseInt(request.getParameter("score"));
		
		Booking booking = bookingDAO.getBooking(bookingId);
		booking.setScore(scored);
		
		
		bookingDAO.updateBookingByObj(booking);
		
		session.setAttribute("message", "Booking Modified");
		return "redirect:/gamer/gamer-booking-list.htm";
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

}
