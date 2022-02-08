package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Admin;
import com.precise.model.Forum;
import com.precise.model.Inbox;
import com.precise.model.SessionBean;
import com.precise.service.AdminService;

@Controller
public class AdminController {
     @Autowired
     AdminService adminService;
     
    @RequestMapping(value = "/kmadmin")
 	public ModelAndView getKMAdminPage(HttpServletRequest req) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		 userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
 		System.out.println("AdminController.getKMAdminPage()");
    	Map<Integer,String> studentList=adminService.getStudentList();
        Map<Integer,String> placecomStudentList=adminService.getPlaceComStudentList();
 		Map<Integer,String> prefix=adminService.getAllPrefix();
 		req.setAttribute("prefix", prefix);
 		req.setAttribute("comStudent", placecomStudentList);
 		return new ModelAndView("admin","studentList",studentList);
 	}
    
    @RequestMapping(value = "/saveStudent")
 	public String saveStudent(HttpServletRequest req,HttpServletResponse res) {
 		System.out.println("AdminController.saveStudent()");
 		
 		/*String studentIds=req.getParameter("studentIds");
 		String[] num=studentIds.split(",");
 		List<Integer> ids=new ArrayList<Integer>();
 		for(String str:num){
			System.out.println("studnet ids-"+str);
			ids.add(Integer.parseInt(str));
		}*/
 		
 		String[] ids=req.getParameterValues("students");
 		List<Integer> stuIds=new ArrayList<Integer>();
 		for(String str:ids){
 			System.out.println("abadsd"+str);
 			stuIds.add(Integer.parseInt(str));
 		}
 		
 		System.out.println("idsss"+stuIds);
 		adminService.saveStudent(stuIds); 		
 		
 		Map<Integer,String> placecomStudentList=adminService.getPlaceComStudentList();
 		
 		req.setAttribute("comStudent", placecomStudentList);
 		/*
 		 JSONArray jsonArray = new JSONArray();	   
 	     for(Entry<Integer,String> map:placecomStudentList.entrySet()) {	        
 	        JSONObject formDetailsJson = new JSONObject();	        
 	        formDetailsJson.put("placeComStudentId", map.getKey());
 	        formDetailsJson.put("placeComStudentName",map.getValue());	 	       	       
 	        jsonArray.put(formDetailsJson);
 	    }
 	    System.out.println("json array- "+jsonArray);
    	
 	   try {
			PrintWriter out=res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("excepition in json aararr"+e);
			e.printStackTrace();
		}*/
	    
 	   
 	    Map<Integer,String> studentList=adminService.getStudentList();
 	    Map<Integer,String> prefix=adminService.getAllPrefix();
		req.setAttribute("prefix", prefix);
 	    
 		/*return new ModelAndView("admin","studentList",studentList);*/
		return "redirect:kmadmin";
 	}
    
    
    
    @RequestMapping(value = "/removeStudent")
 	public String removeStudent(HttpServletRequest req,HttpServletResponse res) {
 		System.out.println("AdminController.removeStudent()");
 			
 		String[] ids=req.getParameterValues("comstudents");
 		List<Integer> stuIds=new ArrayList<Integer>();
 		for(String str:ids){
 			System.out.println("abadsd"+str);
 			stuIds.add(Integer.parseInt(str));
 		}
 		
 		System.out.println("idsss"+stuIds);
 		adminService.removeStudent(stuIds); 		
 		
 		Map<Integer,String> placecomStudentList=adminService.getPlaceComStudentList();
 		
 		req.setAttribute("comStudent", placecomStudentList);
 		
 	   
 	    Map<Integer,String> studentList=adminService.getStudentList();
 	    Map<Integer,String> prefix=adminService.getAllPrefix();
		req.setAttribute("prefix", prefix);
 	    
 		/*return new ModelAndView("admin","studentList",studentList);*/
		return "redirect:kmadmin";
 	}
    
    @RequestMapping(value = "/getStudentByPrefixId")
   	public void getStudentByPrefixId(HttpServletRequest req,HttpServletResponse res ) {    	
    	int prefixId=Integer.parseInt(req.getParameter("prefixId"));
    	System.out.println("prefixId-"+prefixId);
    	Map<Integer,String> studentList=adminService.getStudentListByPrefix(prefixId);
    	
	    JSONArray jsonArray = new JSONArray();	   
	     for(Entry<Integer,String> map:studentList.entrySet()) {	        
	        JSONObject formDetailsJson = new JSONObject();	        
	        formDetailsJson.put("prefixId", map.getKey());
	        formDetailsJson.put("studentName",map.getValue());	 	       	       
	        jsonArray.put(formDetailsJson);
	    }
	    System.out.println("json array- "+jsonArray);
   	
	   try {
			PrintWriter out=res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("excepition in json aararr"+e);
			e.printStackTrace();
		}
    }
	   
    @RequestMapping(value = "/getPlaceComStudentByPrefixId")
	   	public void getPlaceComStudentByPrefixId(HttpServletRequest req,HttpServletResponse res ) {    	
	    	int prefixId=Integer.parseInt(req.getParameter("prefixId"));
	    	System.out.println("prefixId-"+prefixId);
	    	Map<Integer,String> studentList=adminService.getPlaceComStudentListByPrefixId(prefixId);
	    	
		    JSONArray jsonArray = new JSONArray();	   
		     for(Entry<Integer,String> map:studentList.entrySet()) {	        
		        JSONObject formDetailsJson = new JSONObject();	        
		        formDetailsJson.put("prefixId", map.getKey());
		        formDetailsJson.put("studentName",map.getValue());	 	       	       
		        jsonArray.put(formDetailsJson);
		    }
		    System.out.println("json array- "+jsonArray);
	   	
		   try {
				PrintWriter out=res.getWriter();
				out.print(jsonArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("excepition in json aararr"+e);
				e.printStackTrace();
			}
    	
    	
    }
    
    @RequestMapping(value = "/getMentorPage")
 	public ModelAndView getMentorPage(HttpServletRequest req) {
    	int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
 		//System.out.println("AdminController.getMentorPage()");    	
 		Map<Integer,String> allstudentList=adminService.getMentorStudentList();
 		Map<Integer,String> prefix=adminService.getAllPrefix();
 		req.setAttribute("prefix", prefix);
 		Map<Integer,String> getMentor=adminService.getMentor();
 		req.setAttribute("mentor", getMentor); 		
 		Map<Integer,String> mentorAllStudent= adminService.getMentorAllStudent();
 		req.setAttribute("allMentroStudent", mentorAllStudent); 		
 		return new ModelAndView("mentor","allstudentList",allstudentList);
 	}
    
    @RequestMapping(value = "/saveMentorStudent")
 	public String saveMentorStudent(HttpServletRequest req,HttpServletResponse res,Admin admin) {
 		System.out.println("AdminController.saveMentorStudent()"); 		 		
 		adminService.saveMentorStudent(admin); 		
 		Map<Integer,String> allstudentList=adminService.getMentorStudentList();
 		Map<Integer,String> prefix=adminService.getAllPrefix();
 		req.setAttribute("prefix", prefix);
 		Map<Integer,String> getMentor=adminService.getMentor();
 		req.setAttribute("mentor", getMentor); 		
 		Map<Integer,String> mentorAllStudent= adminService.getMentorAllStudent();
 		req.setAttribute("allMentroStudent", mentorAllStudent); 		
 		return "redirect:getMentorPage";
 	}
    
    
    @RequestMapping(value = "/removeMentorStudent")
 	public String removeMentorStudent(HttpServletRequest req,HttpServletResponse res,Admin admin) {
 		System.out.println("AdminController.removeStudent()"); 		
 		List<Integer> ids=admin.getStudentIds();
 		System.out.println("remove mentor idsss"+ids);
 		adminService.removeMentorStudent(ids); 	 		
 		Map<Integer,String> allstudentList=adminService.getMentorStudentList();
 		Map<Integer,String> prefix=adminService.getAllPrefix();
 		req.setAttribute("prefix", prefix);
 		Map<Integer,String> getMentor=adminService.getMentor();
 		req.setAttribute("mentor", getMentor); 		
 		Map<Integer,String> mentorAllStudent= adminService.getMentorAllStudent();
 		req.setAttribute("allMentroStudent", mentorAllStudent); 		
 		return "redirect:getMentorPage";
 	}
    
    @RequestMapping(value = "/getMentorStudentListByPrefixId")
    public void getMentorStudentListByPrefixId(HttpServletRequest req,HttpServletResponse res ) {    	
    	int prefixId=Integer.parseInt(req.getParameter("prefixId"));
    	System.out.println("prefixId-"+prefixId);
    	
    	Map<Integer,String> studentListByPrefix=adminService.getMentorStudentListByPrefixId(prefixId);    	
	    JSONArray jsonArray = new JSONArray();	   
	     for(Entry<Integer,String> map:studentListByPrefix.entrySet()) {	        
	        JSONObject formDetailsJson = new JSONObject();	        
	        formDetailsJson.put("STUID", map.getKey());
	        formDetailsJson.put("STUName",map.getValue());	 	       	       
	        jsonArray.put(formDetailsJson);
	    }
	    System.out.println("json array- "+jsonArray);
   	
	   try {
			PrintWriter out=res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("excepition in json aararr"+e);
			e.printStackTrace();
		}
    }
	  
    @RequestMapping(value = "/getStudentByMentorId")
   	public void getStudentByMentorId(HttpServletRequest req,HttpServletResponse res ) {    	
    	int mentorId=Integer.parseInt(req.getParameter("mentorId"));
    	System.out.println("mentorId-"+mentorId);
    	Map<Integer,String> studentList=adminService.getMentorStudentListByMentorId(mentorId);
    	
	    JSONArray jsonArray = new JSONArray();	   
	     for(Entry<Integer,String> map:studentList.entrySet()) {	        
	        JSONObject formDetailsJson = new JSONObject();	        
	        formDetailsJson.put("studentId", map.getKey());
	        formDetailsJson.put("studentName",map.getValue());	 	       	       
	        jsonArray.put(formDetailsJson);
	    }
	    System.out.println("metor studnet json array- "+jsonArray);
   	
	   try {
			PrintWriter out=res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("excepition in json aararr"+e);
			e.printStackTrace();
		}
	
	
     }
    
    @RequestMapping(value = "/getConvertPgpPage")
 	public ModelAndView getConvertPgpPage(HttpServletRequest req) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
 		System.out.println("AdminController.getKMAdminPage()");    	
    	Map<Integer,String> pgp1studentList=adminService.getPgpOneStudentList();
        Map<Integer,String> pgp2studentList=adminService.getPgpTwoStudentList(); 		
 		req.setAttribute("pgp2studentList", pgp2studentList);
 		return new ModelAndView("convertPgpPage","studentList",pgp1studentList);
 	}
        
    @RequestMapping(value = "/convertStudentPgp")
 	public String convertStudentPgpOneToTwo(HttpServletRequest req,HttpServletResponse res,Admin admin) {
 		System.out.println("AdminController.convertStudentPgpOneToTwo()"); 		
 		String[] ids=req.getParameterValues("students");
 		List<Integer> stuIds=new ArrayList<Integer>();
 		for(String str:ids){
 			System.out.println("abadsd"+str);
 			stuIds.add(Integer.parseInt(str));
 		}
 		admin.setStudentIds(stuIds);
 		System.out.println("idsss"+stuIds); 			 		
 		adminService.convertPgpOneToTwo(admin);
		return "redirect:getConvertPgpPage";
 	}
    
    @RequestMapping(value = "/convertStudenttoPgp1")
 	public String convertStudenttoPgp1(HttpServletRequest req,HttpServletResponse res,Admin admin) {
 		System.out.println("AdminController.convertStudenttoPgp1()"); 		
 		String[] ids=req.getParameterValues("pgp2students");
 		List<Integer> stuIds=new ArrayList<Integer>();
 		for(String str:ids){
 			System.out.println("abadsd"+str);
 			stuIds.add(Integer.parseInt(str));
 		}
 		admin.setStudentIds(stuIds);
 		System.out.println("idsss"+stuIds); 			 		
 		adminService.convertPgpTwoToOne(admin);
		return "redirect:getConvertPgpPage";
 	}
    
    @RequestMapping(value = "/getAssignExperiencePage")
 	public ModelAndView getAssignExperiencePage(HttpServletRequest req) {
 		
    	int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
			{
				return new ModelAndView("LoginPage");
			}
    	System.out.println("AdminController.getSssignExperiencePage()");    	
 		
 		return new ModelAndView("assignExperience");
 	}
    
    @RequestMapping(value = "/saveAssignExperience")
 	public String saveAssignExperience(HttpServletRequest req,Admin admin) {
 		System.out.println("AdminController.saveAssignExperience()");   		
 		System.out.println("interv exp-"+admin.getInterviewExperience()+"  interns exp-"+admin.getInternshipExperience());
 		
 		if(admin.getInterviewExperience()==null && admin.getInternshipExperience()==true){
			 admin.setInterviewExperience(false);
			 adminService.assignExperience(admin);
		}
		else if(admin.getInternshipExperience()==null && admin.getInterviewExperience()==true){
			 admin.setInternshipExperience(false);
		     adminService.assignExperience(admin);
		}
 		else if(admin.getInterviewExperience()==null && admin.getInternshipExperience()==null){
 			 admin.setInterviewExperience(false);
 			 admin.setInternshipExperience(false);
		     adminService.assignExperience(admin);
		}
 		else if(admin.getInterviewExperience()==true && admin.getInternshipExperience()==true){
		     adminService.assignExperience(admin);
		}
 		//req.setAttribute("Success", "Experience assigned successfully!!");
 		return "redirect:getAssignExperiencePage";
 	}
    
    @RequestMapping(value = "/getExperienceByPGP")
 	public ModelAndView getExperienceByPGP(HttpServletRequest req,Admin admin,Model model) {
 		System.out.println("AdminController.getExperienceByPGP()"); 
 		int gid=0;
 		if(req.getParameter("pgpId")==""){
 			
 		}else{
 		 gid=Integer.parseInt(req.getParameter("pgpId"));
 		}
 		System.out.println("gid----pgp--"+gid);
 		List<Admin> getExp=adminService.getExperienceDataByPGP(gid);
 		int pgpid=admin.getGroupId();
        if(pgpid==0){
 			System.out.println("nothing selected --");
 		}
 		System.out.println("group id in getexp--"+pgpid);
 		model.addAttribute("PGP",pgpid);
 		return new ModelAndView("assignExperience","Experince",getExp);
 	}
    
  
}
