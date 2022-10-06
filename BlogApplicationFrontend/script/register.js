
var btn = document.querySelector("#submit");
 btn.addEventListener("click", func);

 let names;
 let age;
 let password;
 let email;


function func(event){
    event.preventDefault();
    names = document.querySelector("#name").value;
    age = document.querySelector("#age").value;
    email = document.querySelector("#email").value;
    password = document.querySelector("#password").value;
    registerUser("http://localhost:8080/api/user/register");
}

function registerUser(url) {
    console.log(password);
    fetch(url,{
        method: 'POST',
        body:
        `{
            "name":"${names}",
            "age":${age},
            "email":"${email}",
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
    console.log(result);
    alert("Resgistered suscessfully please login!")
    window.location.href = "login.html";
}