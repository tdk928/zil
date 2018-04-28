package org.softuni.nuggets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.user.repositories.EmployeeRepository;
import org.softuni.nuggets.areas.user.services.EmployeeService;
import org.softuni.nuggets.areas.user.services.EmployeeServiceImpl;
import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.models.binding.UserEditEmployeeBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;

import static junit.framework.TestCase.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class UserServicesTests {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangePassword() {
        this.testEntityManager.persistAndFlush(this.createEntity("1234512345"));
        UserEditEmployeeBindingModel model = new UserEditEmployeeBindingModel();
        model.setUsername("1234512345");
        model.setPassword("1235");
        EmployeeService employeeService = initializeEmployeeService();
        employeeService.editEmployer("1234512345", model);
        assertEquals("1235",model.getPassword());
    }

    @Test
    public void testSaveEmployer() {
        EmployeeService employeeService = initializeEmployeeService();
        employeeService.save(this.createEntityServiceModel("1234512345"));
        EmployeeServiceModel emp = employeeService.getByUsernameAndDeletedOnIsNotNull("1234512345");
        assertEquals("1234512345",emp.getUsername());
    }

    @Test
    public void testGetByUsernameAndDeletedOnIsNotNull() {
        this.testEntityManager.persistAndFlush(this.createEntity("5555555555"));
        EmployeeService employeeService = initializeEmployeeService();
        EmployeeServiceModel emp = employeeService.getByUsernameAndDeletedOnIsNotNull("5555555555");
        assertEquals("5555555555",emp.getUsername());
    }

    private EmployeeService initializeEmployeeService() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ModelMapper modelMapper = new ModelMapper();
        EmployeeService employeeService = new EmployeeServiceImpl(this.employeeRepository,encoder,modelMapper);
        return employeeService;
    }

    private Employee createEntity(String username) {
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword("123");
        employee.setFirstName("pe6o");
        employee.setLastName("pe6ev");
        employee.setSalary(50);
        return employee;
    }

    private EmployeeServiceModel createEntityServiceModel(String username) {
        EmployeeServiceModel employee = new EmployeeServiceModel();
        employee.setUsername(username);
        employee.setPassword("123");
        employee.setFirstName("pe6o");
        employee.setLastName("pe6ev");
        employee.setSalary(50);
        return employee;
    }
}
