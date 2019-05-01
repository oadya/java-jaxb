package com.jaxb.example.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//@XmlType( propOrder = { "name", "capital", "foundation", "continent" , "population"} )
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "Country" )
public class Country implements Serializable{
	
	
//  @XmlRootElement as root element.
//  @XmlElement in combination with setter methods.
//  @XmlAttribute to pass attributes to the XML nodes. These attributes can have properties like to be required or not.
//  @XmlType to indicate special options like to order of appearance in the XML.

	private String name;
	private String capital;
	private LocalDate foundation;
	private String continent;
	private int population;
	private int importance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public LocalDate getFoundation() {
		return foundation;
	}
	public void setFoundation(LocalDate foundation) {
		this.foundation = foundation;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	
	
	
	

}
