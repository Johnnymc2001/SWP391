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
function findGetParameter(parameterName) {
    var result = null,
            tmp = [];
    var items = location.search.substr(1).split("&");
    for (var index = 0; index < items.length; index++) {
        tmp = items[index].split("=");
        if (tmp[0] === parameterName)
            result = decodeURIComponent(tmp[1]);
    }
    return result;
}

function changePage(page) {
    let url = "search";

    var searchValue = findGetParameter("txtSearchValue");
    var searchType = findGetParameter("txtSearchType");
    
    if (searchValue !== null) {
        url += `?txtSearchValue=${searchValue}&page=${page}`;
    } else if (searchType !== null) {
        url += `?txtSearchType=${searchType}&page=${page}`;
    } else{
        url += "?page=" + page;
    }

    window.location.href = url;
}