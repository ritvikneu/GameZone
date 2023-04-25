
package com.gamezone.pojo;

import java.util.ArrayList;
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
import javax.persistence.EmbeddedId;
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
	   
    @EmbeddedId
    private BookingId bookingId; // Represents the composite primary key

    private String slot; 
	private int score;
	
	private boolean zoneBooking;
	
	private String nameOfZone;
		
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Gamer> zoners = new ArrayList<>();
	
    public Booking() {
    }


    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

	public BookingId getBookingId() {
		return bookingId;
	}


	public void setBookingId(BookingId bookingId) {
		this.bookingId = bookingId;
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


	public List<Gamer> getZoners() {
		return zoners;
	}


	public void setZoners(List<Gamer> zoners) {
		this.zoners = zoners;
	}



    
}
