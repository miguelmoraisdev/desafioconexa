package com.conexatest.miguel.controller;

import com.conexatest.miguel.dto.FilmResponse;
import com.conexatest.miguel.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    private String title;
    private String episodeId;
    private FilmResponse filmResponse1;
    private FilmResponse filmResponse2;
    private Page<FilmResponse> page;
    private Page<FilmResponse> page2;
    private Page<FilmResponse> page3;
    private List<FilmResponse> listClient = new ArrayList<>();

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
        episodeId = "2";
        listClient.add(filmResponse1);
        listClient.add(filmResponse2);
        page = new PageImpl<>(listClient);
        page2 = new PageImpl<>(List.of(filmResponse1));
        page3 = new PageImpl<>(List.of(filmResponse2));
        when(filmService.getLukeSkywalker(null, null)).thenReturn(page);
        when(filmService.getLukeSkywalker(null, episodeId)).thenReturn(page2);
        when(filmService.getLukeSkywalker(title, null)).thenReturn(page3);
        when(filmService.getLukeSkywalker(title, episodeId)).thenReturn(page);

    }

    @Test
    void testGetFilmsByLuke() throws Exception {

        //without title and episodeId
        ResultActions result = mockMvc.perform(get("/film/lukeskywalker").accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content", hasSize(2)));

        //with only title
        ResultActions result2 = mockMvc.perform(get("/film/lukeskywalker")
                .accept(MediaType.APPLICATION_JSON)
                .param("title", title));
        result2.andExpect(status().isOk());
        result2.andExpect(jsonPath("$.content", hasSize(1)));

        //with only epsisodeId
        ResultActions result3 = mockMvc.perform(get("/film/lukeskywalker")
                .accept(MediaType.APPLICATION_JSON)
                .param("episodeId", episodeId));
        result3.andExpect(status().isOk());
        result3.andExpect(jsonPath("$.content", hasSize(1)));

        //with title and episodeId
        ResultActions result4 = mockMvc.perform(get("/film/lukeskywalker")
                .accept(MediaType.APPLICATION_JSON)
                .param("title", title)
                .param("episodeId", episodeId));
        result4.andExpect(status().isOk());
        result4.andExpect(jsonPath("$.content", hasSize(2)));

    }


}
