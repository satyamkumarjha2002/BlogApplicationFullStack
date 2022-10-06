let mainHeading;
let subHeading;
let content;
let imgLink;

function createPost() {

    mainHeading = (document.getElementById("Mainheading").value).trim();
    subHeading = (document.getElementById("Subheading").value).trim();
    content = (document.getElementById("post").value).trim();
    imgLink = (document.getElementById("imageLink").value).trim();
    let uuid = localStorage.getItem("uuid");
    console.log(uuid);
    genratePost(`http://localhost:8080/api/post/create/${uuid}`);
}

function genratePost(url) {
    mainHeading = mainHeading.replace(/(\r\n|\n|\r)/gm, "");
    subHeading = subHeading.replace(/(\r\n|\n|\r)/gm, "");
    content = content.replace(/(\r\n|\n|\r)/gm, "");
    imgLink = imgLink.replace(/(\r\n|\n|\r)/gm, "");
    fetch(url, {
        method: 'POST',
        body: `{
            "heading" : "${mainHeading}",
            "subHeading" : "${subHeading}",
            "content" : "${content}",
            "imgLink" : "${imgLink}"
        }`,
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (res) {
        showRes(res);
    })
}

function showRes(result) {

    if (result.message != undefined) {
        alert(result.message);
    }

    console.log(result);
}