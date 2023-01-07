package com.conexatest.miguel.service;

import com.conexatest.miguel.client.FeignRestClient;
import com.conexatest.miguel.dto.Film;
import com.conexatest.miguel.dto.FilmResponse;
import com.conexatest.miguel.dto.People;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class FilmServiceTest {

    @InjectMocks
    private FilmService filmService;

    @Mock
    private FeignRestClient feignRestClient;

    private FilmResponse filmResponse1;
    private FilmResponse filmResponse2;
    private People people;
    private List<FilmResponse> listClient = new ArrayList<>();
    private String[] films = new String[1];
    private String title;
    private String episodeid;
    private Page<FilmResponse> page;
    private Film film = new Film();

    @BeforeEach
    void setUp() throws Exception {
        filmResponse1 = FilmResponse.builder()
                .title("Star Wars 1")
                .episodeId("1")
                .director("George Lucas")
                .releaseDate(new Date()).build();
        filmResponse2 = FilmResponse.builder()
                .title("Star Wars 2")
                .episodeId("2")
                .director("George Lucas")
                .releaseDate(new Date()).build();
        title = "Star Wars 1";
        episodeid = "2";
        listClient.add(filmResponse1);
        listClient.add(filmResponse2);
        page = new PageImpl<>(listClient);
        film = Film.builder().director("George Lucas").episodeId("2").title("Star Wars 2").releaseDate("1977-05-25").build();
        people = new People();
        films[0] = "https://swapi.dev/api/films/2/";
        people.setFilms(films);

        when(feignRestClient.getLukeSkywalker()).thenReturn(people);
        when(feignRestClient.getLukeSkywalkerFilms(anyString())).thenReturn(film);
    }

    @Test
    void testGetLukeSkywalker() {
            title = "Star Wars 2";
            Assertions.assertNotNull(filmService.getLukeSkywalker(null, null));
            Assertions.assertNotNull(filmService.getLukeSkywalker(null, episodeid));
            Assertions.assertNotNull(filmService.getLukeSkywalker(title, null));
    }

    @Test
    void testGetFilmeResponseFromFilm() {
        Assertions.assertNotNull(filmService.getFilmeResponseFromFilm(film));
    }

    @Test
    void testGetPagination() {
        Assertions.assertEquals(2, filmService.getPagination(listClient).getTotalElements());
    }

    @Test
    void testGetFilter() {
        //with title and espisodeId
        List<FilmResponse> listFilter = filmService.getFilter(title, episodeid, listClient);
        Assertions.assertEquals(2, listFilter.size());

        //with title
        episodeid = null;
        Assertions.assertEquals(1, filmService.getFilter(title, episodeid, listClient).size());

        //with episodeId
        title = null;
        episodeid = "2";
        Assertions.assertEquals(1, filmService.getFilter(title, episodeid, listClient).size());

        //without episodeId and title
        title = null;
        episodeid = null;
        Assertions.assertEquals(2, filmService.getFilter(title, episodeid, listClient).size());
    }
}
