import org.apache.jena.rdf.model.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;


public class FirstJenaProject {

	public static void main(String[] args) throws IOException {


	

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();

		
		// reading text file 
		 	File txt = new File("C:\\Users\\IT_ANDRICE\\Desktop\\DEMMM\\bikes\\src\\main\\resources\\stops.txt");
		    Scanner scanner = new Scanner(txt);
		    
		    ArrayList<Stops> stopList = new ArrayList<Stops>();
		    
		    while(scanner.hasNext()){
		    	
		    	Stops aStop = new Stops();
		    	String string = scanner.nextLine();
		    	String[] parts = string.split(",");
		    	
		  
		    	String id = parts[0];
		    	aStop.setStopId(id);
		    	
		    	String name = parts[1];
		    	aStop.setStopName(name);
		    	
		    	String lon = parts[3];
		    	aStop.setStoplon(lon);
		    	
		    	String lat = parts[4];
		    	aStop.setStoplat(lat);
		    
		    	
		    	stopList.add(aStop);
		    	
		    }
		   
		    
			// TESTING THE LIST 		    
					  /*  for (int i = 0; i < stopList.size(); i++) {
							System.out.println("Station name in List:" + stopList.get(i).getStopName());
						}*/
		    

		    //prefixes
			final String ex = "http://www.example.com#";
			final String rdfs  = "http://www.w3.org/2000/01/rdf-schema#";
			final String xsd = "http://www.w3.org/2001/XMLSchema#";
			final String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";

			
			 model.setNsPrefix( "ex", ex );
			 model.setNsPrefix( "rdfs", rdfs );
			 model.setNsPrefix( "xsd", xsd );
			 model.setNsPrefix( "geo", geo );
			 
			

		    for (int i = 0; i < stopList.size(); i++) {
							 model.createResource(ex+stopList.get(i).getStopId())
									.addProperty(RDFS.label,stopList.get(i).getStopName())
									.addProperty(RDF.type, geo+"SpatialThing")
									.addLiteral(model.createProperty(geo + "lat"), stopList.get(i).getStoplat())
									.addLiteral(model.createProperty(geo + "lon"), stopList.get(i).getStoplon());
						}
		    
		  File fileName= new File("TRABELSI.ttl");
		  model.write(new FileOutputStream(fileName), "Turtle");
		  model.write(System.out,"Turtle");
	}
	
	 

}
