
// $('#summernote').summernote('disable');
$('#summernote').summernote('code', blogContent);
//$('#summernote').hide();

backup = $('#summernote').summernote('code');
$('.note-editor').hide();

var backup;
function EnableEditAndSave() {
    $('#summernote').summernote('enable');
    $("#editBtn").hide();
    $("#saveBtn").show();
    $("#undoBtn").show();

    // Show View
    $('#blog-view').css("visibility", "hidden");
    // Show Edtior
    //$('#summernote').show();
    $('.note-editor').show();
    // $('#summernote').summernote('enable');
}

function SaveEdit() {
    $("#editBtn").show();
    $("#saveBtn").hide();
    $("#undoBtn").hide();
    $("#updateBtn").show();

    // Show View
    $('#blog-view').css("visibility", "visible");
    // Hide Editor
    //$('#summernote').hide();
    $('.note-editor').hide();
    // $('#summernote').summernote('disable');

    // Copy Editor Into View
    $('.blog-view').html($('#summernote').summernote('code'));
}

function Undo() {
    if (confirm('Are you sure you want undo everything?')) {
        $('#summernote').summernote('code', backup);
        $("#editBtn").show();
        $("#saveBtn").hide();
        $("#undoBtn").hide();
        $("#updateBtn").hide();
        SaveEdit();
    } else {

    }
}
