package com.Busybox.rewards.application.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface OTPdao extends JpaRepository<OTPmodel, Long>{

	@Query(value="SELECT count(*) FROM otp_table where activity = :inputActivity and session_id = :inputSessionID and otp = :inputOTP ",nativeQuery=true)
	public int checkOtp(@Param("inputActivity") String inputActivity
			,@Param("inputOTP") String inputOTP
			,@Param("inputSessionID") String inputSessionID
			
			);
	
	
	
//	@Query("SELECT COUNT(o) FROM otp_table o WHERE o.sessionId = :inputSessionID AND o.otp = :inputOTP AND o.activity = :inputActivity")
//    int checkOtp(
//        @Param("inputSessionID") String inputSessionID,
//        @Param("inputOTP") String inputOTP,
//        @Param("inputActivity") String inputActivity
//    );
// inputSessionID, inputOTP, inputActivity

}
