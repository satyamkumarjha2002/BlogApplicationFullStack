let post = JSON.parse(localStorage.getItem("post"));

let mainHeading = document.getElementById("Mainheading");
let subHeading = document.getElementById("Subheading");
let content = document.getElementById("post");
let imgLink = document.getElementById("imageLink");
let updateButton = document.getElementById("post_button");

mainHeading.value = post.heading;
subHeading.value = post.subHeading;
content.value = post.content;
imgLink.value = post.imgLink;

function updatePost(){
    mainHeading = (document.getElementById("Mainheading").value).trim();
    subHeading = (document.getElementById("Subheading").value).trim();
    content = (document.getElementById("post").value).trim();
    imgLink = (document.getElementById("imageLink").value).trim();
    let uuid = localStorage.getItem("uuid");
    console.log(uuid);
    let url = `http://localhost:8080/api/post/update`;

    mainHeading = mainHeading.replace(/(\r\n|\n|\r)/gm, "");
    subHeading = subHeading.replace(/(\r\n|\n|\r)/gm, "");
    content = content.replace(/(\r\n|\n|\r)/gm, "");
    imgLink = imgLink.replace(/(\r\n|\n|\r)/gm, "");
    fetch(url, {
        method: 'PUT',
        body: `{
            "id" : "${post.id}",
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
    }else{
        alert("post updated");
    }
}
