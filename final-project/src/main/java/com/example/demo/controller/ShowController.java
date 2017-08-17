package com.example.demo.controller;

import com.example.demo.model.Artist;
import com.example.demo.model.Show;
import com.example.demo.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duhlig on 8/17/17.
 */
@RestController
@RequestMapping("/api")
public class ShowController {
    @Autowired
    ShowRepository showRepo;

    @PostMapping("/create-show")
    @CrossOrigin
    public String createShow(@RequestBody Show newShow, HttpSession session) {
        Artist createdBy = userRepo.findOne((Integer) session.getAttribute("artistId"));
        List<Show> showList = new ArrayList<>();
        showList.add(newShow);
        createdBy.setShows(showList);
        try {
            showRepo.save(newShow);
        } catch (Exception ex) {
            return "error creating event";
        }
        return "event created successfully";
    }

    @GetMapping("/view-shows")
    @CrossOrigin
    public List<Show> allShows(HttpSession session) {
        Artist currentArtist = userRepo.findOne((Integer) session.getAttribute("artistId"));
        return currentArtist.getShows();
    }
}
