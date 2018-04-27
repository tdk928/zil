package org.softuni.nuggets.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterBindingModel {
    private static final String EGN_NAME_CANNOT_BE_EMPTY = "EGN name cannot be empty.";
    private static final String YOUR_EGN_MUST_BE_EXACTLY_10_CHARACTERS = "Your EGN must be exactly 10 characters.";
    private static final String PASSWORD_MUST_BE_BETWEEN_3_AND_10_SYMBOLS = "Password must be between 3 and 10 symbols.";
    private static final String PASSWORD_MUST_BE_BETWEEN_3_AND_10_SYMBOLS1 = "Password must be between 3 and 10 symbols.";
    private static final String EGN_NAME_CANNOT_BE_EMPTY1 = "EGN name cannot be empty.";
    private static final String YOUR_FIRST_NAME_MUST_BE_MAXIMUM_20_CHARACTERS = "Your first name must be maximum 20 characters.";
    private static final String EGN_NAME_CANNOT_BE_EMPTY2 = "EGN name cannot be empty.";
    private static final String YOUR_LAST_NAME_MUST_BE_MAXIMUM_20_CHARACTERS = "Your last name must be maximum 20 characters.";
    @NotEmpty(message = EGN_NAME_CANNOT_BE_EMPTY)
    @Size(min=10, max=10, message = YOUR_EGN_MUST_BE_EXACTLY_10_CHARACTERS)
    private String username;

    @NotEmpty
    @Size(min=3, max=10, message = PASSWORD_MUST_BE_BETWEEN_3_AND_10_SYMBOLS)
    private String password;

    @NotEmpty
    @Size(min=3, max=10, message = PASSWORD_MUST_BE_BETWEEN_3_AND_10_SYMBOLS1)
    private String confirmPassword;

    @NotEmpty(message = EGN_NAME_CANNOT_BE_EMPTY1)
    @Size(max=20, message = YOUR_FIRST_NAME_MUST_BE_MAXIMUM_20_CHARACTERS)
    private String firstName;

    @NotEmpty(message = EGN_NAME_CANNOT_BE_EMPTY2)
    @Size(max=20, message = YOUR_LAST_NAME_MUST_BE_MAXIMUM_20_CHARACTERS)
    private String lastName;

    private double salary;

    public RegisterBindingModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
