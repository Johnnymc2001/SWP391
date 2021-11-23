function goTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

async function rate(blogid, rate) {
    var data = "";
    await fetch(`rate?txtBlogID=${blogid}&txtRate=${rate}`).then(resp => {
        data = resp;
    });

    var json = await data.json();
    document.querySelector(".blog-vote .user-vote div span").innerText = json.rating;
}