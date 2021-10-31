const menubtn = document.querySelector(".navbar-toggler-icon");
const leftnav = document.querySelector(".left-nav");
const mask = document.querySelector(".page-content-mask");
const intro = document.querySelector(".web-intro");
let open = false;
menubtn.addEventListener('click', () => {
    if (!open) {
        menubtn.classList.add('open');
        leftnav.classList.add('open');
        open = true;
    } else {
        menubtn.classList.remove('open');
        leftnav.classList.remove('open');
        open = false;
    }
});

mask.addEventListener('click', () => {
    if (open) {
        menubtn.classList.remove('open');
        leftnav.classList.remove('open');
        open = false;
    }
});

intro.addEventListener('click', () => {
    if (open) {
        menubtn.classList.remove('open');
        leftnav.classList.remove('open');
        open = false;
    }
});