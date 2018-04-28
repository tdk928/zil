package org.softuni.nuggets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.softuni.nuggets.areas.admin.repositories.AdminRepository;
import org.softuni.nuggets.areas.admin.services.AdminServiceImpl;
import org.softuni.nuggets.areas.admin.services.PasswordEncoder;
import org.softuni.nuggets.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class AdminRepositoriesTests {

    private static final String USERNAME = "1234567890";

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Autowired
    private TestEntityManager testEntityManager;




    @Test
    public void testRegisterAndCheckUsername() {
        when(this.adminRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        Employee reg = new Employee();
        reg.setUsername("1234567890");
        reg.setPassword("123");
        reg.setFirstName("pe6o");
        reg.setLastName("pe6ev");
        reg.setSalary(50);

        Employee registered = this.adminService.register(reg);


        assertEquals(
                "Password was not or wrongly encoded!",
                registered.getUsername(),"1234567890"
        );
    }

}
