
function ShowCreateAward() {
    var x = document.getElementById("CreateAward");
    if (x.classList.contains("d-none")) {
        x.classList.remove("d-none");
        document.querySelector('#award-btn').textContent = 'Cancel';
    } else {
        x.classList.remove("d-block");
        document.querySelector('#award-btn').textContent = 'Create an Award';
        x.classList.add("d-none");
    }
}
