package ado.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@Controller
public class HelloController{
    
	@Autowired
	AnnotationConfigWebApplicationContext webAppContext;
	
	 @GetMapping(value = "/")
   @ResponseBody
   public String printWelcome(HttpServletRequest request) {
       return "hello";
   }

   @GetMapping(value = "/hello/{name:.+}")
   @ResponseBody
   public String hello(@PathVariable("name") String name) {
       return "hello " + name;
   }
   
   @GetMapping(value = "/startJetty")
   @ResponseBody
   public String startJetty(HttpServletRequest req) {
       try {
           JettyEmbedded.startSimpleJetty(webAppContext);
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return "Jetty Started hopefully";
   }
}
