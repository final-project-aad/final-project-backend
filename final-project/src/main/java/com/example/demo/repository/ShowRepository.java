package com.example.demo.repository;

import com.example.demo.model.Show;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by duhlig on 8/17/17.
 */
public interface ShowRepository extends CrudRepository<Show, Integer> {
}
