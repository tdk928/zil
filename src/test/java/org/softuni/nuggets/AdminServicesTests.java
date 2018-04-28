package org.softuni.nuggets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.admin.repositories.AdminRepository;
import org.softuni.nuggets.areas.admin.services.AdminService;
import org.softuni.nuggets.areas.admin.services.AdminServiceImpl;
import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.models.binding.AdminEditEmployeeBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.softuni.nuggets.repositories.appointment.AppointmentRepository;
import org.softuni.nuggets.repositories.role.RoleRepository;
import org.softuni.nuggets.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class AdminServicesTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSizeOfSkippedMethodElements() {
        this.createSomeEmployers();
        List<Employee> result = this.adminRepository.skipAndGetProportion(5, 2);
        assertEquals("skipped 5 and take 2 elements", result.size(), 2);
    }

    @Test
    public void testSizeOfGetProportionMethodElements() {
        this.createSomeEmployers();
        List<Employee> result = this.adminRepository.getProportion(5);
        assertEquals("skipped 5 and take 2 elements", result.size(), 5);
    }

    @Test
    public void testRegister() {
        Employee entity = this.createEntity("1231231232");
        this.testEntityManager.persistAndFlush(entity);
        Employee firstByUsernameAndDeletedOnIsNull = this.adminRepository.findFirstByUsernameAndDeletedOnIsNull("1231231232");
        assertEquals(entity.getUsername(), firstByUsernameAndDeletedOnIsNull.getUsername());
    }

    @Test
    public void testGetAllEmployers() {

        AdminService adminService = this.initializeAdminService();
        this.testEntityManager.persistAndFlush(this.createEntity("1234567890"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567891"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567892"));
        List<Employee> allEmployers = adminService.getAllEmployers();
        assertEquals(3,allEmployers.size());
    }

    @Test
    public void testGetByUsername() {
        this.testEntityManager.persistAndFlush(this.createEntity("1234567890"));
        AdminService adminService = this.initializeAdminService();
        EmployeeServiceModel byUsername = adminService.getByUsername("1234567890");
        assertEquals("pe6o",byUsername.getFirstName());
    }

    @Test
    public void testRemoveEmployer() {
        this.testEntityManager.persistAndFlush(this.createEntity("1234567890"));
        AdminService adminService = this.initializeAdminService();
        adminService.removeEmployer("1234567890");
        assertEquals(0,adminService.getAllEmployers().size());
    }

    @Test
    public void testEditEmployer() {
        this.testEntityManager.persistAndFlush(this.createEntity("1234567890"));

        AdminService adminService = this.initializeAdminService();

        AdminEditEmployeeBindingModel model = new AdminEditEmployeeBindingModel();
        model.setUsername("1111122222");
        model.setPassword("1234");

        adminService.editEmployer("1234567890",model);
        assertEquals("1111122222",model.getUsername());
    }

    @Test
    public void testEmployerCount() {
        this.testEntityManager.persistAndFlush(this.createEntity("1234567890"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567892"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567893"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567894"));

        AdminService adminService = this.initializeAdminService();

        assertEquals(4,adminService.employersCount());
    }

    @Test
    public void testSaveEmployer() {
        AdminService adminService = this.initializeAdminService();
        EmployeeServiceModel emp = new EmployeeServiceModel();
        emp.setUsername("1234567890");
        emp.setFirstName("pe6o");
        emp.setLastName("pe6ev");
        emp.setSalary(500);
        emp.setPassword("123");
        adminService.save(emp);
        assertEquals(1,adminService.getAllEmployers().size());
    }

    private void createSomeEmployers() {
        this.testEntityManager.persistAndFlush(this.createEntity("1234567891"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567892"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567893"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567894"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567895"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567896"));
        this.testEntityManager.persistAndFlush(this.createEntity("1234567897"));
    }

    private AdminService initializeAdminService() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ModelMapper modelMapper = new ModelMapper();
        AppointmentService appointmentService = new AppointmentServiceImpl(this.appointmentRepository);
        RoleService roleService = new RoleServiceImpl(this.roleRepository);
        AdminService adminService = new AdminServiceImpl(this.adminRepository, encoder, roleService,
                null, modelMapper, null, appointmentService);
        return adminService;
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
}
