const buttonList = document.querySelectorAll("button");
const iframe = document.querySelector("iframe");
buttonList.forEach((button) => {
    button.addEventListener("click", function () {
        const value = button.value;
        iframe.src = "accountDetail?accountid=" + value;
    });
});

//const PageItemSelect = document.querySelector("#pageItemSelect");
function changeMaxPageItem(selector) {
    let max = selector.value;

    var f = document.createElement('form');
    f.action = 'listAccount';
    f.method = 'POST';

    var i = document.createElement('input');
    i.type = 'hidden';
    i.name = 'maxPageItem';
    i.value = `${max}`;
    f.appendChild(i);

    document.body.appendChild(f);
    f.submit();
//    window.location.href = `listAccount?maxPageItem=${max}`;
}