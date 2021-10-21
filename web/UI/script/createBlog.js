let inputFile = document.getElementById('attachment');
let fileName = document.getElementById('file-name');
inputFile.addEventListener('change', function (event) {
    let uploadedFileName = event.target.files[0].name;
    fileName.textContent = uploadedFileName;
});

attachment.onchange = evt => {
    const [file] = attachment.files;
    if (file) {
        imgReview.src = URL.createObjectURL(file);
    }
};