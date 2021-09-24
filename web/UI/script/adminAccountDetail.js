//const button = document.querySelector("#editButton");
//const inputList = document.querySelectorAll("form input");
//const form = document.querySelector("form");

const roleSelect = document.querySelector("#roleSelect")
const categorySelect = document.querySelector("#categorySelect")

function changeRole(selector) {
    const value = selector.value;
    if (value === "Mentor") {
        categorySelect.hidden = false;
    } else {
        categorySelect.hidden = true;
    }
}

changeRole(roleSelect)

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