package org.softuni.nuggets.areas.admin.services;

import org.softuni.nuggets.entities.Employee;
import org.softuni.nuggets.models.binding.AdminEditEmployeeBindingModel;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.softuni.nuggets.models.service.EmployeeServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminService {
    List<Employee> getAllEmployers();

    void register(RegisterBindingModel bindingModel);

    EmployeeServiceModel getByUsername(String egn);

    void editEmployer(String username, AdminEditEmployeeBindingModel editEmployeeBindingModel);

    void removeEmployer(String username);

    void save(EmployeeServiceModel employee);

    void registerAdmin(Employee employee);

    int employersCount();

    List<Employee> getProportion(int num);

    List<Employee> skipAndGetProportion(int skip, int take);
}
