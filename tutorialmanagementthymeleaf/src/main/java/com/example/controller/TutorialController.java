package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Repository.TutorialRepository;
import com.example.entity.Tutorial;

@Controller
@RequestMapping("/tutorialsman")
public class TutorialController {
	@Autowired
	private TutorialRepository tutorep;

	@GetMapping("/signup")
	public String showSignupForm(Tutorial tut) {
		return "add-tutorial";
	}

	@GetMapping("/list")
	public String showUpdateForm(Model model) {
		model.addAttribute("tutorials", tutorep.findAll());
		return "index";
	}

	@PostMapping("/add")
	public String addTutorial(Tutorial tut, BindingResult result, Model model) {
		if (result.hasErrors())
			return "add-tutorial";
		tutorep.save(tut);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable int id, Model model) {
		Tutorial tut = tutorep.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid tutorial id: " + id));
		model.addAttribute("tutorial", tut);
		return "update-tutorial";
	}

	@PostMapping("/update/{id}")
	public String updateTutorial(@PathVariable int id, Tutorial tut, BindingResult result, Model model) {
		if (result.hasErrors()) {
			tut.setId(id);
			return "update-tutorial";
		}
		tutorep.save(tut);
		return "redirect:/tutorialsman/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteTutorial(@PathVariable int id, Model model) {
		Tutorial tut = tutorep.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid tutorial id: " + id));
		tutorep.delete(tut);
		return "redirect:/tutorialsman/list";
	}
}
