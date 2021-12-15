package org.upec.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// @XmlRootElement(name="Professeurs")
public class Professeurs {
    private ArrayList<Professeur> professeurs;

    public Professeurs(){
        professeurs = new ArrayList<Professeur>();
    }
    
    // @XmlElement(name="Professeur")
    public ArrayList<Professeur> getProfesseurs(){
        return this.professeurs;
    }

    public void setProfesseurs(ArrayList<Professeur> professeeurs){
        this.professeurs = professeeurs;
    }

    public String toString(){
        String profs="";
        for(Professeur prof:getProfesseurs()){
         profs += prof.getDiplome()+", ";
        }
        return profs; 
    }
}
