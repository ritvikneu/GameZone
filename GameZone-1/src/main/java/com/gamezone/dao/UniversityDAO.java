/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamezone.dao;

import com.gamezone.pojo.University;
import java.util.List;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

/**
 *
 * @author ritvikparamkusham
 */
@Component
public class UniversityDAO extends DAO {

	public UniversityDAO() {
		super();
	}

	public void saveUniversity(University univ) {
		try {
			begin(); // inherited from super class DAO
			getSession().save(univ);
			commit();
			close();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public void deleteUniversity(University univ) {
		begin();
		getSession().delete(univ);
		commit();
		close();
	}

	public void deleteUniversityById(int univId) {
		begin();
		getSession().delete(getSession().get(University.class, univId));
		commit();
		close();
	}

	public University getUniversity(int univId) {
		begin();
		University university = getSession().get(University.class, univId);
		commit();
		close();
		return university;
	}

	public List<University> getAllUniv() {

		close();
		begin();
		Query qObj = getSession().createQuery("from University");
		List<University> univList = qObj.getResultList();
		commit();
		close();

		return univList;

	}
	

}
