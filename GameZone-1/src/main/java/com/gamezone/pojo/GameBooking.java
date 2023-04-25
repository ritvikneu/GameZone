package com.gamezone.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class GameBooking {
	   
    @EmbeddedId
    private BookingId bookingId; // Represents the composite primary key
    
    private String slot; 
    private int score;
    private boolean zoneBooking;
    private String nameOfZone;
	
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Gamer> zoners = new HashSet<Gamer>();

	public GameBooking() {
		super();
	}

	public BookingId getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingId bookingId) {
		this.bookingId = bookingId;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
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
