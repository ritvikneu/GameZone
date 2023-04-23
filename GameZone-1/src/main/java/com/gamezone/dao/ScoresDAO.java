package com.gamezone.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.gamezone.pojo.Scores;

@Component
public class ScoresDAO extends DAO{

	public ScoresDAO() {
		super();
	}
	
	 public void saveScores(Scores scores) {
	        try {
	            begin(); //inherited from super class DAO
	            getSession().save(scores);
	            commit();
	        } catch (Exception e) {
	            System.out.println("Exception: " + e.getMessage());
	        }
	    }
	    
		public List<Scores> getAllScores() {
			close();
			begin();
			Query qObj = getSession().createQuery("from Scores");
			List<Scores> ScoresList = qObj.getResultList();
			commit();
			close();
			return ScoresList;
		}

		public List<Scores> getScoresByGamer(int gamerId) {
			close();
			begin();
			Query qObj = getSession().createQuery("from Scores where gamerId=:gamerId ");
			qObj.setParameter("gamerId", gamerId);
			List<Scores> ScoresList = qObj.getResultList();
			commit();
			close();
			return ScoresList;
		}
		public List<Scores> getScoresByGame(int gameId) {
			close();
			begin();
			Query qObj = getSession().createQuery("from Scores where gameId=:gameId order by score desc");
			qObj.setParameter("gameId", gameId);
			List<Scores> ScoresList = qObj.getResultList();
			commit();
			close();
			return ScoresList;
		}
		
	    public void deleteScores(Scores scores) {
	        begin();
	        getSession().delete(scores);
	        commit();
	    }

	    public void deleteScoresById(int scoresId) {
	        begin();
	        getSession().delete(getSession().get(Scores.class, scoresId));
	        commit();
	    }

	    public Scores getScores(int scoresId) {
	        Scores Scores = getSession().get(Scores.class, scoresId);
	        return Scores;
	    }
	

}
