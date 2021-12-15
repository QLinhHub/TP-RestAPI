package org.upec.dao;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class WriteXML {

    private File SERIALIZED_FILE;

    public WriteXML(){
        this.SERIALIZED_FILE = new File("/home/quanglinhle/Data/M2_SDTS/Gestion_de_Processus/REST_LIVE_EXAMPLE/demo/restapi/src/main/webapp/prof.xml");
    }

    public WriteXML(File file){
        this.SERIALIZED_FILE = file;
    }

    public void write_xml(ResultSet results) throws SQLException{
        Professeurs profs = new Professeurs();

        ArrayList<Professeur> profList = profs.getProfesseurs();
        while(results.next()){
            int num_p = Integer.parseInt(results.getString("NUM_P"));
            int id_p = Integer.parseInt(results.getString("FK_ID_P1")); 
            String diplome = results.getString("DIPLOME");
            String module = results.getString("FK_TITRE_MODULE");

            Professeur prof = new Professeur(num_p, id_p, diplome, module);

            profList.add(prof);
        }

        profs.setProfesseurs(profList); 
        
        // try {
        //     JAXBContext jaxbContext = JAXBContext.newInstance(Professeurs.class);
        //     Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    
        //     // output pretty printed
        //     jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    
        //     jaxbMarshaller.marshal(profs, SERIALIZED_FILE);
        //     // jaxbMarshaller.marshal(profs, System.out);
    
        //   } catch (JAXBException e) {
        //     e.printStackTrace();
        //   }

        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE)));
        }catch(FileNotFoundException fileNotFound){
        System.out.println("ERROR: While Creating or Opening file");
        }
        encoder.writeObject(profs);
        encoder.close();
    }

    public static void main(String[] args) throws SQLException {

        Database db = new Database();
        ResultSet results = db.getData();

        WriteXML xml_writer = new WriteXML();
        xml_writer.write_xml(results);
    }
}