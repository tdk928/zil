package org.softuni.nuggets.areas.admin.services;

import org.modelmapper.ModelMapper;
import org.softuni.nuggets.areas.admin.repositories.AdminRepository;
import org.softuni.nuggets.entities.*;
import org.softuni.nuggets.models.binding.AdminEditEmployeeBindingModel;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.softuni.nuggets.repositories.role.RoleRepository;
import org.softuni.nuggets.service.AppointmentService;
import org.softuni.nuggets.service.HolidayService;
import org.softuni.nuggets.service.RoleService;
import org.softuni.nuggets.service.SickService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.softuni.nuggets.areas.contants.Constans.ROLE_ADMIN_ID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final HolidayService holidayService;
    private final SickService sickService;
    private final AppointmentService appointmentService;


    public AdminServiceImpl(AdminRepository adminRepository, BCryptPasswordEncoder encoder,
                            RoleService roleService, SickService sickService,
                            ModelMapper modelMapper, HolidayService holidayService,
                            AppointmentService appointmentService) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
        this.roleService = roleService;

        this.modelMapper = modelMapper;
        this.sickService = sickService;
        this.holidayService = holidayService;
        this.appointmentService = appointmentService;
    }

    @Override
    public List<Employee> getAllEmployers() {
        return this.adminRepository.getAllByDeletedOnIsNull();
    }


    private void fillSickAndHolidayAndAppointmentTable(Employee employee) {
        Holiday holiday = new Holiday();
        holiday.setEmployee(employee);
        this.holidayService.save(holiday);
        Sick sick = new Sick();
        sick.setEmployee(employee);
        this.sickService.save(sick);
        Appointment appointment = new Appointment();
        appointment.setEmployees(employee);
        appointment.setStart(LocalDate.now());
        this.appointmentService.save(appointment);
    }

    private void configureUserDetailsBug(Employee employee) {
        employee.setAccountNonExpired(true);
        employee.setAccountNonLocked(true);
        employee.setCredentialsNonExpired(true);
        employee.setEnabled(true);
    }
    @Override
    public void register(RegisterBindingModel bindingModel) {
        Employee employee = this.modelMapper.map(bindingModel, Employee.class);

        employee.setPassword(this.encoder.encode(bindingModel.getPassword()));

        HashSet<Role> role = new HashSet<>();

        role.add(this.roleService.findById(ROLE_ADMIN_ID));
        employee.setAuthorities(role);

        this.configureUserDetailsBug(employee);

        this.adminRepository.save(employee);
        this.fillSickAndHolidayAndAppointmentTable(employee);
    }

    @Override
    public EmployeeServiceModel getByUsername(String egn) {
        ModelMapper mapper = new ModelMapper();

        Employee employee = this.adminRepository.findFirstByUsernameAndDeletedOnIsNull(egn);

        EmployeeServiceModel result = mapper.map(employee, EmployeeServiceModel.class);
        result.setUsername(employee.getUsername());
        return result;
    }

    @Override
    public void editEmployer(String username, AdminEditEmployeeBindingModel model) {
        ModelMapper modelMapper = new ModelMapper();

        Employee employeeEntity = this.adminRepository
                .findFirstByUsernameAndDeletedOnIsNull(username);

        if(employeeEntity == null) return;
        if (!model.getPassword().trim().equals(employeeEntity.getPassword())) {
            model.setPassword(this.encoder.encode(model.getPassword()));
        }
        modelMapper.map(model,employeeEntity);

        this.configureUserDetailsBug(employeeEntity);
        this.adminRepository.save(employeeEntity);
    }

    @Override
    public void removeEmployer(String username) {
        Employee employer = this.adminRepository.findFirstByUsernameAndDeletedOnIsNull(username);
        if(employer != null) {
            Appointment currentAppointment = this.appointmentService.findFirstByEmployeesId(employer.getId());
            employer.setDeletedOn(LocalDate.now());
            if(currentAppointment != null) {
                currentAppointment.setEnd(LocalDate.now());
                this.appointmentService.save(currentAppointment);
            }

            this.adminRepository.save(employer);
        }
    }

    @Override
    public void save(EmployeeServiceModel model) {
        Employee employee = this.modelMapper.map(model, Employee.class);
        this.adminRepository.save(employee);
    }
}
