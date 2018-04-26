package org.softuni.nuggets.areas.contants;

public final class Constans {
    //routes
    public static final String ADMIN_ROUTE = "{admin}/";
    public static final String REGISTER_ROUTE = "/register";
    public static final String EDIT_ROUTE = "/edit/{username}";
    public static final String DELETE_ROUTE = "/delete/{username}";
    public static final String ADD_EVENT = "/addevent";
    public static final String REMOVE_EVENT = "/removeevent";
    public static final String UPDATE_EVENT = "/updateevent";
    public static final String ALL_EVENTS = "/events";
    public static final String ALL_EMPLOYERS = "/admin/allEmployers";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";

    //views
    public static final String ADMIN_REGISTER = "/admin/register";
    public static final String HOME = "home";
    public static final String EDIT = "/admin/edit-employer";
    public static final String ALL_EMPLOYERS_VIEW = "/admin/all-employers";
    public static final String REMOVE_EMPLOYER = "/admin/remove-employer";

    //other
    public static final String USERNAME = "username";
    public static final String EMPLOYER_INPUT = "employerInput";
    public static final String EMPLOYER_INPUT_VALIDATION = "org.springframework.validation.BindingResult.employerInput";
    public static final String JSON = "json";
    public static final String START = "start";
    public static final String END = "end";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String BAD_START_DATE = "bad start date: ";
    public static final String BAD_END_DATE = "bad end date: ";
    public static final long ROLE_USER_ID = 2L;
    public static final long ROLE_ADMIN_ID = 1L;
    private Constans() {
    }
}
