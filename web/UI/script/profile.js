

/* global $$, jquery */

var editBtn = document.getElementById("editBtn");
var saveBtn = document.getElementById("saveBtn");
var undoBtn = document.getElementById("undoBtn");
var passTxtBoxs1 = document.getElementById("passwordTextBox1");
var passTxtBoxs2 = document.getElementById("passwordTextBox2");

function EnableEditAndSaveProfile() {
    editBtn.classList.replace("d-inline", "d-none");
    saveBtn.classList.replace("d-none", "d-inline");
    undoBtn.classList.replace("d-none", "d-inline");
    passTxtBoxs1.classList.replace("d-none", "d-inline");
    passTxtBoxs2.classList.replace("d-none", "d-inline");
    
    $(document).ready(function () {
        $('.txt-edit').attr("readonly", false);
    });

}

function SaveEditProfile() {
    editBtn.classList.replace("d-none", "d-inline");
    saveBtn.classList.replace("d-inline", "d-none");
    undoBtn.classList.replace("d-inline", "d-none");
    passTxtBoxs1.classList.replace("d-inline", "d-none");
    passTxtBoxs2.classList.replace("d-inline", "d-none");
    $("#userProfile").submit();
    window.location.href("profilePage");
}

function CancelProfile() {
    editBtn.classList.replace("d-none", "d-inline");
    saveBtn.classList.replace("d-inline", "d-none");
    undoBtn.classList.replace("d-inline", "d-none");
    passTxtBoxs1.classList.replace("d-inline", "d-none");
    passTxtBoxs2.classList.replace("d-inline", "d-none");
    $(document).ready(function () {
        $('.txt-edit').attr("readonly", true);
    });
    window.location.reload();
}
