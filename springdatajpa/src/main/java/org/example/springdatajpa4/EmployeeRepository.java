package org.example.springdatajpa4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Example of a custom query
    List<Employee> findByLastName(String lastName);

    List<Employee> findBySalaryGreaterThanEqual(Double salary);

    List<Employee> findBySalaryLessThanOrSalaryGreaterThan(Double salary1, Double salary2);

    List<Employee> findByHireDateBetween(Date startDate, Date endDate);

    List<Employee> findByDepartmentIdIn(List<Integer> departmentIds);

    List<Employee> findByDepartmentIdInAndSalaryBetween(List<Integer> departmentIds, Double minSalary, Double maxSalary);

    List<Employee> findByManagerIdIsNull();

    List<Employee> findByManagerIdIsNotNull();

    List<Employee> findByCommissionPctNotNullOrderBySalaryDescCommissionPctDesc();

    List<Employee> findByLastNameStartingWith(String prefix);

    Optional<Employee> findById(Integer employeeId);
}
