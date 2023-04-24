/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamezone.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

/**
 *
 * @author ritvikparamkusham
 */
@Component
@Entity
@Table(name="Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @ManyToOne
    private Gamer gamer; 
    @ManyToOne 
    private Games games; 
//    private String location;
    private String slot; 
    
	@Temporal(TemporalType.DATE)
	private Date bookDate;
	
	private int score;

    public Booking() {
    }


    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }


	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
    
	
    
    
}
