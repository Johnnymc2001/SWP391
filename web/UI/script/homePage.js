function popular() {
    var element = document.getElementById("news");
    element.classList.remove("recent-activated");
    element.classList.add("popular-activated");
}

function recent() {
    var element = document.getElementById("news");
    element.classList.remove("popular-activated");
    element.classList.add("recent-activated");
}

function goTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

