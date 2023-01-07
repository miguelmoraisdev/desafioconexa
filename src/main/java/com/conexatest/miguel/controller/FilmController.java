package com.conexatest.miguel.controller;

import com.conexatest.miguel.dto.FilmResponse;
import com.conexatest.miguel.service.FilmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@RequestMapping(value = "/film")
public class FilmController {

    @Autowired
    private FilmService clientService;

    @Operation(summary = "Luke Skywalker films", description = "Get pagination of Luke Skywalker films",tags = "Get")
    @GetMapping(value = "/lukeskywalker")
    public ResponseEntity<Page<FilmResponse>> getFilmsByLuke(
            @Parameter(name = "Title of film", example = "A New Hope") @RequestParam(value = "title", required = false) String title,
            @Parameter(name = "Episode of film", example = "4") @RequestParam(value = "episodeId", required = false) String episodeId
    ) throws ParseException {
        return ResponseEntity.ok().body(clientService.getLukeSkywalker(title, episodeId));
    }
}
