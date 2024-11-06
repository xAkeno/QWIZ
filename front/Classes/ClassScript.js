putcourse();
putgrade();
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

function putcourse() {
  const subject = [
    "Computer Science",
    "Mathematics",
    "English",
    "Science",
    "History",
    "Geography",
    "Physics",
    "Chemistry",
    "Biology",
    "Philosophy",
    "Economics",
    "Psychology",
    "Sociology",
    "Political Science",
    "Art",
    "Music",
    "Physical Education",
    "Business",
    "Others",
  ];
  const holder = document.getElementById("course-pop");
  let output = "";
  for (let x = 0; x < subject.length; x++) {
    output += `<option>${subject[x]}</option>`;
  }
  holder.innerHTML = output;
}
function putgrade() {
  const grade = [
    "Grade 1",
    "Grade 2",
    "Grade 3",
    "Grade 4",
    "Grade 5",
    "Grade 6",
    "Grade 7",
    "Grade 8",
    "Grade 9",
    "Grade 10",
    "Grade 11",
    "Grade 12",
    "College",
  ];
  const holder = document.getElementById("grade-pop");
  let output = "";
  for (let x = 0; x < grade.length; x++) {
    output += `<option>${grade[x]}</option>`;
  }
  holder.innerHTML = output;
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
