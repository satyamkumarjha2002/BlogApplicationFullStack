
function getUser(){
    let uuid = localStorage.getItem("uuid");
    if(uuid==undefined){
        alert("Please login first");
    }else{
        registerUser(`http://localhost:8080/api/user/getUser/${uuid}`);
    }
}

function registerUser(url) {
    fetch(url,{
        method: 'GET',
        headers: {
            "Access-Control-Allow-Origin":"*",
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

function displayDetails(result){
    console.log(result);
    localStorage.setItem("email",result.email);
    document.getElementById("name").innerText = result.name;
    document.getElementById("email").innerText = result.email;
    document.getElementById("age").innerText = result.age;
    //document.getElementById("post").innerText = result.posts.length;
    
}

getUser();