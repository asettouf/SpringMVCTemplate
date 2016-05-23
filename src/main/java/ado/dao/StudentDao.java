package ado.dao;

import ado.classes.Student;

public interface StudentDao {
	
	public Student getStudent(String name);
	public Student getStudent(int id);
	public void addStudent(String name, int age);
	public void addStudent(Student student);

}
