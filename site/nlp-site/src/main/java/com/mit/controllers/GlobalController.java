package com.mit.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public final class GlobalController {
	@Value("${site.resources.path}")
	private String resourcesPath;
	
	@Value("${site.prefix}")
	private String sitePrefix;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest req) {
		model.addAttribute("resources_path", resourcesPath);
		model.addAttribute("site_prefix", sitePrefix);
	}
}