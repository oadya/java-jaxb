package com.jaxb.example;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.jaxb.example.dto.Country;
import com.jaxb.example.dto.Department;
import com.jaxb.example.dto.Employee;
import com.jaxb.example.dto.EmployeeMap;
import com.jaxb.example.dto.Employees;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JAXBException, SAXException {
    	
    	//marshalCountry();
    	
    	// marshalEmployee();
    	
    	
    	//unmarshalEmployee();
    	
    	//jaxbJsonToObject();
    	
    	//jaxbObjectToJSON();
    	
    	//jaxbMapToXml();
    	
    	//jaxbXmlToMap();
    	
    //	jaxbListToXml();
    	
    //	jaxbValidationXmlFileToObject();
    		
    }
    
   public static void marshalCountry() throws JAXBException{
	   
 		Country country = new Country();
		
		country.setName( "Spain" );
		country.setCapital( "Madrid" );
		country.setContinent( "Europe" );
		country.setImportance( 1 );
		country.setFoundation( LocalDate.of( 1469, 10, 19 ) );
		country.setPopulation( 45000 );
		
		/* init jaxb marshaler */
		JAXBContext jaxbContext = JAXBContext.newInstance( Country.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		
		/* set this flag to true to format the output */
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		
		/* marshaling of java objects in xml (output to file and standard output) */
		jaxbMarshaller.marshal( country, new File( "src/main/resources/country.xml" ) );
		jaxbMarshaller.marshal( country, System.out );
    	
    }
   
   public static void marshalEmployee() throws JAXBException{
	   
	   Employee employee = new Employee();
		
	   employee.setId(1);
	   employee.setFirstName("john");
	   employee.setLastName("james");
	   
	   employee.setDepartment(new Department(101, "IT"));;
	   
	   
	   // Initialization using Arrays.asList
	   List<String> list1 = new ArrayList<String>( Arrays.asList("Swimming", "Playing", "Karate"));
	  
	   // inner class method to initialize ArrayList
	   List<String> list2 = new ArrayList<String>(){{
		   add("Swimming");
		   add("Playing");
		   add("Karate");
	   }}; 
		  
	   employee.setHobbies(list1);
	   
		/* init jaxb marshaler */
		JAXBContext jaxbContext = JAXBContext.newInstance( Employee.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		
		/* set this flag to true to format the output */
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		
		//Store XML to File
        File file = new File("src/main/resources/employee.xml");
        
		/* marshaling of java objects in xml (output to file and standard output) */
		jaxbMarshaller.marshal( employee, file );
		jaxbMarshaller.marshal( employee, System.out );
    	
    }
   
   
  public static void unmarshalEmployee() {
	   
	  String fileName = "src/main/resources/employee.xml";
	  
	  File xmlFile = new File(fileName);
      
      JAXBContext jaxbContext;
      try
      {
          jaxbContext = JAXBContext.newInstance(Employee.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           
          Employee employee = (Employee) jaxbUnmarshaller.unmarshal(xmlFile);
           
          System.out.println(employee);
      }
      catch (JAXBException e)
      {
          e.printStackTrace();
      }
    }
  
  private static void jaxbJsonToObject() {
	  
	  String fileName = "src/main/resources/employee.json";
      File xmlFile = new File(fileName);
      
      String jsonString = "{\"employee\":{\"department\":{\"id\":101,\"name\":\"IT\"},"
      		+ "\"firstName\":\"Lokesh\",\"id\":1,\"lastName\":\"Gupta\"}}";
      
   // Create the StreamSource by creating StringReader using the JSON input
      StreamSource json = new StreamSource(
              new StringReader("{\"employee\":{\"department\":{\"id\":101,\"name\":\"IT\"},"
                		+ "\"firstName\":\"Lokesh\",\"id\":1,\"lastName\":\"Gupta\"}}"));
       
      JAXBContext jaxbContext;
      try
      {
          jaxbContext = JAXBContext.newInstance(Employee.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           
          //Set JSON type
          jaxbUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
          jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
           
          Employee employee = (Employee) jaxbUnmarshaller.unmarshal(xmlFile);
          
          Employee employee2 = (Employee) jaxbUnmarshaller.unmarshal(new StringReader(jsonString));
          
          Employee employee3 = jaxbUnmarshaller.unmarshal(json, Employee.class).getValue();;
           
          System.out.println(employee3);
      }
      catch (JAXBException e)
      {
          e.printStackTrace();
      }
  }
  
  private static void jaxbObjectToJSON()
  {
	  Employee employee3 = new Employee(1, "Lokesh", "Gupta", new Department(101, "IT"));
	  
      try
      {
    	// Create a JaxBContext
          JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
       // Create the Marshaller Object using the JaxB Context
          Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        
           
          //Set JSON type
       // Set the Marshaller media type to JSON or XML
          jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
          // Set it to true if you need to include the JSON root element in the JSON output
          jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
          
          // To format JSON
          // Set it to true if you need the JSON output to formatted
             jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

          //Print JSON String to Console
          StringWriter sw = new StringWriter();
          jaxbMarshaller.marshal(employee3, sw);
          System.out.println(sw.toString());
      }
      catch (JAXBException e)
      {
          e.printStackTrace();
      }
  }
  
  public static void jaxbMapToXml() throws JAXBException{
	   
	  HashMap<Integer, Employee> map = new HashMap<Integer, Employee>();
		
	    Employee emp1 = new Employee();
	    emp1.setId(1);
	    emp1.setFirstName("Lokesh");
	    emp1.setLastName("Gupta");
	  
	     
	    Employee emp2 = new Employee();
	    emp2.setId(2);
	    emp2.setFirstName("John");
	    emp2.setLastName("Mclane");
	
	     
	    map.put( 1 , emp1);
	    map.put( 2 , emp2);
	   
	    //Add employees in map
	    EmployeeMap employeeMap = new EmployeeMap();
	    employeeMap.setEmployeeMap(map);
	    
		/* init jaxb marshaler */
		JAXBContext jaxbContext = JAXBContext.newInstance( EmployeeMap.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		
		/* set this flag to true to format the output */
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		
		//Store XML to File
       File file = new File("src/main/resources/employeeMap.xml");
       
		/* marshaling of java objects in xml (output to file and standard output) */
		jaxbMarshaller.marshal( employeeMap, file );
		jaxbMarshaller.marshal( employeeMap, System.out );
   	
   }
  
  public static void jaxbXmlToMap() {
	   
	  String fileName = "src/main/resources/employeeMap.xml";
	  
	  File xmlFile = new File(fileName);
      
      JAXBContext jaxbContext;
      try
      {
          jaxbContext = JAXBContext.newInstance(EmployeeMap.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           
          EmployeeMap empMap = (EmployeeMap) jaxbUnmarshaller.unmarshal(xmlFile);
           
          for(Integer empId : empMap.getEmployeeMap().keySet())
          {
              System.out.println(empMap.getEmployeeMap().get(empId).getFirstName());
              System.out.println(empMap.getEmployeeMap().get(empId).getLastName());
          }
      }
      catch (JAXBException e)
      {
          e.printStackTrace();
      }
    }
  
  public static void jaxbListToXml() throws JAXBException{
	   
	   Employees employees = new Employees();
	   
	   employees.setEmployees(new ArrayList<Employee>());
	   
	    Employee emp1 = new Employee();
	    emp1.setId(1);
	    emp1.setFirstName("Lokesh");
	    emp1.setLastName("Gupta");
	  
	     
	    Employee emp2 = new Employee();
	    emp2.setId(2);
	    emp2.setFirstName("John");
	    emp2.setLastName("Mclane");
	
	     
	    //Add the employees in list
	    employees.getEmployees().add(emp1);
	    employees.getEmployees().add(emp2);
	    
		/* init jaxb marshaler */
		JAXBContext jaxbContext = JAXBContext.newInstance( Employees.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		
		/* set this flag to true to format the output */
		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		
		//Store XML to File
       File file = new File("src/main/resources/employeeList.xml");
       
		/* marshaling of java objects in xml (output to file and standard output) */
		jaxbMarshaller.marshal( employees, file );
		jaxbMarshaller.marshal( employees, System.out );
   	
   }
  
  private static void jaxbValidationXmlFileToObject() throws JAXBException, SAXException {
      
	  String xmlFile = "src/main/resources/employee.xml";
      String xsdFile = "src/main/resources/employee1.xsd";
      
      JAXBContext jaxbContext;
       
      
          //Get JAXBContext
          jaxbContext = JAXBContext.newInstance(Employee.class);
           
          //Create Unmarshaller
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           
          //Setup schema validator
          SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
          Schema employeeSchema = sf.newSchema(new File(xsdFile));
          jaxbUnmarshaller.setSchema(employeeSchema);
           
          //Unmarshal xml file
          Employee employee = (Employee) jaxbUnmarshaller.unmarshal(new File(xmlFile));
           
          System.out.println(employee);
      
      
  }
  
  /*private static void objectToJsonJackson(){
	  
	  @SuppressWarnings("deprecation")
      Employee employee = new Employee(1, "Lokesh", "Gupta");
      ObjectMapper mapper = new ObjectMapper();
      try
      {
         mapper.writeValue(new File("c://temp/employee.json"), employee);
      } catch (JsonGenerationException e)
      {
         e.printStackTrace();
      } catch (JsonMappingException e)
      {
         e.printStackTrace();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
	  
  }*/
  
  
}
