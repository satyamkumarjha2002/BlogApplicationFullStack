var btn = document.querySelector("#submit");
 btn.addEventListener("click", func);

 let password;
 let userName;

function func(event){
    event.preventDefault();
    userName = document.querySelector("#email").value;
    password = document.querySelector("#password").value;
    registerUser("http://localhost:8080/api/user/login");
}

function registerUser(url) {
    fetch(url,{
        method: 'POST',
        body:
        `{
            "userName":"${userName}",
            "password":"${password}"
        }`
        ,
        headers: {
            "Access-Control-Allow-Origin":"*",
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

function showRes(result){

    if(result.uuid==undefined){
          alert(result.message);
    }else{
        localStorage.setItem("uuid", result.uuid);
        alert("You have loged in");
        window.location.href = "profile.html";
    }
    
}