package kz.bitlab.springsprinttask2.controller;

import kz.bitlab.springsprinttask2.entities.ApplicationRequest;
import kz.bitlab.springsprinttask2.entities.Courses;
import kz.bitlab.springsprinttask2.repository.ApplicationRequestRepository;
import kz.bitlab.springsprinttask2.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class HomeController {

    private final ApplicationRequestRepository applicationRequestRepository;
    private final CoursesRepository coursesRepository;


    @GetMapping(value = "/")
    public String indexPage(Model model) {
        List<ApplicationRequest> applicationRequestsList = applicationRequestRepository.findAll();
        model.addAttribute("requests", applicationRequestsList);

        return "index";
    }

    @GetMapping(value = "/new-requests")
    public String newRequest(Model model) {
        List<ApplicationRequest> applicationRequestsList = applicationRequestRepository.findAllByHandledIsFalse();
        model.addAttribute("requests", applicationRequestsList);

        return "index";
    }

    @GetMapping(value = "/handled-requests")
    public String handledRequest(Model model) {
        List<ApplicationRequest> applicationRequestsList = applicationRequestRepository.findAllByHandledIsTrue();
        model.addAttribute("requests", applicationRequestsList);

        return "index";
    }

    @GetMapping(value = "/details/{requestId}")
    public String detailsRequest(@PathVariable(name = "requestId") Long id, Model model) {
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);
        model.addAttribute("request", applicationRequest);

        List<Courses> coursesList = coursesRepository.findAll();
        model.addAttribute("courses", coursesList);

        return "details";
    }

    @PostMapping(value = "/change-request")
    public String changeRequest(ApplicationRequest request) {
        applicationRequestRepository.save(request);

        return "redirect:/";
    }

    @PostMapping(value = "/delete-request")
    public String deleteRequest(Long id) {
        applicationRequestRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping(value = "/add-request")
    public String addRequest(Model model) {
        List<Courses> coursesList = coursesRepository.findAll();
        model.addAttribute("courses", coursesList);

        return "add_request";
    }

    @PostMapping(value = "/add-request")
    public String addRequest(ApplicationRequest request) {
        applicationRequestRepository.save(request);

        return "redirect:/";
    }




}
