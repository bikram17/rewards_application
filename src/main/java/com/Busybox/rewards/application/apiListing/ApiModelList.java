package com.Busybox.rewards.application.apiListing;

import jakarta.persistence.Column;
// ApiModelList
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "api_model_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ApiModelList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

	
    @Column(name = "sub_model_name")
    private String subModelName;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "description")
    private String desc;

    @Column(name = "api_link")
    private String apiLink;

    @JoinColumn(name = "public_model_id")
    private String publicID;
    
  
}
