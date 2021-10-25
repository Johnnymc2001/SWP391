
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
                var image = $('<img>').attr('src', json.imageUrl).css("width", "50%");
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
