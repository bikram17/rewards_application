package com.Busybox.rewards.application.commercial;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.controller.ResponseHandler;

// 						MASTER
@CrossOrigin("*")
@RestController
@RequestMapping("/master")
public class Package_Master_Controller {

	@Autowired private package_master_dao dao;
	
	
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		try{
			List<Package_Master_Model> abc=dao.findAll();
			return ResponseHandler.generateResponse(abc, HttpStatus.OK, "Data Fetched Successfully");
		}catch(Exception e) {
			return ResponseHandler.generateResponse(e.toString(), HttpStatus.NOT_FOUND, "||ERROR||");
		}
	}
	
	@GetMapping("/getall/{id}")
	public ResponseEntity<?> getallbyid(@PathVariable Long id) {
	    try {
	        Optional<Package_Master_Model> optionalEntity = dao.findById(id);
	        if (optionalEntity.isPresent()) {
	            // Entity found, return it as a response
	            Package_Master_Model entity = optionalEntity.get();
	            return ResponseHandler.generateResponse(entity, HttpStatus.OK, "Fetched Successfully");
	        } else {
	            // Entity not found, return a response with an appropriate message
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Entity not found");
	        }
	    } catch (Exception e) {
	        // Handle exceptions and return an error response
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Load Data");
	    }
	}


	
	
	
	
	@PostMapping("/add/package/master")
	public ResponseEntity<?> addOneCommercialPackages(@RequestBody Package_Master_Model pmm) {
	    try {
	        if (pmm == null) {
	            // Handle the case where the request body is null
	            return ResponseHandler.generateResponse(null, HttpStatus.BAD_REQUEST, "Request body is null");
	        }

	        // Save the package information into the database using dao.save(pmm)
	        Package_Master_Model savedPackage = dao.save(pmm);

	        // Return a success response with the saved package and a 201 CREATED status
	        return ResponseHandler.generateResponse(savedPackage, HttpStatus.OK, "Package Added Into Database");
	    } catch (Exception e) {
	        // Handle exceptions and return an error response with a 500 INTERNAL_SERVER_ERROR status
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Error Adding Package to Database");
	    }
	}

	@PostMapping("/add/package/master/array")
	public ResponseEntity<?> addOneCommercialPackagesArray(@RequestBody Package_Master_Model[] pmm) {
	    try {
	        if (pmm == null) {
	            // Handle the case where the request body is null
	            return ResponseHandler.generateResponse(null, HttpStatus.BAD_REQUEST, "Request body is null");
	        }

	        // Save the package information into the database using dao.save(pmm)
	    //    Package_Master_Model savedPackage = dao.save(pmm);

	        for(Package_Master_Model pModel : pmm) {
	        	dao.save(pModel);
	        }
	        
//	        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added " + pmm.length + " commission details.");
	        return ResponseHandler.generateResponse(pmm, HttpStatus.OK, "Success added"+ pmm.length+ " commission details.");
	        // Return a success response with the saved package and a 201 CREATED status
	      //  return ResponseHandler.generateResponse(savedPackage, HttpStatus.CREATED, "Package Added Into Database");
	    } catch (Exception e) {
	        // Handle exceptions and return an error response with a 500 INTERNAL_SERVER_ERROR status
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Error Adding Package to Database");
	    }
	}
	
	@PutMapping("/update/{id1}")
	public ResponseEntity<?> updateOneCommercialPackage(@PathVariable Long id1, @RequestBody Package_Master_Model updatedPackage) {
	    try {
	        if (updatedPackage == null) {
	            // Handle the case where the request body is null
	            return ResponseHandler.generateResponse(null, HttpStatus.BAD_REQUEST, "Request body is null");
	        }

	        // Check if the entity with the given ID exists in the database
	        Long id = dao.getIdfromPackageCode(id1);
	        Optional<Package_Master_Model> optionalEntity = dao.findById(id);

	        if (optionalEntity.isPresent()) {
	            // Get the existing entity
	            Package_Master_Model existingPackage = optionalEntity.get();

	            // Update all properties of the existing entity with the new entity's properties
	            // For example, you can update all properties like this:
	            existingPackage.setPackagename(updatedPackage.getPackagename());
	            existingPackage.setStatus(updatedPackage.getStatus());
	            existingPackage.setPackagecode(updatedPackage.getPackagecode());
	            // ...

	            // Update the createdAt field (if needed)
	           // existingPackage.setTimestamp(updatedPackage.getTimestamp());

	            // Save the updated entity
	            Package_Master_Model updatedEntity = dao.save(existingPackage);

	            // Return a success response with the updated entity and a 200 OK status
	            return ResponseHandler.generateResponse(updatedEntity, HttpStatus.OK, "Package Updated Successfully");
	        } else {
	            // Handle the case where the entity with the given ID was not found
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Entity not found");
	        }
	    } catch (Exception e) {
	        // Handle exceptions and return an error response with a 500 INTERNAL_SERVER_ERROR status
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating Package");
	    }
	}



	@DeleteMapping("/delete/{id1}")
	public ResponseEntity<?> deleteOneCommercialPackage(@PathVariable Long id1) {
	    try {
	        // Check if the entity with the given ID exists in the database
	    	 Long id = dao.getIdfromPackageCode(id1);
	        Optional<Package_Master_Model> optionalEntity = dao.findById(id);
	        
	        if (optionalEntity.isPresent()) {
	            // Entity found, delete it
	            dao.deleteById(id);   

	            // Return a success response with a 204 NO_CONTENT status
	            return ResponseHandler.generateResponse(null, HttpStatus.NO_CONTENT, "Package Deleted Successfully");
	        } else {
	            // Handle the case where the entity with the given ID was not found
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Entity not found");
	        }
	    } catch (Exception e) {
	        // Handle exceptions and return an error response with a 500 INTERNAL_SERVER_ERROR status
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Error Deleting Package");
	    }
	}

	
	
}
