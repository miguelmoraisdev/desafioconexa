package com.conexatest.miguel.controller;

import com.conexatest.miguel.dto.FilmResponse;
import com.conexatest.miguel.service.FilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@RequestMapping(value = "/movie")
public class FilmController {

    @Autowired
    private FilmService clientService;

    @GetMapping(value = "/lukeskywalker")
    public ResponseEntity<Page<FilmResponse>> getPeople(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "episodeId") String episodeId
    ) throws ParseException {
        return ResponseEntity.ok().body(clientService.getLukeSkywalker(title, episodeId));
    }
}
