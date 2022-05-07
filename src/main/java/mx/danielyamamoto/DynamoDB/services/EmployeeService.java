package mx.danielyamamoto.DynamoDB.services;

import mx.danielyamamoto.DynamoDB.dto.EmployeeDTO;
import mx.danielyamamoto.DynamoDB.models.Employee;
import mx.danielyamamoto.DynamoDB.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    // CREATE
    public void createEmployee(final EmployeeDTO employeeDTO) {
        Employee e = new Employee();
        e.setEmployeeId(employeeDTO.getEmployeeId());
        e.setName(employeeDTO.getName());
        e.setLastname(employeeDTO.getLastname());
        e.setEmail(employeeDTO.getEmail());
        e.setRole(employeeDTO.getRole());
        e.setActive(employeeDTO.getActive());
        employeeRepository.save(e);
    }

    // READ
    public Iterable<Employee> listAll() {
        return employeeRepository.findAll();
    }

    public Iterable<Employee> findEmployee(final Integer employeeId) {
        return employeeRepository.findEmployee(employeeId);
    }

    // DELETE
    public void deleteById(final Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    // UPDATE
    public Employee update(EmployeeDTO employeeDTO) {
        Employee e = new Employee();
        e.setEmployeeId(employeeDTO.getEmployeeId());
        // Data to update
        e.setName(employeeDTO.getName());
        e.setLastname(employeeDTO.getLastname());
        e.setActive(employeeDTO.getActive());
        return employeeRepository.update(e);
    }
}
