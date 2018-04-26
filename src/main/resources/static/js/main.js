$(document).ready(() => {

    $("#loginBtn").click(
        ev => {
            ev.preventDefault();
            $("#renderCalendar").empty();
            let content = $("#content").empty();
            $.get("/login", response => content.html(response))
        });

    $("#registerBtn").click(
        ev => {
            ev.preventDefault();
            $("#renderCalendar").empty();
            $.get("/admin/register", response => $("#content").html(response))
        }
    );

    $("#all-employers").click(
        ev => {
            ev.preventDefault();
            $("#renderCalendar").empty();
            $.get("/admin/allEmployers", response => $("#content").html(response))
        }
    );

    $("#logoutBtn").click(
        ev => {
            ev.preventDefault();
            $("#renderCalendar").empty();
            $.get("/logout", response => $("#content").html(response))
        }
    );

    $("#homeBtn").click(
        ev => {
            ev.preventDefault();
            $("#renderCalendar").empty();
            $.get("/home", response => $("#content").html(response))
        }
    );

    $("#zileanBtn").click(
        ev => {
            ev.preventDefault();
            // $("#body").empty();
            // var index = $("#content").empty();
            $.get("/", response => $("#content").html(response))
        }
    );

    $("#changePassword").click(
        ev => {
            ev.preventDefault();
            $("#renderCalendar").empty();
            $.get("/user/changePassword", response => $("#content").html(response))
        }
    );
});




