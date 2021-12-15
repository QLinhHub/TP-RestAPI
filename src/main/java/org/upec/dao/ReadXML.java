package org.upec.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;   

public class ReadXML{ 
	private String SERIALIZED_FILE_NAME;
	
	public ReadXML(){
		this.SERIALIZED_FILE_NAME = "/home/quanglinhle/Data/M2_SDTS/Gestion_de_Processus/REST_LIVE_EXAMPLE/demo/restapi/src/main/webapp/prof.xml";
	}

	public ReadXML(String xml_file_name){
		this.SERIALIZED_FILE_NAME = xml_file_name;
	}

	public ArrayList<Professeur> getAllProfs(Boolean read_from_db) throws SQLException{
		XMLDecoder decoder=null;

		if (read_from_db == true){
			// get data from database
			Database db = new Database();
			ResultSet results = db.getData();

			// write data to xml form
			File xml_file = new File(this.SERIALIZED_FILE_NAME);		
			WriteXML xmlWriter = new WriteXML(xml_file);
			xmlWriter.write_xml(results);
		}

		// decode xml file
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			Professeurs profs=(Professeurs)decoder.readObject();
			ArrayList<Professeur> pList= profs.getProfesseurs();
			System.out.println(pList);
			return pList;
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found");
			
			//Create and save an empty list
			ArrayList<Professeur> l = new ArrayList<Professeur>();
			this.saveUserListToXml(l);
			return l;
		}
	} 
	
   private void saveUserListToXml(ArrayList<Professeur> pList) {
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(pList);
		
		encoder.close();
   }

	// public static void main(String[] args) {
	// 	String SERIALIZED_FILE_NAME = "/home/quanglinhle/Data/M2_SDTS/Gestion_de_Processus/REST_LIVE_EXAMPLE/demo/restapi/src/main/webapp/prof.xml";
	// 	XMLDecoder decoder=null;
	// 	try {
	// 		decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
	// 	} catch (FileNotFoundException e) {
	// 		System.out.println("ERROR: File not found");
	// 	}
	// 	Professeurs profs=(Professeurs)decoder.readObject();
	// 	ArrayList<Professeur> pList= profs.getProfesseurs();
	// 	for(int i = 0; i < pList.size(); i++) {
	// 		Professeur p = pList.get(i);
	// 		System.out.println(p);
	// 	}
	// }
}

