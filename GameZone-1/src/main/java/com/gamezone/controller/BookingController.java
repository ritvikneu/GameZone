/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gamezone.controller;

import com.gamezone.dao.BookingDAO;
import com.gamezone.dao.GamesDAO;
import com.gamezone.dao.UniversityDAO;
import com.gamezone.pojo.Booking;
import com.gamezone.pojo.BookingId;
import com.gamezone.pojo.Gamer;
import com.gamezone.pojo.Games;
import com.gamezone.pojo.University;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

	@PostMapping("/booking/createBooking.htm")
	public String addBookingPOST(GamesDAO gamesDAO, BookingDAO bookingDAO, Booking booking, BookingId bookingId,
			BindingResult result, HttpServletRequest request, ModelAndView mv, Model model, SessionStatus status)
			throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");
			bookingId.setGamer(gamer);
		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));

		Games games = gamesDAO.getGame(gameId);
		int availSlots = games.getAvailSlots();
		if (availSlots < 1) {
			session.setAttribute("message", "cannot make the booking as no slots available");

		} else {

			games.setAvailSlots(games.getAvailSlots() - 1);
			bookingId.setGames(games);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingDate = request.getParameter("bookDate");
			Date bookDate = new Date();
			bookDate = dateFormat.parse(bookingDate);
			bookingId.setBookDate(bookDate);

			booking.setBookingId(bookingId);

			booking.setSlot(request.getParameter("slot"));

			String zoneBook = request.getParameter("zoneBooking");
			if (zoneBook.equals("on")) {
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
	public String cancelBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO, GamesDAO gamesDAO)
			throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));

		Games games = gamesDAO.getGame(gameId);

		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		Booking booking = bookingDAO.getBooking(bookingId);
		bookingDAO.deleteBooking(booking);

		session.setAttribute("message", "Cancelled Booking");
		return "redirect:/gamer/gamer-booking-list.htm";
	}

	@GetMapping("/booking/modifyBooking.htm")
	public String modifyBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO,
			Booking booking,
			Gamer gamer,
			GamesDAO gamesDAO) throws Exception {

		BookingId bookingId = new BookingId();
		HttpSession session = request.getSession(false);
		if (session != null) {
			 gamer = (Gamer) session.getAttribute("loggedGamer");
			
		}

		int gameId = Integer.parseInt(request.getParameter("gameId"));
		Games games = gamesDAO.getGame(gameId);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bookingDate = request.getParameter("bookDate");
		String currbookingDate = request.getParameter("currBookDate");
		Date bookDate = new Date();
		Date currBookDate = new Date();
		bookDate = dateFormat.parse(bookingDate);
		currBookDate  = dateFormat.parse(currbookingDate);
//		bookingId.setBookDate(bookDate);

		booking = bookingDAO.getBookingById(gamer.getGamerId(),games.getGameId(),currBookDate);
		bookingId = booking.getBookingId();
		bookingDAO.deleteBooking(booking);
		
		bookingId.setBookDate(bookDate);
		bookingDAO.saveBooking(booking);

		session.setAttribute("message", "Booking Modified");
		return "redirect:/gamer/gamer-booking-list.htm";
	}

	@GetMapping("/booking/joinBooking.htm")
	public String joinBooking(ModelAndView mv, HttpServletRequest request, BookingDAO bookingDAO, GamesDAO gamesDAO,
			Gamer gamer) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));

		List<Booking> bookingList = bookingDAO.getBookingForZoning(gameId, gamer.getGamerId());

//		Set<Gamer> zoners = booking.getZoners();
//		zoners.add(gamer);
//		
//		booking.setZoners(zoners);
//			bookingDAO.updateBookingByObj(booking);
		request.setAttribute("bookingList", bookingList);

		return "join-booking";
	}

	@GetMapping("/booking/gamer/joinBooking.htm")
	public String gamerJoinBooking( HttpServletRequest request, BookingDAO bookingDAO,
			GamesDAO gamesDAO, Gamer gamer) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int gamerId = Integer.parseInt(request.getParameter("gamerId"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currbookingDate = request.getParameter("currBookDate");
		Date currBookDate = new Date();
		currBookDate  = dateFormat.parse(currbookingDate);

		Booking booking = bookingDAO.getBookingById(gamerId, gameId, currBookDate);

		List<Gamer> zoners = booking.getZoners();
		zoners.add(gamer);

		booking.setZoners(zoners);
		bookingDAO.updateBookingByObj(booking);
		String joinBooking = "redirect:/booking/joinBooking.htm?gameId=" + gameId;
		return joinBooking;
	}
	
	@GetMapping("/booking/showZoners.htm")
	public void showZoners( HttpServletRequest request,
			BookingDAO bookingDAO,
			HttpServletResponse response, 
			GamesDAO gamesDAO, Gamer gamer) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			gamer = (Gamer) session.getAttribute("loggedGamer");

		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int gamerId = Integer.parseInt(request.getParameter("gamerId"));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bookingDate = request.getParameter("bookDate");
		Date bookDate = new Date();
		bookDate  = dateFormat.parse(bookingDate);
		List<Booking> zonerList =  bookingDAO.getZoners(gamerId, gameId, bookDate);

		request.setAttribute("zonerList", zonerList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/gamer/gamer-booking-list.htm");
	    dispatcher.forward(request, response);

			
	}

	@GetMapping("/booking/modifyBookingScore.htm")
	public String modifyBookingScore(ModelAndView mv, HttpServletRequest request, 
			BookingDAO bookingDAO,
		
			GamesDAO gamesDAO) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Gamer gamer = (Gamer) session.getAttribute("loggedGamer");

		}
//		int bookingId = Integer.parseInt(request.getParameter("bookingId"));

		int scored = Integer.parseInt(request.getParameter("score"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bookingDate = request.getParameter("bookDate");
		Date bookDate = new Date();
		bookDate  = dateFormat.parse(bookingDate);
		
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int gamerId = Integer.parseInt(request.getParameter("gamerId"));


		Booking booking = bookingDAO.getBookingById(gamerId, gameId, bookDate);
		
		booking.setScore(scored);
		
//
		bookingDAO.updateBookingByObj(booking);

		session.setAttribute("message", "Booking Modified");
		return "redirect:/gamer/gamer-booking-list.htm";
	}



}
