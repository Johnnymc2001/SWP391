
$('#summernote').summernote('code', blogContent);
var backupContent = $('#summernote').summernote('code');
var backupThumbnail = $('#txtImageUrl').val();

var editBtn = document.getElementById("editBtn");
var saveBtn = document.getElementById("saveBtn");
var undoBtn = document.getElementById("undoBtn");
var deleteThumnailBtn = document.getElementById("deleteThumnailBtn");
var undoDeleteBtn = document.getElementById("undoDeleteBtn");
var blogView = document.getElementById("blog-view");
var userWrite = document.getElementById("user-write");
var summerNote = document.getElementById("summernote");
var txtImageUrl = document.getElementById("txtImageUrl");
var imgReview = document.getElementById("imgReview");
var updateBtn = document.getElementById("updateBtn");

function EnableEditAndSave() {
    editBtn.classList.replace("d-inline", "d-none");
    saveBtn.classList.replace("d-none", "d-inline");
    undoBtn.classList.replace("d-none", "d-inline");
    blogView.classList.replace("d-block", "d-none");
    if ($('#txtImageUrl').val() === "") {
        undoDeleteBtn.classList.replace("d-none", "d-inline");
    } else {
        deleteThumnailBtn.classList.replace("d-none", "d-inline");
    }
    userWrite.classList.replace("d-none", "d-block");
}

function SaveEdit() {
    editBtn.classList.replace("d-none", "d-inline");
    saveBtn.classList.replace("d-inline", "d-none");
    undoBtn.classList.replace("d-inline", "d-none");
    deleteThumnailBtn.classList.replace("d-inline", "d-none");
    updateBtn.classList.replace("d-none", "d-block");
    undoDeleteBtn.classList.replace("d-inline", "d-none");
    $('.blog-view').html($('#summernote').summernote('code'));
    userWrite.classList.replace("d-block", "d-none");
    blogView.classList.replace("d-none", "d-block");
}

function Undo() {
    if (confirm('Are you sure you want undo everything?')) {
        editBtn.classList.replace("d-none", "d-inline");
        saveBtn.classList.replace("d-inline", "d-none");
        undoBtn.classList.replace("d-inline", "d-none");
        updateBtn.classList.replace("d-block", "d-none");
        deleteThumnailBtn.classList.replace("d-inline", "d-none");
        undoDeleteBtn.classList.replace("d-inline", "d-none");
        blogView.classList.replace("d-none", "d-block");
        $('.blog-view').html(backupContent);
        $('#summernote').summernote('code', blogView.innerHTML);
        userWrite.classList.replace("d-block", "d-none");
        $('#txtImageUrl').val(backupThumbnail);
        imgReview.classList.replace("d-none", "d-block");
    }
}

function deleteThumbnail() {
    imgReview.classList.replace("d-block", "d-none");
    $('#txtImageUrl').val("");
    deleteThumnailBtn.classList.replace("d-inline", "d-none");
    undoDeleteBtn.classList.replace("d-none", "d-inline");

}

function undoDeleteThumbnail() {
    imgReview.classList.replace("d-none", "d-block");
    $('#txtImageUrl').val(backupThumbnail);
    deleteThumnailBtn.classList.replace("d-none", "d-inline");
    undoDeleteBtn.classList.replace("d-inline", "d-none");
}