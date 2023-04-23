package com.gamezone.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.gamezone.pojo.Gamer;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class LoginController {
	
	@GetMapping("/login.htm")
	public ModelAndView displayLogin(@ModelAttribute("gamer") Gamer gamer,
			HttpServletRequest request,
			ModelAndView mv) {
		mv.addObject("gamer", gamer);
		mv.setViewName("login");
		return mv;
	}


}
