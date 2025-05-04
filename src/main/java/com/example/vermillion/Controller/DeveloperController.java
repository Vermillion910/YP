package com.example.vermillion.Controller;

import com.example.vermillion.Model.Developer;
import com.example.vermillion.Repository.DeveloperRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @GetMapping
    public String listDevelopers(Model model) {
        model.addAttribute("developers", developerRepository.findAll());
        return "developers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "developers/form";
    }

    @PostMapping
    public String createDeveloper(@ModelAttribute Developer developer) {
        developerRepository.save(developer);
        return "redirect:/developers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid developer Id:" + id));
        model.addAttribute("developer", developer);
        return "developers/form";
    }

    @PostMapping("/{id}")
    public String updateDeveloper(@PathVariable Long id, @ModelAttribute Developer developer) {
        developer.setDeveloperId(id);
        developerRepository.save(developer);
        return "redirect:/developers";
    }

    @GetMapping("/{id}/delete")
    public String deleteDeveloper(@PathVariable Long id) {
        developerRepository.deleteById(id);
        return "redirect:/developers";
    }
}