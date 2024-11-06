main();
function main(){
  putcourse();
  putgrade();
  getAllJoined();
}
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
    output += `<option value="${grade[x]}">${grade[x]}</option>`;
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
    .then(function (response) {
      const holder = document.querySelector(".result-total");
      console.log(response)
      let output = "";
      for (let responses of response) {
        output += `
          <div class="result">
              <div class="subject">
                <h2>${responses.classname}</h2>
                <h4>${responses.classroom_id}</h4>
                <h4>${responses.course}<h4>
              </div>
              <div class="creator">
                <h4 class="noto-sans-elbasan-regular">By ${responses.creator}</h4>
              </div>
            </div>
        `;
      }
      holder.innerHTML = output;
    });
}
const check = true;
function showPop(){
  const pop = document.getElementById('create-pop');
  const Centerpop = document.getElementById('center-pop');
  if(check){
    pop.style.display = "flex";
    Centerpop.style.animation = "appearlogo 0.3s forwards";
  }
}
function cancelPop(){
  const pop = document.getElementById('create-pop');
  if(check){
    
    pop.style.display = "none";
  }
}
async function createRoom() {
  const ClassName = document.getElementById('name-pop').value;
  const Course =  document.getElementById('course-pop');
  const grade = document.getElementById('grade-pop');

  var CourseValue = Course.value;
  var GradeValue = grade.value;

  const url = "http://localhost:8080/api/classroom/create";

  const json = {
    id:1,
    classname:ClassName,
    classroom_id:1,
    course:CourseValue,
    created:"",
    creator:"",
    grade:GradeValue
  }
  const api = await fetch(url,{
    method:'POST',
    headers: {
      Authorization: "Bearer " + localStorage.getItem("access_token"),
      'Content-Type':'application/json'
    },
    body:JSON.stringify(json),
    credentials: "include",
  }).then(function(response){
    if(response.status == 200){
      return response.text();
    }
    else{
      alert("failed to create")
    }
  }).then(data => {
    alert("This is your code. Please remember it and copy it: " + data);
    cancelPop();
  })
}