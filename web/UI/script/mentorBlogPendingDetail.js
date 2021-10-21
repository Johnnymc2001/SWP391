$('#summernote').summernote('code', `${blogContent}`);
$('#summernote').summernote('disable');

var backup;
function EnableEditAndSave() {
    backup = $('#summernote').summernote('code');
    $('#summernote').summernote('enable');
    $("#editBtn").hide();
    $("#saveBtn").show();
    $("#undoBtn").show();
}

function SaveEdit() {
    $('#summernote').summernote('disable');
    $("#editBtn").show();
    $("#saveBtn").hide();
    $("#undoBtn").hide();
    $("#updateBtn").show();
}

function Undo() {
    if (confirm('Are you sure you want undo everything?')) {
        $('#summernote').summernote('code', backup);
        $("#editBtn").show();
        $("#saveBtn").hide();
        $("#undoBtn").hide();
        $('#summernote').summernote('disable');
    } else {

    }
}
