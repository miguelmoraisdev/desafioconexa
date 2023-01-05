package com.conexatest.miguel.controller;

import com.conexatest.miguel.dto.People;
import com.conexatest.miguel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/lukeskywalker")
    public ResponseEntity<People> getPeople() {
        return ResponseEntity.ok().body(clientService.getLukeSkywalker());
    }
}
