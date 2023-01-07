package com.conexatest.miguel.client;

import com.conexatest.miguel.dto.Film;
import com.conexatest.miguel.dto.People;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.name.site}", url = "${feign.url.site}")
public interface FeignRestClient {

    @GetMapping(value = "people/1", consumes="application/json")
    public People getLukeSkywalker();

    @GetMapping(value = "films/{id}", consumes="application/json")
    public Film getLukeSkywalkerFilms(@PathVariable String id);

}
