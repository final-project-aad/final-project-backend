package com.example.demo.repository;


import com.example.demo.model.Artist;
import org.springframework.data.repository.CrudRepository;
import sun.java2d.pipe.AATextRenderer;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    Artist findByEmail(String email);

}
