package _02employee.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao {
	
	EmployeeDao findByName(String Name);

}
