package com.lingar.SocialEvents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

}