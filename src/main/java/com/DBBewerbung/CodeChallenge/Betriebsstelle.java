/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DBBewerbung.CodeChallenge;

import javax.persistence.*;

/**
 *
 * @author maximilian
 */
//Klasse zur Darstellung des Tables betriebsstelle 
@Entity
@Table(name = "Betriebsstelle")
public class Betriebsstelle {

    //Id einer Instanz
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String kurzname;
    private String typ;
    private String abkuerzung;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Kurzname
     */
    public String getKurzname() {
        return kurzname;
    }

    /**
     * @param Kurzname the Kurzname to set
     */
    public void setKurzname(String kurzname) {
        this.kurzname = kurzname;
    }

    /**
     * @return the Typ
     */
    public String getTyp() {
        return typ;
    }

    /**
     * @param Typ the Typ to set
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * @return the Abkuerzung
     */
    public String getAbkuerzung() {
        return abkuerzung;
    }

    /**
     * @param Abkuerzung the Abkuerzung to set
     */
    public void setAbkuerzung(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

}
