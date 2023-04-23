package com.gamezone.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.gamezone.pojo.Booking;

@Component
public class BookingDAO extends DAO{
	
	public BookingDAO() {
		super();
	}
	
	 public void saveBooking(Booking booking) {
	        try {
	            begin(); //inherited from super class DAO
	            getSession().save(booking);
	            commit();
	        } catch (Exception e) {
	            System.out.println("Exception: " + e.getMessage());
	        }
	    }
	    
		public List<Booking> getAllBooking() {
			close();
			begin();
			Query qObj = getSession().createQuery("from Booking");
			List<Booking> bookingList = qObj.getResultList();
			commit();
			close();
			return bookingList;
		}

		public List<Booking> getBookingByGamer(int gamerId) {
			close();
			begin();
			Query qObj = getSession().createQuery("from Booking where gamerId:gamerId ");
			List<Booking> bookingList = qObj.getResultList();
			qObj.setParameter("gamerId", gamerId);
			commit();
			close();
			return bookingList;
		}
	    public void deleteBooking(Booking booking) {
	        begin();
	        getSession().delete(booking);
	        commit();
	    }

	    public void deleteBookingById(int bookingId) {
	        begin();
	        getSession().delete(getSession().get(Booking.class, bookingId));
	        commit();
	    }

	    public Booking getBooking(int bookingId) {
	        Booking Booking = getSession().get(Booking.class, bookingId);
	        return Booking;
	    }

}