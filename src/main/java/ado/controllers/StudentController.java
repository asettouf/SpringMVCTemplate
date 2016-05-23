package ado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ado.classes.Student;
import ado.dao.StudentDao;

@Controller
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping(value="/student", method=RequestMethod.GET)
	public ModelAndView student(@RequestParam(value="name", required=true) String name){
		return new ModelAndView("student", "command", studentDao.getStudent(name));
	}

	@RequestMapping(value = "/addstudent", method=RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb") Student student, ModelMap model){
		model.addAttribute("name", student.getName());
		model.addAttribute("age", student.getAge());
		model.addAttribute("id", student.getId());
		studentDao.addStudent(student);
		return "result";
	}
}
