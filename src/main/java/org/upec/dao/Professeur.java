package org.upec.dao;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// @XmlRootElement(name="Professeur")
public class Professeur {
    // @XmlElement(name="NUM_P")
    private int num_prof;
    
    // @XmlElement(name="FK_ID_P1")
    private int id_person;
    
    // @XmlElement(name="DIPLOME")
    private String diplome;
    
    // @XmlElement(name="FK_TITRE_MODULE")
    private String module;

    public Professeur(){}

    public Professeur(int num_prof, int id_person, String diplome, String module){
        this.num_prof = num_prof;
        this.id_person = id_person;
        this.diplome = diplome;
        this.module = module;
    }

    public void setNum_prof(int num_prof){
        this.num_prof = num_prof;
    }

    public void setId_person(int id_person){
        this.id_person = id_person;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public void setModule(String module){
        this.module = module;
    }

    public int getNum_prof(){
        return this.num_prof;
    }

    public int getId_person(){
        return this.id_person;
    }

    public String getDiplome() {
        return this.diplome;
    }

    public String getModule() {
        return this.module;
    }

    public String toString() {
        diplome = this.diplome;
        return diplome + " -"; 
    }
}
