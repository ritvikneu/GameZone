package com.gamezone.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.gamezone.pojo.Booking;
import com.gamezone.pojo.BookingId;
import com.gamezone.pojo.Games;

import org.hibernate.query.*;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Component
public class BookingDAO extends DAO {

	public BookingDAO() {
		super();
	}

	public void saveBooking(Booking booking) {
		try {
			begin(); // inherited from super class DAO
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

	public Booking getBookingById(int gamerId, int gameId, Date currBookDate) {
		close();
		begin();

		Criteria crit = getSession().createCriteria(Booking.class, "booking");
		crit.add(Restrictions.eq("booking.bookingId.gamer.gamerId", gamerId));
		crit.add(Restrictions.eq("booking.bookingId.games.gameId", gameId));
		crit.add(Restrictions.eq("booking.bookingId.bookDate", currBookDate));
		Booking booking = (Booking) crit.uniqueResult();

//			List<Booking> bookingList = crit.list();

		commit();
		close();
		return booking;
	}

	public List<Booking> getZoners(int gamerId, int gameId, Date currBookDate) {
		close();
		begin();

		Criteria crit = getSession().createCriteria(Booking.class, "booking");
		crit.add(Restrictions.eq("booking.bookingId.gamer.gamerId", gamerId));
		crit.add(Restrictions.eq("booking.bookingId.games.gameId", gameId));
		crit.add(Restrictions.eq("booking.bookingId.bookDate", currBookDate));

		List<Booking> zonerList = crit.list();

		commit();
		close();
		return zonerList;
	}

	public List<Booking> getBookingByGamer(int gamerId) {
		close();
		begin();

		Criteria crit = getSession().createCriteria(Booking.class, "booking");
		crit.add(Restrictions.eq("booking.bookingId.gamer.gamerId", gamerId));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // Add this line to fetch only unique entries

		List<Booking> bookingList = crit.list();

		commit();
		close();
		return bookingList;
	}

	public List<Booking> getBookingByZoner(int gamerId) {
		close();
		begin();

		Criteria crit = getSession().createCriteria(Booking.class, "booking");

//			crit.add(Restrictions.eq("zoneBooking", true));
//			crit.add(Restrictions.isNotNull("booking.zoners"));
//			crit.add(Restrictions.eq("booking.zoners.gamerId", gamerId));
//			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // Add this line to fetch only unique entries
////		    
		crit.add(Restrictions.eq("zoneBooking", true));
		crit.createAlias("booking.zoners", "zoners"); // Create an alias for the zoners collection
		crit.add(Restrictions.eq("zoners.gamerId", gamerId)); // Use the alias to access properties of objects within
																// the collection
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // Add this line to fetch only unique entries

		List<Booking> zonerList = crit.list();
//			
//			
		commit();
		close();
		return zonerList;
	}

	public List<Booking> getBookingByGames(int gameId) {
		close();
		begin();
//			Query qObj = getSession().createQuery("from Booking where games_gameId=:gameId and score>0 order by score desc");
//			qObj.setParameter("gameId", gameId);
//			List<Booking> bookingList = qObj.getResultList();

		Criteria crit = getSession().createCriteria(Booking.class, "booking");
		crit.add(Restrictions.eq("booking.bookingId.games.gameId", gameId));
		crit.add(Restrictions.gt("booking.score", 0));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Booking> bookingList = crit.list();

		commit();
		close();
		return bookingList;
	}

	public List<Booking> getBookingForZoning(int gameId, int gamerId) {
		close();
		begin();

		Criteria crit = getSession().createCriteria(Booking.class, "booking");
		crit.add(Restrictions.eq("booking.bookingId.games.gameId", gameId));
		crit.add(Restrictions.ne("booking.bookingId.gamer.gamerId", gamerId));
		crit.add(Restrictions.eq("zoneBooking", true));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // Add this line to fetch only unique entries

		List<Booking> bookingList = crit.list();

		commit();
		close();
		return bookingList;
	}

	public void updateBookingByObj(Booking booking) {
		begin();
		getSession().update(booking);
		commit();
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
