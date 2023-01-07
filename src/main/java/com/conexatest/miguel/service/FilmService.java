package com.conexatest.miguel.service;

import com.conexatest.miguel.client.FeignRestClient;
import com.conexatest.miguel.dto.Film;
import com.conexatest.miguel.dto.FilmResponse;
import com.conexatest.miguel.dto.People;
import com.conexatest.miguel.service.exceptions.FeignClientException;
import com.conexatest.miguel.service.exceptions.ParseDateException;
import feign.FeignException;
import org.slf4j.Logger;
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

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FilmService.class);

    public Page<FilmResponse> getLukeSkywalker(String title, String episodeId) {
        try {
            People people = feignRestClient.getLukeSkywalker();
            log.info("People entity returned");
            List<FilmResponse> listFilm = new ArrayList<>();
            for (int i=0; i < people.getFilms().length; i++) {
                String link = people.getFilms()[i];
                String[] splitted = link.split("/");
                String id = splitted[5];
                Film film = feignRestClient.getLukeSkywalkerFilms(id);
                log.info("Film returned");
                listFilm.add(getFilmeResponseFromFilm(film));
            }

            return getPagination(getFilter(title, episodeId, listFilm));
        } catch (FeignException e) {
            log.error("time out exception");
            throw new FeignClientException("time out exception");
        }

    }

    protected FilmResponse getFilmeResponseFromFilm(Film film) {
        try {
            return FilmResponse.builder()
                    .title(film.getTitle())
                    .episodeId(film.getEpisodeId())
                    .director(film.getDirector())
                    .releaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(film.getReleaseDate()))
                    .build();
        } catch (ParseException e) {
            log.error("Parse issue from release_date");
            throw new ParseDateException("Parse issue from release_date");
        }
    }

    protected PageImpl getPagination(List<FilmResponse> responseList) {
        Pageable pageable = PageRequest.ofSize(10);
        return new PageImpl<>(responseList, pageable, responseList.size());
    }

    protected List<FilmResponse> getFilter(String title, String epsiodeId, List<FilmResponse> filmResponses) {
        List<FilmResponse> filteredList = new ArrayList<>();
        if (title == null && epsiodeId == null) {
            filteredList = filmResponses;
        } else if (title == null && epsiodeId != null) {
            filteredList = filmResponses.stream().filter(x -> x.getEpisodeId().equals(epsiodeId)).toList();
        } else if (epsiodeId == null && title != null) {
            filteredList = filmResponses.stream().filter(x -> x.getTitle().equalsIgnoreCase(title)).toList();
        } else {
            filteredList = filmResponses.stream()
                    .filter(x -> x.getTitle().equalsIgnoreCase(title) || x.getEpisodeId().equalsIgnoreCase(epsiodeId)).toList();
        }
        return filteredList;
    }
}
