package com.olx.actuator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;



@Component
@Endpoint(id ="bug-fixes")
public class BugFixesActuator {
	
     static	Map<String,List<String>> bugsMap =new HashMap<>();
     
     @PostConstruct
     public void initialize() {
    	 bugsMap.put("v1",Arrays.asList("Validate Token Issue","Null username issue"));
    	
     }
     
     @ReadOperation
     public Map<String,List<String>>getAllbugs(){
    	 return bugsMap;
     }
     
     
     
     @WriteOperation
     public void addNewBugs(@Selector String id, String bugs){
    	  bugsMap.put(id,Arrays.asList(bugs.split(",")));
    
     }
     
     @DeleteOperation
     public void deleteBugFixes(@Selector String version) {
     bugsMap.remove(version);
   
     
     }
     
     

}
