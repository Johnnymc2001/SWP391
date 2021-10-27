

/* global $$ */

var editBtn = document.getElementById("editBtn");
var saveBtn = document.getElementById("saveBtn");
var undoBtn = document.getElementById("undoBtn");
var updateBtn = document.getElementById("updateBtn");

function EnableEditAndSave() {
    editBtn.classList.replace("d-inline", "d-none");
    saveBtn.classList.replace("d-none", "d-inline");
    undoBtn.classList.replace("d-none", "d-inline");
    $$('.txt-edit').forEach((el) => el.readOnly = false);
    
}
    
function SaveEdit() {
    editBtn.classList.replace("d-none", "d-inline");
    saveBtn.classList.replace("d-inline", "d-none");
    undoBtn.classList.replace("d-inline", "d-none");
    updateBtn.classList.replace("d-none", "d-block");
    $$('.txt-edit').forEach((el) => el.readOnly = true);
}

function Cancel() {
    if (confirm('Are you sure you want cancel?')) {
        editBtn.classList.replace("d-none", "d-inline");
        saveBtn.classList.replace("d-inline", "d-none");
        undoBtn.classList.replace("d-inline", "d-none");
        updateBtn.classList.replace("d-block", "d-none");
        $$('.txt-edit').forEach((el) => el.readOnly = false);
    }
}
