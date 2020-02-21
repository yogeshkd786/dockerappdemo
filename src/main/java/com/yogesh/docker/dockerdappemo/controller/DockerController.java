package com.yogesh.docker.dockerappdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

	@GetMapping("/message")
	public String getMyName() {
		
		return "Simple Spring Boot docker Application demo for argocd CI CD";
	}
}
