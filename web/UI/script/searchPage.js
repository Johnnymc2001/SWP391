window.addEventListener("scroll", function () {
    const scrolled = window.scrollY;
    var element = document.getElementById("search-bar");
    if (scrolled >= 100) {
        element.classList.add("fixed");
    } else if (scrolled < 100) {
        element.classList.remove("fixed");
    }
    // console.log(scrolled);
})

function goTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}
