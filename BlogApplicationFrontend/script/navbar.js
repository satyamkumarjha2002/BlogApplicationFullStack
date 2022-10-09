import {navbar} from "../components/navbar.js";
    document.querySelector("#navbar").innerHTML = navbar();

    let uuid2 = localStorage.getItem("uuid");

    let login = document.getElementById("login");
    let reg = document.getElementById("reg");
    let profile = document.getElementById("profile");

    if (uuid2 == undefined) {
        profile.style.display = "none";
        login.addEventListener("click", function () {
            gotToLoginPage();
        })
    } else {
        profile.style.display = "block";
    }

    if (uuid2 != undefined) {
        login.innerText = "logout";
        reg.style.display = "none";
        login.addEventListener("click", function () {
            logout();
        })
    } else {
        login.innerText = "login";

        reg.style.display = "block";
    }

    console.log(login, reg, profile)

function gotToLoginPage() {

    
    window.location.href = "login.html";
}

function logout(){
    if(uuid2!=undefined||uuid2!=null){
        fetch(`http://localhost:8080/api/user/logout/${uuid2}`,{
            method: 'DELETE',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Content-Type": "application/json"
            }
        })
        localStorage.clear();
        window.location.reload();
    }
}
