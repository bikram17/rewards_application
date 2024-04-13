package com.Busybox.rewards.application.commercial;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "package_master_model")
public class Package_Master_Model {

	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    @Column(name = "package_Id", nullable = true)
    private Long id;
	
	@Column(name="Package_Name", nullable= true, length = 255)
	private String packagename;
	
	@Column(name="Package_Code", nullable= true, length = 255)
	private String Packagecode;

	@Column(name="Service_Name", nullable= true, length = 255)
	private String serviename;
	
	@Column(name="Service_Id", nullable= true, length = 255)
	private String servieId;
	
	@Column(name="status", nullable=true)
	private String status;
	
	@CreationTimestamp
    @Column(name = "Created_At", nullable = true)
    private LocalDateTime timestamp;
	
	@UpdateTimestamp
	@Column(name = "Updated_At", nullable = true)
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getPackagecode() {
		return Packagecode;
	}

	public void setPackagecode(String packagecode) {
		Packagecode = packagecode;
	}

	public String getServiename() {
		return serviename;
	}

	public void setServiename(String serviename) {
		this.serviename = serviename;
	}

	public String getServieId() {
		return servieId;
	}

	public void setServieId(String servieId) {
		this.servieId = servieId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}









/*{
    "message": "Fetched Successfully",
    "status": 200,
    "data": {
        "id": 1,
        "packagename": "1",
        "status": "1",
        "timestamp": null,
        "test_table": "1"
    }
}*/