package com.conexatest.miguel.service;

import com.conexatest.miguel.client.PeopleClient;
import com.conexatest.miguel.dto.Movie;
import com.conexatest.miguel.dto.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private PeopleClient peopleClient;

    public People getLukeSkywalker() {
        People people = peopleClient.getLukeSkywalker();
        return people;
    }
}
