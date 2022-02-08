package com.precise.controller;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.EventCalander;
import com.precise.model.Label;
import com.precise.model.SessionBean;
import com.precise.service.InboxService;


@Controller
public class CaladerController {
	@Autowired
	InboxService iservice;
	@RequestMapping(value="/getCalander")
	public ModelAndView  getInboxData(HttpSession session,HttpServletRequest req){
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int receiverId=sbean.getUserID();
		//System.out.println("receiver id-"+receiverId);
		Map<Integer,Label> getLabel=iservice.getLabelByUserId(receiverId);
		//List<Company> CompanyDetaillist = companyService.getAllCompanyDetail(userId);
		//req.setAttribute("companyDetails", CompanyDetaillist);
		JSONObject eventlist= iservice.getEventList(receiverId);
		
		//System.out.println("json array::"+eventlist.toString());
		req.setAttribute("eventlist",eventlist);
		JSONObject companyApply=iservice.getCompanyEventList(receiverId);
		req.setAttribute("eventscomlist",companyApply);
		return new ModelAndView("calander");
	}
}
