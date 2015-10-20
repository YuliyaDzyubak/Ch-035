package com.crsms.service;

import java.util.Set;

import com.crsms.domain.Test;

/**
 * @author Andriets Petro
 * 
 */

public interface TestService {
	
	public void createTest(Test test);
	
	public Test getTestById(Long id);
	
	public Set<Test> getAllTest();

	public void updateTest(Test test);
	
	public void deleteTest(Test test);
	
	public void delteTestById(Long id);
	
}
