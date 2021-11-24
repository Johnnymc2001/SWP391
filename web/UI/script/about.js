
window.addEventListener('load', () => {
    let observerShapeColor = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add("start-animation");
            }
        });
    });

    document.querySelectorAll('.introduce-row').forEach(row => {
        observerShapeColor.observe(row);
    });
});