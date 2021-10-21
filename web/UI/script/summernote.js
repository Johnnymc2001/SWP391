
$('#summernote').summernote({
    placeholder: "",
    tabsize: 2,
    airMode: false,
    disableDragAndDrop: true,
    height: 400,
    maximumImageFileSize: 1000 * 1024, // 2mb
    callbacks: {
        onImageUpload: function (image) {
            uploadImage(image[0]);
        }

    },
    // toolbar
    toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'italic', 'underline', 'clear']],
        // ['font', ['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
        ['fontname', ['fontname']],
        ['fontsize', ['fontsize']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['height', ['height']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'hr']],
        ['view', ['fullscreen'/*, 'codeview' */]], // remove codeview button 
        ['help', ['help']]
    ],
});

function uploadImage(image) {
    var data = new FormData();
    data.append("image", image);
    $.ajax({
        url: './imageUpload',
        cache: false,
        contentType: false,
        processData: false,
        data: data,
        type: "post",
        success: function (json) {
            if (json.status === "OK") {
                var image = $('<img>').attr('src', json.imageUrl);
                $('#summernote').summernote("insertNode", image[0]);
            } else {
                console.log(json);
            }

        },
        error: function (data) {
            console.log(data);
        }
    });
}

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
    if (confirm('Are you sure you fucked up everything?')) {
        $('#summernote').summernote('code', backup);
        $("#editBtn").show();
        $("#saveBtn").hide();
        $("#undoBtn").hide();
        $('#summernote').summernote('disable');
    } else {

    }
}

