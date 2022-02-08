package com.precise.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.AssignRole;
import com.precise.model.SessionBean;
import com.precise.service.AssignRoleService;

@Controller
public class AssignRoleController {
	@Autowired
	AssignRoleService assignRoleService;
	@RequestMapping(value = "/getRolePage")
	public ModelAndView getAssignRolePage(HttpServletRequest req) {
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
	 //System.out.println("AdminController.getAssignRolePage()");
	 List<AssignRole> getRole=assignRoleService.getAssignRoles();
	    	
	return new ModelAndView("assignRole","roles",getRole);
	}
	
	@RequestMapping(value = "/saveAssignedRoles", method = RequestMethod.POST)
	public String saveForum(ModelAndView model, AssignRole role,HttpServletRequest req) {
		//System.out.println("AssignRoleController.saveAssignedRoles() sid" +role.getStudentId()+" mid-"+role.getMentorId()+" rid-"+role.getRmId());
		 
		
		String studentId=req.getParameter("studentId");
		String mentorId=req.getParameter("mentorId");
		
	//	String rmId=req.getParameter("rmId");
	//	System.out.println("Student id in request :: "+studentId+"mentor id  :: "+mentorId+"rM is :: "+rmId);
		
		role.setStudentId(Integer.parseInt(req.getParameter("studentId")));
		role.setMentorId(Integer.parseInt(req.getParameter("mentorId")));
		role.setRmId(Integer.parseInt(req.getParameter("rmId")));
		role.setStartoAdminId(Integer.parseInt(req.getParameter("startoAdminId")));
		role.setPrintingTeamId(Integer.parseInt(req.getParameter("printingTeamId")));
		role.setSatcomId(Integer.parseInt(req.getParameter("satcomId")));
		role.setSchedulingAdminId(Integer.parseInt(req.getParameter("schedulingAdminId")));
		role.setNegoId(Integer.parseInt(req.getParameter("negoId")));
		role.setSideTrackerId(Integer.parseInt(req.getParameter("sideTrackerId")));
		role.setOfferProcessorId(Integer.parseInt(req.getParameter("offerProcessorId")));
		role.setSuperRMOnTheDayId(Integer.parseInt(req.getParameter("superRMOnTheDayId")));
		role.setSuperRMThroughoutTheYearId(Integer.parseInt(req.getParameter("superRMThroughoutTheYearId")));
		role.setCoordinatorId(Integer.parseInt(req.getParameter("coordinatorId")));
		role.setSchedulerId(Integer.parseInt(req.getParameter("schedulerId")));
		
		//System.out.println("schedulerId--"+req.getParameter("schedulerId")+"coordinatorId-"+req.getParameter("coordinatorId"));
		
		if(role.getMentorId()==3 || role.getRmId()==4 || role.getStartoAdminId()==9 || role.getPrintingTeamId()==19 || role.getSatcomId()==20 || role.getSchedulingAdminId()==21 || role.getNegoId()==48 || role.getSideTrackerId()==17 || role.getOfferProcessorId()==8|| role.getSuperRMOnTheDayId()==49
				|| role.getSuperRMThroughoutTheYearId()==50 || role.getCoordinatorId()==55 || role.getSchedulerId()==56){
			 //System.out.println("inside save assign role");
		    assignRoleService.saveAssignedRoles(role);
		 }
		 if(role.getMentorId()==1 || role.getRmId()==1 || role.getStartoAdminId()==1 || role.getPrintingTeamId()==1 || role.getSatcomId()==1 || role.getSchedulingAdminId()==1 || role.getNegoId()==1 || role.getSideTrackerId()==1|| role.getOfferProcessorId()==1 || role.getSuperRMOnTheDayId()==1 || role.getSuperRMThroughoutTheYearId()==1 || role.getCoordinatorId()==1 || role.getSchedulerId()==1){
			// System.out.println("inside update assign role");
		 assignRoleService.updateAssignedRoles(role);
		 }
		// AssignRole checkRole=assignRoleService.getRolesByStudentId(role.getStudentId());
		
		// List<AssignRole> getRole=assignRoleService.getAssignRoles();
		return "redirect:getRolePage";
	}
	
}
