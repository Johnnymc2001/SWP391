const buttonList = document.querySelectorAll("button");
const iframe = document.querySelector("iframe");
buttonList.forEach((button) => {
    button.addEventListener("click", function () {
        const value = button.value;
        iframe.src = "accountDetail?accountid=" + value;
    });
});

console.log("Script Loaded!");