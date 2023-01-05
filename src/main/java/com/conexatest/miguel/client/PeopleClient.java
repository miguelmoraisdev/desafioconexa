package com.conexatest.miguel.client;

import com.conexatest.miguel.dto.People;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "swapdev", url = "https://swapi.dev/api/")
public interface PeopleClient {

    @GetMapping(value = "people/1", consumes="application/json")
    public People getLukeSkywalker();
}
