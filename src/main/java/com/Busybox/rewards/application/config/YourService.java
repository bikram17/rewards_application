package com.Busybox.rewards.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.Busybox.rewards.application.dao.ApiLogRepository;
import com.Busybox.rewards.application.model.Rewards_Application_API_Logs;
@CrossOrigin("*")
@Service
public class YourService {

	@Autowired ApiLogRepository apiLogsRepository;
	
    @Transactional
    public void saveLogEntry(Rewards_Application_API_Logs logEntry) {
        apiLogsRepository.save(logEntry);
    }
}