package com.example.demo.controller;


import com.example.demo.model.Artist;
import com.example.demo.repository.ArtistRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/artist")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepo;

    @PostMapping("/register")
    public String artistSignUp(@RequestBody Artist artist){

        System.out.println(artist);


        String hashed = BCrypt.hashpw(artist.getPassword(), BCrypt.gensalt(12));
        String email = artist.getEmail();
        String firstName = artist.getFirstName();
        String lastName = artist.getLastName();


        Artist newArtist = new Artist();
        newArtist.setFirstName(firstName);
        newArtist.setLastName(lastName);
        newArtist.setEmail(email);
        newArtist.setPassword(hashed);


        artistRepo.save(newArtist);

        return "user created";

    }

    @PostMapping("/login")
    public String artistLogin(@RequestBody Artist artist, HttpSession session){

        Artist foundArtist = artistRepo.findByEmail(artist.getEmail());

        if(foundArtist == null){
            return "no artist found";
        }
        if (BCrypt.checkpw(artist.getPassword(), foundArtist.getPassword())){
            session.setAttribute("artistId", foundArtist.getArtistId());
            return "user successfully logged in";

        }

        return "No email/password combination exsist";
    }


}