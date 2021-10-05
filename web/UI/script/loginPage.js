function goToRegister() {
    var loginForm = document.getElementById("login-table");
    var registerForm = document.getElementById("register-table");
    loginForm.classList.remove("activated");
    loginForm.classList.add("deactivated");
    registerForm.classList.remove("deactivated");
    registerForm.classList.add("activated");
}

function goToLogin() {
    var loginForm = document.getElementById("login-table");
    var registerForm = document.getElementById("register-table");
    registerForm.classList.remove("activated");
    registerForm.classList.add("deactivated");
    loginForm.classList.remove("deactivated");
    loginForm.classList.add("activated");
}

function showPassword() {
    var x = document.getElementById("password");
    var eyeC = document.getElementById("close-eye");
    var eyeO = document.getElementById("open-eye");
    eyeC.classList.remove("d-block");
    eyeC.classList.add("d-none");
    eyeO.classList.remove("d-none");
    eyeO.classList.add("d-block");
    x.type = "text";
}

function hidePassword() {
    var x = document.getElementById("password");
    var eyeC = document.getElementById("close-eye");
    var eyeO = document.getElementById("open-eye");
    eyeO.classList.remove("d-block");
    eyeO.classList.add("d-none");
    eyeC.classList.remove("d-none");
    eyeC.classList.add("d-block");
    x.type = "password";
}

function showrePassword() {
    var x = document.getElementById("password-register");
    var eyeC = document.getElementById("reclose-eye");
    var eyeO = document.getElementById("reopen-eye");
    eyeC.classList.remove("d-block");
    eyeC.classList.add("d-none");
    eyeO.classList.remove("d-none");
    eyeO.classList.add("d-block");
    x.type = "text";
}

function hiderePassword() {
    var x = document.getElementById("password-register");
    var eyeC = document.getElementById("reclose-eye");
    var eyeO = document.getElementById("reopen-eye");
    eyeO.classList.remove("d-block");
    eyeO.classList.add("d-none");
    eyeC.classList.remove("d-none");
    eyeC.classList.add("d-block");
    x.type = "password";
}

function showcoPassword() {
    var x = document.getElementById("confirm-password");
    var eyeC = document.getElementById("coclose-eye");
    var eyeO = document.getElementById("coopen-eye");
    eyeC.classList.remove("d-block");
    eyeC.classList.add("d-none");
    eyeO.classList.remove("d-none");
    eyeO.classList.add("d-block");
    x.type = "text";
}

function hidecoPassword() {
    var x = document.getElementById("confirm-password");
    var eyeC = document.getElementById("coclose-eye");
    var eyeO = document.getElementById("coopen-eye");
    eyeO.classList.remove("d-block");
    eyeO.classList.add("d-none");
    eyeC.classList.remove("d-none");
    eyeC.classList.add("d-block");
    x.type = "password";
}