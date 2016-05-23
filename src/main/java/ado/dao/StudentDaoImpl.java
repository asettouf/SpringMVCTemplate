package ado.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ado.classes.Student;

@Transactional
public class StudentDaoImpl implements StudentDao {

	private SessionFactory sFactory;
	
	public StudentDaoImpl(SessionFactory sFactory) {
		this.sFactory = sFactory;
	}
	@Override
	public Student getStudent(String name) {
		
		Session session = sFactory.getCurrentSession();
		Student student = null;
		session.beginTransaction();
		Query q = session.createQuery("from Student where name= :name");
		q.setParameter("name", name);
		student = (Student) q.uniqueResult(); 
		
		return student;
	}

	@Override
	public void addStudent(String name, int age) {
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		Session session = sFactory.getCurrentSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		
	}

	@Override
	public void addStudent(Student student) {
		Session session = sFactory.getCurrentSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		
	}
	@Override
	public Student getStudent(int id) {
		Session session = sFactory.getCurrentSession();
		Student student = null;
		session.beginTransaction();
		student = (Student) session.get(Student.class, id);
		
		return student;
	}

}
