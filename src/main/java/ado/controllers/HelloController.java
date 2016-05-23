package ado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hello")
public class HelloController{
       
   @RequestMapping(method=RequestMethod.GET)
   public String printHello(ModelMap model){
	   model.addAttribute("message","Hello Spring MVC");
	   
	   return "hello";
	   
   }

}
