//const buttonList = document.querySelectorAll("button");
const iframe = document.querySelector("iframe");

//buttonList.forEach((button) => {
//    button.addEventListener("click", function () {
//        const value = button.value;
//        window.location.href = "accountDetail?accountid=" + value;
//    });
//});

//function modifyAccount(id) {
//    window.location.href = "accountDetail?accountid=" + id;
//}
async function deactivateAccount(id) {
    await fetch("accountDetail?accountid=" + id + "&submitAction=Disable");
    window.location.href = "accountList";
}

async function activateAccount(id) {
    await fetch("accountDetail?accountid=" + id + "&submitAction=Enable");
    window.location.href = "accountList";
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
    let url = "accountList";
    let searchValue = findGetParameter("txtSearchValue");
    let accountType = findGetParameter("txtAccountType");

    url += searchValue == null && accountType == null ? "?page=" + page : "?";
    url += searchValue != null ? "txtSearchValue=" + searchValue + "&" : "";
    url += accountType != null ? "txtAccountType=" + accountType + "&" : "";

    if (searchValue != null || accountType != null) {
        url += "page=" + page;
    }

    window.location.href = url;
}
////const PageItemSelect = document.querySelector("#pageItemSelect");
//function changeMaxPageItem(selector) {
//    let max = selector.value;
//
//    var f = document.createElement('form');
//    f.action = 'listAccount';
//    f.method = 'POST';
//
//    var i = document.createElement('input');
//    i.type = 'hidden';
//    i.name = 'maxPageItem';
//    i.value = `${max}`;
//    f.appendChild(i);
//
//    document.body.appendChild(f);
//    f.submit();
////    window.location.href = `listAccount?maxPageItem=${max}`;
//}