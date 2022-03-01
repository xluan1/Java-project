package net.longvan.training.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.longvan.training.spring.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
