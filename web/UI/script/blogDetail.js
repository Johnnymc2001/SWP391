function goTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

async function rate(blogid, rate) {
    await fetch(`rate?txtBlogID=${blogid}&txtRate=${rate}`);
}