package com.gamezone.pojo;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Embeddable
public class BookingId implements Serializable{
	 @ManyToOne
     private Gamer gamer;
     
     @ManyToOne
     private Games games;
     
     @Temporal(TemporalType.DATE)
     private Date bookDate;
     
     

	public BookingId() {
		super();
	}

	public BookingId(Gamer gamer, Games games, Date bookDate) {
		super();
		this.gamer = gamer;
		this.games = games;
		this.bookDate = bookDate;
	}

	public Gamer getGamer() {
		return gamer;
	}

	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
	}

	public Games getGames() {
		return games;
	}

	public void setGames(Games games) {
		this.games = games;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
     
     
     
}
