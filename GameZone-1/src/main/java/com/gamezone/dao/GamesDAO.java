package com.gamezone.dao;

import com.gamezone.pojo.Gamer;
import com.gamezone.pojo.Games;
import com.gamezone.pojo.University;

import static com.gamezone.dao.DAO.getSession;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.*;

import org.springframework.stereotype.Component;

/**
 *
 * @author ritvikparamkusham
 */
@Component
public class GamesDAO extends DAO {

	public GamesDAO() {
		super();
	}

	public void saveGames(Games games) {
		try {
			begin(); // inherited from super class DAO
			getSession().save(games);
			commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public List<Games> getAllGames() {
		close();
		begin();
		Query qObj = getSession().createQuery("from Games");
//		qObj.setString("gameId", String.valueOf(gameId));
		List<Games> gamesList = qObj.getResultList();
		commit();
		close();
		return gamesList;
	}

	public List<Games> getGamesByUniv(int univId) {
		close();
		begin();
		Query qObj = getSession().createQuery("from Games where univId=:univId ");
		qObj.setParameter("univId", univId);
		List<Games> gamesList = qObj.getResultList();
		commit();
		close();
		return gamesList;
	}

	public void deleteGames(Games games) {
		begin();
		getSession().delete(games);
		commit();
	}
	
	public void updateGamesByObj(Games games) {
		begin();
		getSession().update(games);
		commit();
	}


	public void deleteGamesById(int gamesId) {
		begin();
		getSession().delete(getSession().get(Games.class, gamesId));
		commit();
	}

	public Games getGame(int gameId) {
		Query qObj = getSession().createQuery("from Games where gameId=:gameId");
		qObj.setParameter("gameId", gameId);
		Games games = (Games) qObj.uniqueResult();
//        Games games = getSession().get(Games.class, gameId);
		return games;
	}
	
	
	public void updateGame(int gameId, int slots) {
		try {
			begin();
			@SuppressWarnings("unchecked")
			Query<Games> updQuery = getSession()
					.createQuery("update Games set availSlots = :slots" + " where gameId=:gameId");
			updQuery.setParameter("slots", slots);
			updQuery.setParameter("gameId", gameId);
			updQuery.executeUpdate();
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
		}
	}

}
