async function joinclass() {
  const code = document.getElementById("code").value;

  let url = "http://localhost:8080/api/classroom/join/" + code;
  const api = await fetch(url, {
    method: "POST",
    credentials: "include",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("access_token"),
    },
  }).then((response) => {
    if (response.status == 200) {
      alert("successfull join the class");
    } else alert("NO class found");
  });
}

async function getAllJoined() {
  let url = "http://localhost:8080/api/classroom/getAllClass";

  const api = await fetch(url, {
    method: "GET",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("access_token"),
    },
    credentials: "include",
  })
    .then(function (response) {
      if (response.status == 200) {
        return response.json();
      }
    })
    .then(function (data) {
      const holder = document.querySelector(".result-total");
      let output = "";
      for (let datas of data) {
        output += `
          <div class="result">
              <div class="subject">
                <h2>English</h2>
                <h4>${datas.classroomId}</h4>
              </div>
              <div class="creator">
                <h4 class="noto-sans-elbasan-regular">By ${datas.created}</h4>
              </div>
            </div>
        `;
      }
      holder.innerHTML = output;
    });
}
getAllJoined();
