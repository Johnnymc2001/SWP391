//const button = document.querySelector("#editButton");
//const inputList = document.querySelectorAll("form input");
const form = document.querySelector("form");

const roleSelect = document.querySelector("#roleSelect")
const categorySelect = document.querySelector("#categorySelect")

function changeRole(selector) {
    const value = selector.value;
    if (value === "Mentor") {
        categorySelect.disabled = false;
    } else {
        categorySelect.disabled = true;
    }
}

changeRole(roleSelect);

const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#password');

togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye / eye slash icon
    this.classList.toggle('bi-eye');
});
//const usernameRegex = new RegExp("[a-zA-Z0-9]{6,30}");
//const usernameInput = form.querySelector("#username");
//
//function validateForm() {
//    let error = false;
//
//    let usernameValue = usernameInput.value;
//
//    if (!usernameRegex.test(usernameValue)) {
//        let usernameError = "Username must be from 6 to 20 in length, and only contains characters and numbers";
////        usernameInput.setCustomValidity(usernameError);
////        usernameInput.reportValidity()
//        error = true;
//    } else {
////        usernameInput.setCustomValidity("");
//    }
//
//    console.log(error);
//
//    if (error) {
//        event.preventDefault();
//        return false;
//    } else {
//        return true;
//    }
//}



//function enableInput(input) {
//    input.readOnly = false;
//}
//
//function disableInput(input) {
//    input.readOnly = true;
//}
//
//function toggle(button) {
//    if (button.innerHTML === "Edit") {
//        button.innerHTML = "Done";
//        inputList.forEach(i => {
//            enableInput(i);
//        });
//    } else if (button.innerHTML === "Done") {
//        button.innerHTML = "Edit";
//        inputList.forEach(i => {
//            disableInput(i);
//        });
//    }
//}
//
//inputList.forEach(i => {
//    disableInput(i);
//});