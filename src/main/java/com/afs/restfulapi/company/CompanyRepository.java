package com.afs.restfulapi.company;

import com.afs.restfulapi.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
//    @Query("select * from employee where companyId = ?1") wrong
//    List<Employee> findEmployeesById(int id);
}
