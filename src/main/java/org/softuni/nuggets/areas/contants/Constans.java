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
    public static final String CHANGE_PASSWORD = "/user/changePassword";
    public static final String CALENDAR = "/user/calendar";
    public static final String INDEX = "/";
    public static final String HOME = "/home";

    //views
    public static final String ADMIN_REGISTER = "/admin/register";
    public static final String HOME_VIEW = "/home";
    public static final String EDIT_VIEW = "/admin/edit-employer";
    public static final String ALL_EMPLOYERS_VIEW = "/admin/all-employers";
    public static final String REMOVE_EMPLOYER = "/admin/remove-employer";
    public static final String LOGIN_ROUTE = "/user/login";
    public static final String CHANGE_PASSWORD_VIEW = "/user/change-password";
    public static final String CALENDAR_VIEW = "/user/jsoncalendar";
    public static final String INDEX_VIEW = "/index";

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
    public static final long ROLE_USER_ID = 1L;
    public static final long ROLE_ADMIN_ID = 2L;
    public static final String ERROR = "error";
    public static final String USERNAME_EXCEPTION = "Username not found.";
    public static final String REDIRECT = "redirect:";
    public static final String UUID_GENERATOR = "org.hibernate.id.UUIDGenerator";
    public static final String UUID = "UUID";

    //table names
    public static final String APPOINTMENTS_TABLE =  "appointments";
    public static final String EVENT_TABLE = "Event";
    public static final String EMPLOYERS_TABLE = "employers";
    public static final String HOLIDAYS_TABLE = "holiday_days";
    public static final String ROLE_TABLE = "roles";
    public static final String SICK_TABLE = "sick_days";
    public static final String NEEDED_EMPLOYERS = "needed_employers";
    public static final String TEAMS = "teams";


    //redirect
//    public static final String SICK_TABLE = "admin/all-employers"

    private Constans() {
    }
}
