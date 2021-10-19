function activePage(name) {
    var btn = document.getElementsByClassName("list-group");
    var iframe = document.getElementById("iframe-window");
    for (let i = 0; i < btn.length; i++) {
        if (btn[i].classList.contains("active") && btn[i].textContent != name.textContent) {
            btn[i].classList.remove("active");
        } else if (btn[i].textContent == name.textContent) {
            btn[i].classList.add("active");
            name.classList.add("active");
        }
    }
    if (name.textContent == "Accounts List") {
        iframe.src = "adminAccountList";
        console.log("Iframe src have been changed");
    } else if (name.textContent == "Manage Category") {
        iframe.src = "adminCategoryManage";
        console.log("Iframe src have been changed");
    } else if (name.textContent == "New Account") {
        iframe.src = "adminAccountCreate";
        console.log("Iframe src have been changed");
    }
}