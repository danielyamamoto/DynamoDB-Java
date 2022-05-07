package mx.danielyamamoto.DynamoDB.controllers;

import mx.danielyamamoto.DynamoDB.dto.EmployeeDTO;
import mx.danielyamamoto.DynamoDB.models.Employee;
import mx.danielyamamoto.DynamoDB.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    // Insert a tuple
    @PostMapping("/save")
    public EmployeeDTO save(@RequestBody EmployeeDTO employee) throws Exception {
        employeeService.createEmployee(employee);
        return employee;
    }

    // Select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.listAll());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Iterable<Employee>> getEmployee(@PathVariable("employeeId") Integer agentId) {
        return ResponseEntity.ok(employeeService.findEmployee(agentId));
    }

    // Delete a tuple
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteById(@PathVariable("employeeId") Integer employeeId) {
        employeeService.deleteById(employeeId);
        return new ResponseEntity("Employee deleted", HttpStatus.OK);
    }

    // Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody EmployeeDTO employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }
}
