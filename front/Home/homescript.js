async function join() {
  var code = document.getElementById("code").value;
  console.log(code);
  const url = "http://localhost:8080/api/classroom/join/" + code;
  const api = await fetch(url, {
    method: "POST",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("access_token"),
    },
    credentials: "include",
  })
    .then(function (response) {
      if (response.status == 200) {
        alert("Successfull joined the classroom");
        return response.json();
      } else alert("No class found");
    })
    .then((data) => {
      console.log(data);
    });
}
