

/* global $$, jquery */

var editBtn = document.getElementById("editBtn");
var saveBtn = document.getElementById("saveBtn");
var undoBtn = document.getElementById("undoBtn");

function EnableEditAndSaveProfile() {
    editBtn.classList.replace("d-inline", "d-none");
    saveBtn.classList.replace("d-none", "d-inline");
    undoBtn.classList.replace("d-none", "d-inline");
    $(document).ready(function () {
        $('.txt-edit').attr("readonly", false);
    });

}

function SaveEditProfile() {
    editBtn.classList.replace("d-none", "d-inline");
    saveBtn.classList.replace("d-inline", "d-none");
    undoBtn.classList.replace("d-inline", "d-none");
    $("#userProfile").submit();
}

function CancelProfile() {
    editBtn.classList.replace("d-none", "d-inline");
    saveBtn.classList.replace("d-inline", "d-none");
    undoBtn.classList.replace("d-inline", "d-none");
    $(document).ready(function () {
        $('.txt-edit').attr("readonly", true);
    });
    window.location.reload();
}
