package com.Busybox.rewards.application.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.Table;
@Entity
@Table(name="tjn_Referral_System")
public class TJN_Referral_Model 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Referee_id", nullable  = true)
	private Long Referee_id;	

    @Column(name = "Referee_Name", nullable = true)
    private String Referee_Name;   
      
    @Column(name = "Referee_Email", nullable = true)
    private String Referee_Email; 
    
    @Column(name="referral_by", nullable = true)
    private Long Customer_id;
    
    @Column(name="Referral_Code", nullable = true)
    private String Referral_Code;
    
//    @Column(name="Referral_Link", nullable = true)
//    private String Referral_Link;
    
    @Column(name="Referral_Balance", nullable = true)
    private String Referral_Balance;
//    @Column(name="Referral_Balance")
//    private String Referral_Balance;

	public Long getReferee_id() {
		return Referee_id;
	}

	public void setReferee_id(Long referee_id) {
		Referee_id = referee_id;
	}

	public String getReferee_Name() {
		return Referee_Name;
	}

	public void setReferee_Name(String referee_Name) {
		Referee_Name = referee_Name;
	}

	public String getReferee_Email() {
		return Referee_Email;
	}

	public void setReferee_Email(String referee_Email) {
		Referee_Email = referee_Email;
	}

	public Long getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		Customer_id = customer_id;
	}

	public String getReferral_Code() {
		return Referral_Code;
	}

	public void setReferral_Code(String referral_Code) {
		Referral_Code = referral_Code;
	}

//	public String getReferral_Link() {
//		return Referral_Link;
//	}
//
//	public void setReferral_Link(String referral_Link) {
//		Referral_Link = referral_Link;
//	}

	public String getReferral_Balance() {
		return Referral_Balance;
	}

	public void setReferral_Balance(String referral_Balance) {
		Referral_Balance = referral_Balance;
	}

	public TJN_Referral_Model(Long referee_id, String referee_Name, String referee_Email, Long customer_id,
			String referral_Code, String referral_Link, String referral_Balance) {
		super();
		Referee_id = referee_id;
		Referee_Name = referee_Name;
		Referee_Email = referee_Email;
		Customer_id = customer_id;
		Referral_Code = referral_Code;
//		Referral_Link = referral_Link;
		Referral_Balance = referral_Balance;
	}

	@Override
	public String toString() {
		return "TJN_Referral_Model [Referee_id=" + Referee_id + ", Referee_Name=" + Referee_Name + ", Referee_Email="
				+ Referee_Email + ", Customer_id=" + Customer_id + ", Referral_Code=" + Referral_Code
				+ ",  Referral_Balance=" + Referral_Balance + "]";
	}
//	Referral_Link=" + Referral_Link + "
	public TJN_Referral_Model() {
		super();
		// TODO Auto-generated constructor stub
	}



	

	public void setAmount(Double referralBalance) {
		// TODO Auto-generated method stub
		
	}



	  
}
