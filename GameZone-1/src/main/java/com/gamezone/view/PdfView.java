package com.gamezone.view;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.gamezone.dao.GamesDAO;
import com.gamezone.dao.UniversityDAO;
import com.gamezone.pojo.Gamer;
import com.gamezone.pojo.Games;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		Paragraph p1 = new Paragraph("A Request is sent to ADMIN");
		Paragraph p2 = new Paragraph("Below are your details");
		
		HttpSession session = request.getSession(false);
		Games game = null;
		Gamer gamer;
		GamesDAO gamesDAO = new GamesDAO();
		UniversityDAO univDAO = new UniversityDAO();
			gamer  = (Gamer) session.getAttribute("loggedGamer");
			game = gamesDAO.getGame(gameId);
			 
		
		Paragraph p3 = new Paragraph(gameId);
		Paragraph p4 = new Paragraph(gamer.getGamerName()+" is requesting to play a game of "+
											game.getGameName()+ " at " +
											game.getUniversity().getUnivName());

		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.add(p4);
	}

	
}
