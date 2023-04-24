
package com.gamezone.pojo;

//import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.SortedMap;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
	
	private boolean zoneBooking;
	
	private String nameOfZone;
	
//	private SortedMap<Gamer> Gamer;

	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Gamer> zoners = new HashSet<Gamer>();
	
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


	public boolean isZoneBooking() {
		return zoneBooking;
	}


	public void setZoneBooking(boolean zoneBooking) {
		this.zoneBooking = zoneBooking;
	}


	public String getNameOfZone() {
		return nameOfZone;
	}


	public void setNameOfZone(String nameOfZone) {
		this.nameOfZone = nameOfZone;
	}


	public Set<Gamer> getZoners() {
		return zoners;
	}


	public void setZoners(Set<Gamer> zoners) {
		this.zoners = zoners;
	}

    
}
