/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DBBewerbung.CodeChallenge;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author maximilian
 */
//Verarbeitung von HTTP Request
@RestController
@RequestMapping("/betriebsstelle")
public class BetriebstelleController {

    @Autowired
    BetriebsstelleRepository betriebsstelleRepository;

    //Get Request
    @GetMapping("/{abkuerzung}")
    public HashMap<String, String> readBetriebsstelle(@PathVariable String abkuerzung) {
        //finde betriebsstelle mit Abkürzung abkuerzung
        Optional<Betriebsstelle> betriebsstelle = Optional.ofNullable(betriebsstelleRepository.findByAbkuerzung(abkuerzung));
        //überprüfe, ob btriebsstelle null
        if (betriebsstelle.isPresent()) {

            //erzeuge HashMap mit Name, Kurzname und Typ für Ausgabe
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Name", betriebsstelle.get().getName());
            map.put("Kurzname", betriebsstelle.get().getKurzname());
            map.put("Typ", betriebsstelle.get().getTyp());
            return map;
        }

        //wenn betriebsstelle null, gebe HTTPStatus 404 zurück
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Betriebsstelle mit dieser Abkuerzung existiert nicht");
    }
    
    /*
    @PostMapping("")
    public void createBetriebsstelle(@RequestBody Betriebsstelle betriebsstelleNeu) {
        betriebsstelleRepository.save(betriebsstelleNeu);
    }

    @PutMapping("/{abkuerzung}")
    public void changeBetriebsstelle(@PathVariable String abkuerzung, @RequestBody Betriebsstelle betriebsstelleChanged) {
        Optional<Betriebsstelle> betriebstelle = Optional.ofNullable(betriebsstelleRepository.findByAbkuerzung(abkuerzung));

        if (betriebstelle.isPresent()) {
            Betriebsstelle betiebstelleInstance = betriebstelle.get();

            betiebstelleInstance.setAbkuerzung(betriebsstelleChanged.getAbkuerzung());
            betiebstelleInstance.setName(betriebsstelleChanged.getName());
            betiebstelleInstance.setKurzname(betriebsstelleChanged.getKurzname());
            betiebstelleInstance.setTyp(betriebsstelleChanged.getTyp());

            betriebsstelleRepository.save(betiebstelleInstance);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Betriebsstelle mit dieser Abkuerzung existiert nicht");
    }

    @DeleteMapping("/{abkuerzung}")
    public void deleteBetriebsstelle(@PathVariable String abkuerzung) {
        Optional<Betriebsstelle> betriebsstelle = Optional.ofNullable(betriebsstelleRepository.findByAbkuerzung(abkuerzung));

        if (betriebsstelle.isPresent()) {

            betriebsstelleRepository.deleteByAbkuerzung(abkuerzung);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Betriebsstelle mit dieser Abkuerzung existiert nicht");
    }*/
}
