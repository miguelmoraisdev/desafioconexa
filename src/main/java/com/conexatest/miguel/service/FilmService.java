package com.conexatest.miguel.service;

import com.conexatest.miguel.client.FeignRestClient;
import com.conexatest.miguel.dto.Film;
import com.conexatest.miguel.dto.FilmResponse;
import com.conexatest.miguel.dto.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FeignRestClient feignRestClient;

    public Page<FilmResponse> getLukeSkywalker(String title, String episodeId) throws ParseException {
        People people = feignRestClient.getLukeSkywalker();
        List<FilmResponse> listFilm = new ArrayList<>();
        for (int i=0; i < people.getFilms().length; i++) {
            String link = people.getFilms()[i];
            String[] splitted = link.split("/");
            String id = splitted[5];
            Film film = feignRestClient.getLukeSkywalkerFilms(id);
            listFilm.add(getFilmeResponseFromFilm(film));
        }

        return getPaginação(getFilter(title, episodeId, listFilm));
    }

    protected FilmResponse getFilmeResponseFromFilm(Film film) throws ParseException {
        return FilmResponse.builder()
                .title(film.getTitle())
                .episodeId(film.getEpisodeId())
                .director(film.getDirector())
                .releaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(film.getReleaseDate()))
                .build();
    }

    protected PageImpl getPaginação(List<FilmResponse> responseList) {
        Pageable pageable = PageRequest.ofSize(10);
        return new PageImpl<>(responseList, pageable, responseList.size());
    }

    protected List<FilmResponse> getFilter(String title, String epsiodeId, List<FilmResponse> filmResponses) {
        List<FilmResponse> filteredList = new ArrayList<>();
        if (title != null && epsiodeId != null) {
            filteredList = filmResponses.stream().filter(x -> x.getTitle().equalsIgnoreCase(title) || x.getEpisodeId().equalsIgnoreCase(epsiodeId)).toList();
        } if (title == null) {
            filteredList = filmResponses.stream().filter(x -> x.getEpisodeId().equals(epsiodeId)).toList();
        } if (epsiodeId == null) {
            filteredList = filmResponses.stream().filter(x -> x.getTitle().equalsIgnoreCase(title)).toList();
        }
        return filteredList;
    }
}
