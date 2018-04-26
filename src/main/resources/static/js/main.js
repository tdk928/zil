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
            $.get("/allEmployers", response => $("#content").html(response))
        }
    );


    // $("#all-employers").click(
    //     ev => {
    //         ev.preventDefault();
    //         $("#renderCalendar").empty();
    //         $.get("/all-employers", response => $("#content").html(response))
    //     }
    // );

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

    // $("#viewCalendar").click(
    //     ev => {
    //         ev.preventDefault();
    //         $("#renderCalendar").empty();
    //         $.get("/user/calendar", response => $("#content").html(response))
    //         // $.get("/user/calendar", response => console.log(response))
    //     }
    // );
    // $("#calendar").click(
    //     ev => {
    //         ev.preventDefault();
    //         let content = $('#content');
    //         // $.get("/calendar", response => );
    //         $.get('/calendar', response => content.html(response));
    //         $.get("/calendar", function () {
    //             $('#main').append($('<div>wwwwwwwwwww</div>'));
    //         });
    //
    //     }
    // );
});




