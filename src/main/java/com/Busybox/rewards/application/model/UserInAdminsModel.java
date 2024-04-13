//package com.Busybox.rewards.application.model;
//
//import java.util.Date;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//@Entity
//@Table(name="tjn_user_roles")
//public class UserInAdminsModel {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JsonIgnore
//	private Long id;
//	@JsonIgnore
//	private String userPermissionId;
//    private String userName;
//    private String role;
//    private String permission;
//    private String userEmail;
//    private String storeId;
//    private String Status;
//    private String isBlocked;
//    private String storeName;    
//    @JsonIgnore
//    private String password;
//    @JsonIgnore
//    @CreationTimestamp
//    private Date createdAt;
//    @JsonIgnore
//    @UpdateTimestamp
//    private Date updatedAt;
//
//}
