function create() {
  const right = document.getElementsByClassName("right-sector")[0];
  const left = document.getElementsByClassName("left-sector")[0];
  const register = document.getElementsByClassName("register")[0];
  const log = document.getElementsByClassName("log")[0];

  //border-top-left-radius
  //transition: font-size 0.2s ease-in-out
  right.style.transform = "translateX(-531px)";
  right.style.borderRadius = "0px";
  right.style.borderTopLeftRadius = "15px";
  right.style.borderBottomLeftRadius = "15px";
  right.style.transition = "transform 0.5s ease-in-out";
  log.style.display = "none";

  left.style.transform = "translateX(531px)";
  left.style.borderRadius = "0px";
  left.style.borderTopRightRadius = "15px";
  left.style.borderBottomRightRadius = "15px";
  left.style.transition = "transform 0.5s ease-in-out";

  register.style.display = "block";
}
function Tolog() {
  const right = document.getElementsByClassName("right-sector")[0];
  const left = document.getElementsByClassName("left-sector")[0];
  const register = document.getElementsByClassName("register")[0];
  const log = document.getElementsByClassName("log")[0];

  //border-top-left-radius
  //transition: font-size 0.2s ease-in-out
  right.style.transform = "translateX(0px)";
  right.style.borderRadius = "0px";
  right.style.borderTopRightRadius = "15px";
  right.style.borderBottomRightRadius = "15px";
  right.style.transition = "transform 0.5s ease-in-out";
  log.style.display = "block";

  left.style.transform = "translateX(0px)";
  left.style.borderRadius = "0px";
  left.style.borderTopLeftRadius = "15px";
  left.style.borderBottomLeftRadius = "15px";
  left.style.transition = "transform 0.5s ease-in-out";

  register.style.display = "none";
}
async function login() {
  const username = document.getElementById("usernamelog").value;
  const password = document.getElementById("passwordlog").value;

  const json = {
    username: username,
    password: password,
  };
  const api = await fetch("http://localhost:8080/api/auth/log", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(json),
  })
    .then((response) => response.json())
    .then(function (data) {
      if (data != null) {
        alert("successfully login");
        localStorage.setItem("access_token", data.access_token);
        window.location = "../Home/home.html";
      }
    });
}
