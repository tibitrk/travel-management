document.addEventListener("DOMContentLoaded", function() {
    console.log("confirm.js loaded successfully!");


    let form = document.querySelector("form");

    if (form) {
        form.addEventListener("submit", function(event) {
            let password = document.getElementById("password").value;
            let confirmPassword = document.getElementById("ConPassword").value;

            console.log("Password:", password);
            console.log("Confirm Password:", confirmPassword);

            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                event.preventDefault();
            }
        });
    } else {
        console.error("Form not found!");
    }
});
