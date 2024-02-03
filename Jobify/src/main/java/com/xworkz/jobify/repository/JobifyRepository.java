package com.xworkz.jobify.repository;



import com.xworkz.jobify.dto.JobifyEntity;

public interface JobifyRepository {

	public boolean save(JobifyEntity entity);
	
//	public List<JobifyEntity> readAll();

	public JobifyEntity findByEmail(String email);
	
	
	
	
	
}
