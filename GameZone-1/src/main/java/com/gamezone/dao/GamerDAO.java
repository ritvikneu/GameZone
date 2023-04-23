/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamezone.dao;

import com.gamezone.pojo.Gamer;
import org.hibernate.query.*;

import static com.gamezone.dao.DAO.getSession;

import org.springframework.stereotype.Component;

/**
 *
 * @author ritvikparamkusham
 */
@Component
public class GamerDAO extends DAO {
	
	public GamerDAO() {
		super();
	}

    public void saveGamer(Gamer gamer) {
        try {
            begin(); //inherited from super class DAO
            getSession().save(gamer);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void deleteGamer(Gamer gamer) {
        begin();
        getSession().delete(gamer);
        commit();
    }

    public void deleteGamerById(int gamerid) {
        begin();
        getSession().delete(getSession().get(Gamer.class, gamerid));
        commit();
    }

    public Gamer getGamer(int gamerid) {
        Gamer gamer = getSession().get(Gamer.class, gamerid);
        return gamer;
    }
    
    public Gamer getGamerAtLogin(String email, String password) {
    	begin();
		Query query = getSession().createQuery("from Gamer where email = :email and password = :password");
		query.setString("email", email);
		query.setString("password", password);
		Gamer gamer = (Gamer) query.uniqueResult();
		commit();
        return gamer;
    }

}
