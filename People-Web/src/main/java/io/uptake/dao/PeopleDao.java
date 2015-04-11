package io.uptake.dao;

import java.util.List;

import io.uptake.ldm.People;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PeopleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void create(People p) {
		this.sessionFactory.getCurrentSession().save(p);
	}

	public void update(People p) {
		this.sessionFactory.getCurrentSession().update(p);
	}

	public People readById(int id) {
		return (People) this.sessionFactory.getCurrentSession().get(
				People.class, id);
	}

	public void delete(int id) {
		People p = (People) this.sessionFactory.getCurrentSession().load(
				People.class, new Integer(id));
		if (null != p) {
			this.sessionFactory.getCurrentSession().delete(p);
		}
	}

	@SuppressWarnings("unchecked")
	public List<People> readAll() {
		return (List<People>) this.sessionFactory.getCurrentSession()
				.createQuery("from People").list();
	}
}
