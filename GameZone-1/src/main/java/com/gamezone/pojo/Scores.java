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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

/**
 *
 * @author ritvikparamkusham
 */
@Component
@Entity
public class Scores {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scoreId;

    @ManyToOne
    @JoinColumn(name = "gamerId")
    private Gamer gamer; 
    @ManyToOne 
    @JoinColumn(name = "gameId")
    private Games games;
    
    private int score;
    
    @Temporal(TemporalType.DATE)
	private Date scoreDate;
    
    private int position;

    public Scores() {
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public Date getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}
    
    
}
