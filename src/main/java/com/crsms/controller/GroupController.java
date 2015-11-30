package com.crsms.controller;

import java.security.Principal;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Group;
import com.crsms.dto.GroupFormDto;
import com.crsms.service.DtoService;
import com.crsms.service.GroupService;

@Controller
@RequestMapping(value = "/courses/{courseId}/groups")
public class GroupController {
	public static final String GROUPS_VIEW = "groups";
	public static final String ADD_GROUP_VIEW = "addgroup";
	public static final String EDIT_GROUP_VIEW = "editgroup";
	
	@Autowired
	private DtoService droService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getGroups(@PathVariable Long courseId, Model model) {
		model.addAttribute("groups", groupService.getAllByCourseId(courseId));
		return GROUPS_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGroup(@PathVariable Long courseId, Model model) {
		GroupFormDto groupFormDto = droService.convert(new Group(),
				GroupFormDto.class, Group.class);
		groupFormDto.setCourseId(courseId);
		groupFormDto.setStartDate(DateTime.now());
		model.addAttribute("groupFormDto", groupFormDto);
		return ADD_GROUP_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGroupSubmit(@PathVariable Long courseId, GroupFormDto groupFormDto) {
		Group group = droService.convert(groupFormDto, Group.class, GroupFormDto.class);
		groupService.save(courseId, group);
		return redirect(courseId);
	}
	
	@RequestMapping(value = "/{groupId}/edit", method = RequestMethod.GET)
	public String editGroup(@PathVariable Long courseId, @PathVariable Long groupId, Model model) {
		Group group = groupService.getById(groupId);
		GroupFormDto groupFormDto = droService.convert(group, GroupFormDto.class, Group.class);
		groupFormDto.setCourseId(courseId);
		model.addAttribute("groupFormDto", groupFormDto);
		return EDIT_GROUP_VIEW;
	}
	
	@RequestMapping(value = "/{groupId}/edit", method = RequestMethod.POST)
	public String editGroupSubmit(@PathVariable Long courseId, @PathVariable Long groupId,
			GroupFormDto groupFormDto) {
		Group group = droService.convert(groupFormDto, Group.class, GroupFormDto.class);
		groupService.update(courseId, group);
		return redirect(courseId);
	}
	
	@RequestMapping(value = "/{groupId}/delete", method = RequestMethod.GET)
	public String editGroupSubmit(@PathVariable Long courseId, @PathVariable Long groupId) {
		groupService.deleteById(groupId);
		return redirect(courseId);
	}
	
	@RequestMapping(value = "/{groupId}/enroll", method = RequestMethod.GET)
	public String subscribe(@PathVariable("groupId") Long groupId, Principal principal) {
		groupService.subscribe(groupId, principal.getName());
		return CourseController.MY_COURSES_REDIRECT;
	}
	
	@RequestMapping(value = "/{groupId}/leave", method = RequestMethod.GET)
	public String unsubscribe(@PathVariable("groupId") Long groupId, Principal principal) {
		groupService.unsubscribe(groupId, principal.getName());
		return CourseController.MY_COURSES_REDIRECT;
	}
	
	private String redirect(Long courseId) {
		return "redirect:/courses/" + courseId + "/groups/";
	}
}