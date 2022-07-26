/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DBBewerbung.CodeChallenge;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maximilian
 */
//Repository zur Verwaltung des Tables Betriebsstelle
@Repository
public interface BetriebsstelleRepository extends CrudRepository<Betriebsstelle, Long> {

    //finde Betriebsstelle mit Abkürzung abkuerzung
    Betriebsstelle findByAbkuerzung(String abkuerzung);

    //lösche Betriebsstelle mit Abkürzung abkuerzung
    @Transactional
    Long deleteByAbkuerzung(String abkuerzung);

}
