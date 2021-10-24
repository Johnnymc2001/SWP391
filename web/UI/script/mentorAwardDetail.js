
function ShowCreateAward() {
  var x = document.getElementById("CreateAward");
  if (x.classList.contains("d-none")) {
    x.classList.remove("d-none");

    x.classList.add("d-block");
  } else {
    x.classList.remove("d-block");

    x.classList.add("d-none");
  }
}
