package com.jack.project.service;

import com.jack.project.model.Report;
import com.jack.project.model.User;
import com.jack.project.repository.ReportRepository;
import com.jack.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public void save(Report report, String name) {
    	User user = userRepository.findByUsername(name);
    	report.setTitle(report.getTitle());
    	report.setDate(report.getDate());
    	report.setContent(report.getContent());
    	report.setUser(user);
    	report.setUploadFile(report.getUploadFile());
        reportRepository.save(report);
    }

    @Override
    public Report findById(int id) {
        return reportRepository.findById(id);
    }

	@Override
	public void save(Report report) {
		
		report.setTitle(report.getTitle());
		report.setContent(report.getContent());
		report.setDate(report.getDate());
		report.setUploadFile(report.getUploadFile());
		reportRepository.save(report);
	}

}
