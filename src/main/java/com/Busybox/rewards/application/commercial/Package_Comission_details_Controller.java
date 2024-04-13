package com.Busybox.rewards.application.commercial;

import java.util.List;
import java.util.Map;
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

import jakarta.persistence.EntityNotFoundException;
@CrossOrigin("*")         
@RestController
@RequestMapping("/details")
public class Package_Comission_details_Controller {

	@Autowired package_comission_dao dao;
	@Autowired package_comission_impl impl;
	@Autowired package_master_dao daoo;
	
	@GetMapping("/hello")
	public String hello() {
		return "helllo";
	}
	
	@GetMapping("/showall")
	public ResponseEntity<?> GetAll(){
		try {
			return ResponseHandler.generateResponse(dao.findAll(), HttpStatus.OK, "Details Fetched Successfully");
		}
		catch(Exception e) {
			return ResponseHandler.generateResponse(e, HttpStatus.NOT_FOUND, "Failed To Load Data");
		}
	}
	
	
	@GetMapping("comm/getAll/{id}")
	public ResponseEntity<?> getAllById(@PathVariable Long id){
		try {
			Optional<Package_Comission_Details> optionalEntity = dao.findById(id);
			if(optionalEntity.isPresent()) {
				Package_Comission_Details entity = optionalEntity.get();
				return ResponseHandler.generateResponse(entity, HttpStatus.OK, "Fetched Successfully");
			} else {
				return ResponseHandler.generateResponse(null, HttpStatus.OK, "Entity Not Found");
			}
		}
		catch(Exception e) {
			return ResponseHandler.generateResponse(e.toString(), HttpStatus.OK, "Failed To Load Data");
		}
	}
	
	@PostMapping("/add/commission")
	public ResponseEntity<?> addComission(@RequestBody Package_Comission_Details inputs){
		try {
			
			if(inputs == null) {
				return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Not Added");
			}else {
				long packageCode =inputs.getPackage_Id();
            	long id =dao.getId(packageCode);
            	inputs.setPackage_Id(id);
				Package_Comission_Details saved = dao.save(inputs);
				return ResponseHandler.generateResponse(saved, HttpStatus.CREATED, "Created");
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponse(e.toString(), HttpStatus.NOT_FOUND, "Failed To Load/ Add Data");
		}
	}
	
	 @PostMapping("/add/commission/array")
	 public ResponseEntity<?> addComissionArray(@RequestBody Package_Comission_Details[] inputs) {
	        try {
	            if (inputs == null) {
	                return ResponseHandler.generateResponse(null, HttpStatus.OK, "NULL DATA");
	            } else {
	                for (Package_Comission_Details pcd : inputs) {
	                	long packageCode =pcd.getPackage_Id();
	                	long id =dao.getId(packageCode);
	                	pcd.setPackage_Id(id);
	                     dao.save(pcd);
	                }
//	                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added " + inputs.length + " commission details.");
	                return ResponseHandler.generateResponse("Successfully added " + inputs.length + " commission details.", HttpStatus.OK , inputs.length + " Successfully Added");
	            }
	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add commission details. Error: " + e.getMessage());
	            return ResponseHandler.generateResponse("FAILED", HttpStatus.OK, "Failed");
	        }
	    }
	
	@PutMapping("/update/package/commission/array/{id}")
	public ResponseEntity<?> updatePackageCommissionDetails(@PathVariable Long id, @RequestBody Package_Comission_Details updatedDetails) {
	    try {
	        if (updatedDetails == null) {
	            return ResponseHandler.generateResponse(null, HttpStatus.BAD_REQUEST, "Request body is null");
	        }

	        Optional<Package_Comission_Details> optionalDetails = dao.findById(id);

	        if (optionalDetails.isPresent()) {
	            Package_Comission_Details existingDetails = optionalDetails.get();
	            // Update the existing details with the new data
	            // Perform the necessary update operations here (e.g., copy properties from updatedDetails)
	            //existingDetails.setPackage_Id(updated)

	            Package_Comission_Details updatedEntity = dao.save(existingDetails);

	            return ResponseHandler.generateResponse(updatedEntity, HttpStatus.OK, "Commission Details Updated Successfully");
	        } else {
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Commission Details not found");
	        }
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating Commission Details");
	    }
	}
	

    @PutMapping("edit/data/{id}")
    public ResponseEntity<Object> updatePackageComissionDetails(
            @PathVariable("id") long id,
            @RequestBody Package_Comission_Details updatedDetails) {    

 
    	Package_Comission_Details existingDetails = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Package Commission Details not found with id: " + id));

        existingDetails.setTo_amount(updatedDetails.getTo_amount());
        existingDetails.setFrom_amount(updatedDetails.getFrom_amount());
        existingDetails.setComissionAmount(updatedDetails.getComissionAmount());
        existingDetails.setIsFlat(updatedDetails.getIsFlat());
        existingDetails.setStatus(updatedDetails.getStatus());


        Package_Comission_Details updatedEntity = dao.save(existingDetails);

        	return ResponseHandler.generateResponse(updatedEntity, HttpStatus.OK, "Updated");
    }

    @PutMapping("/update/commission/array")
    public ResponseEntity<Object> bulkUpdatePackageComissionDetails(@RequestBody Package_Comission_Details[] updatedDetailsArray) {
        try {
            if (updatedDetailsArray == null || updatedDetailsArray.length == 0) {
                return ResponseEntity.badRequest().body("Request body is empty");
            }

            // Iterate through the array of updated details and update each one
            for (Package_Comission_Details updatedDetails : updatedDetailsArray) {
                Long id = updatedDetails.getId(); // Assuming there's an ID field in Package_Comission_Details
                if (id != null) {
                    // Find the existing entity by ID
                    Package_Comission_Details existingDetails = dao.findById(id).orElse(null);

                    if (existingDetails != null) {
                        // Update the existing entity with the new data
                        existingDetails.setTo_amount(updatedDetails.getTo_amount());
                        existingDetails.setFrom_amount(updatedDetails.getFrom_amount());
                        existingDetails.setComissionAmount(updatedDetails.getComissionAmount());
                        existingDetails.setIsFlat(updatedDetails.getIsFlat());
                        existingDetails.setStatus(updatedDetails.getStatus());

                        // Save the updated entity
                        dao.save(existingDetails);
                    }
                }
            }

            return ResponseHandler.generateResponse(updatedDetailsArray.length + "Updated Successfully", HttpStatus.OK, "Updated");
        } catch (Exception e) {
        	return ResponseHandler.generateResponse("ERROR", HttpStatus.BAD_REQUEST, "Not Updated");
        }
    }
    
	@DeleteMapping("/delete/package/commission/details/{id}")
	public ResponseEntity<?> deletePackageCommissionDetails(@PathVariable Long id) {
	    try {
	        Optional<Package_Comission_Details> optionalDetails = dao.findById(id);

	        if (optionalDetails.isPresent()) {
	            dao.deleteById(id);

	            return ResponseHandler.generateResponse(null, HttpStatus.NO_CONTENT, "Commission Details Deleted Successfully");
	        } else {
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Commission Details not found");
	        }
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.getLocalizedMessage() + " ||||ERROR||||", HttpStatus.INTERNAL_SERVER_ERROR, "Error Deleting Commission Details");
	    }
	}
	
	
	@GetMapping("/viewcomission/{id}")
	public ResponseEntity<?> viewC(@PathVariable Long id){
		try {
			long lo = daoo.getIdfromPackageCode(id);
			List<Package_Comission_Details> abc = dao.getAllComissionDetails(lo);
			return ResponseHandler.generateResponse(abc, HttpStatus.OK, "Successfully Fetched");
		}catch(Exception e) {
			return ResponseHandler.generateResponseNull( "Failed",HttpStatus.BAD_REQUEST	);
		}
	}
	
	 @PostMapping("/test")
	public ResponseEntity<?> test(@RequestBody Map<String, String> incomingData) {
	        try {
	            // Your logic here...

	            // Call the checkComissionAmount method from your service
	        	double amount = Double.parseDouble(incomingData.get("amount"));
	           // double entryAmount = Double.parseDouble(incomingData.get("entry_amount"));
	        //    int customerId = Integer.parseInt(incomingData.get("customer_id"));

	            if (!incomingData.isEmpty()) {
//	            	impl.checkComissionAmount(amount, entryAmount, customerId);
	                // If the result is a map, return it as a response
	                return ResponseHandler.generateResponse(impl.checkComissionAmount(amount,"5"), HttpStatus.OK, "Successfull Connected to Impl");
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            
	            
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }}}