let logedUser;

let userEmail = localStorage.getItem("email");
let uuid = localStorage.getItem("uuid");
let loggedUser = null;

function getAllPost(res) {
    loggedUser = res;
    console.log(loggedUser);
    fetchAllPost(`http://localhost:8080/api/post/getAllPost`);
}

function getLoggedUser(url) {
    fetch(url, {
        method: 'GET',
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (res) {
        getAllPost(res);
    })
}

function fetchAllPost(url) {

    fetch(url, {
        method: 'GET',
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (res) {
        displayDetails(res);
    })
}

function displayDetails(result) {

    console.log(result);
    let mainDiv = document.getElementById("container");
    mainDiv.innerHTML = null;
    result.map(element => {

        let div = document.createElement("div");

        let userDetails = document.createElement("div");
        let userNameDiv = document.createElement("div");
        let userName = document.createElement("h4");
        userNameDiv.append(userName);
        let postDateAndTimeDiv = document.createElement("div");
        let postDateAndTime = document.createElement("p");
        postDateAndTime.innerText = element.dateTime;
        postDateAndTimeDiv.append(postDateAndTime);
        userName.innerText = element.user.name;
        userDetails.append(userNameDiv, postDateAndTimeDiv);

        let contentDiv = document.createElement("div");
        let h1 = document.createElement("h1");
        h1.innerText = element.heading;
        let h3 = document.createElement("h3");
        h3.innerText = element.subHeading;
        let p = document.createElement("p");
        p.innerText = element.content;
        contentDiv.append(h1, h3, p);

        let imgDiv = document.createElement("div");
        let img = document.createElement("img");
        img.src = element.imgLink;
        imgDiv.append(img);

        let responseDiv = document.createElement("div");

        let like = document.createElement("div");
        let likeIc = document.createElement("div");

        likeIc.addEventListener("click", function () {
            increaseLike(`http://localhost:8080/api/like/${element.id}/${userEmail}`);
        })

        likeIc.innerHTML = `<i class="fa-solid fa-heart"></i>`
        likeIc.setAttribute("id", "like");
        let count = document.createElement("h3");
        count.innerText = element.likes.length;

        like.append(likeIc, count);

        let disLike = document.createElement("div");
        let disLikeIc = document.createElement("div");

        disLikeIc.addEventListener("click", function () {
            increseDisLike(`http://localhost:8080/api/dislike/${element.id}/${userEmail}`);
        })

        disLikeIc.innerHTML = `<i class="fa-solid fa-heart-crack"></i>`
        disLikeIc.setAttribute("id", "dislike");
        let dislikeCount = document.createElement("h3");

        dislikeCount.innerText = element.dislikes.length;

        disLike.append(disLikeIc, dislikeCount);

        let comment = document.createElement("div");
        let commntIc = document.createElement("div");

        commntIc.setAttribute("id", "comment");
        commntIc.innerHTML = `<i class="fa-solid fa-comment"></i>`
        let commentCount = document.createElement("h3");
        commentCount.innerText = element.comments.length;
        comment.append(commntIc, commentCount);
        let writeComment = document.createElement("div");
        writeComment.setAttribute("id", "writeComment");
        writeComment.style.display = "none";
        commntIc.addEventListener("click", function () {
            increaseComment(element.id, userEmail, writeComment);
        })
        let likecommentdislike = document.createElement("div");
        likecommentdislike.append(like, disLike, comment);

        div.append(userDetails, contentDiv, imgDiv, likecommentdislike, writeComment);
        mainDiv.append(div);
    });

}

function increseDisLike(url) {
    if (loggedUser.email != null) {
        fetch(url, {
            method: 'POST',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json();
        }).then(function (res) {

        })
        setTimeout(() => {
            getLoggedUser(`http://localhost:8080/api/user/getUser/${uuid}`);
        }, 100);
    } else {
        alert(loggedUser.message); //here exception will occour those exception will store in loggeduser if the user is not loged in and i using exception message for alert;
    }
}

let flag = true;

function increaseComment(postId, userId, commentDivPerPost) {

    let commnetMainDiv = commentDivPerPost;
    commnetMainDiv.innerHTML = null;
    let input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("id", "userInputCommnet");

    let button = document.createElement("button");
    button.setAttribute("id", "commentPost");
    button.innerText = "Post";
    button.addEventListener("click", function () {
        postComment(postId, userId);
    });

    let userInputdiv = document.createElement("div");
    userInputdiv.append(input, button);

    let allComment = document.createElement("div");
    if (flag) {
        commnetMainDiv.style.display = "flex";
        flag = false;
    } else {
        commnetMainDiv.style.display = "none";
        flag = true;
    }

    fetch(`http://localhost:8080/api/comment/getComments/${postId}`, {
        method: 'GET',
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (res) {
        commnetMainDiv.innerHTML = null;
        res.forEach(element => {

            let comment = document.createElement("div");
            let dateAnduser = document.createElement("div");
            let user = document.createElement("p");
            user.innerText = element.user.name;
            let dateandtime = document.createElement("p");
            dateandtime.innerText = element.dateTime;

            let texts = document.createElement("div");
            let ptext = document.createElement("p");
            ptext.innerText = element.text;
            texts.append(ptext);

            dateAnduser.append(user, dateandtime);
            comment.append(dateAnduser, texts);
            allComment.append(comment);

        })
        commnetMainDiv.append(userInputdiv, allComment);
    })

}

function postComment(postId, userId) {

    if (loggedUser.email != null) {
        let text = document.getElementById("userInputCommnet").value;
        fetch(`http://localhost:8080/api/comment/add/${postId}/${userId}`, {
            method: 'POST',
            body: `
        {
          "text":"${text}"
        }
        `,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json();
        }).then(function (res) {
            getLoggedUser(`http://localhost:8080/api/user/getUser/${uuid}`);
        })
    } else {
        alert(loggedUser.message);
    }
}

function increaseLike(url) {
    if (loggedUser.email != null) {
        fetch(url, {
            method: 'POST',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json();
        }).then(function (res) {
            getLoggedUser(`http://localhost:8080/api/user/getUser/${uuid}`);
        })
    
    } else {
        alert(loggedUser.message);
    }
}

getLoggedUser(`http://localhost:8080/api/user/getUser/${uuid}`);