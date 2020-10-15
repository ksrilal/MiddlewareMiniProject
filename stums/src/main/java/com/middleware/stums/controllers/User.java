package com.middleware.stums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.middleware.stums.models.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class User {
    private final RestTemplate restTemplate;

    public User(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public String allUsers(Model model) {
        UserDTO[] users = restTemplate.getForObject("http://localhost:8080/user/all", UserDTO[].class);
        model.addAttribute("users", users);
        return "all";
    }



    @GetMapping("/delete")
    public String deleteAction(@RequestParam() Integer id, Model model) {
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String response = restTemplate.exchange(
                "http://localhost:8080/user/delete?id=" + id,
                HttpMethod.DELETE,
                entity,
                String.class
        ).getBody();
        model.addAttribute("response", response);
    }
    catch (Exception e){
        System.out.println(e);
        return "xxx";
    }
    return "redirect";
    }



    @GetMapping("/toAdd")
    public String addLoad() {
        return "add";
    }



    @GetMapping("/toUpdate")
    public String updateLoad(
            @RequestParam() Integer id,
            @RequestParam() String firstName,
            @RequestParam() String lastName,
            @RequestParam() Integer age,
            @RequestParam() String email,
            Model model
    ) {
        UserDTO user  = new UserDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);

        model.addAttribute("user", user);
        return "update";
    }



    @GetMapping("/updateNew")
    public String updateAction(
            @RequestParam() Integer id,
            @RequestParam() String firstName,
            @RequestParam() String lastName,
            @RequestParam() Integer age,
            @RequestParam() String email,
            Model model
    ) {
        UserDTO user  = new UserDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);

        String response = "failed";
        try{
            String jsonBody = objectMapper.writeValueAsString(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            response = restTemplate.exchange(
                    "http://localhost:8080/user/update",
                    HttpMethod.PUT,
                    entity,
                    String.class
            ).getBody();
        } catch (Exception e){
            return "xxx";
        }
        model.addAttribute("response", response);
        return "redirect";
    }



    @GetMapping("/addNew")
    public String addAction(
            @RequestParam() String firstName,
            @RequestParam() String lastName,
            @RequestParam() Integer age,
            @RequestParam() String email,
            Model model
    ) {
        UserDTO user  = new UserDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);

        String response = "failed";
        try{
            String jsonBody = objectMapper.writeValueAsString(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            response = restTemplate.exchange(
                    "http://localhost:8080/user/add",
                    HttpMethod.POST,
                    entity,
                    String.class
            ).getBody();
        } catch (Exception e){
            return "xxx";
        }
        model.addAttribute("response", response);
        return "redirect";
    }
}
