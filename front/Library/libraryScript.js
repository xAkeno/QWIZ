function removeSearchBar() {
  var searchBar = document.getElementsByClassName("center");
  var result = document.getElementsByClassName("result");
  var boxPosition = document.getElementsByClassName("box-center");
  searchBar[0].style.display = "none";
  boxPosition[0].style.justifyContent = "flex-start";
  result[0].style.display = "block";
  result[0].style.animation = "appearlogo 0.2s forwards";
  GetData();
}
function showMore(button) {
  var outWordContainer = button.closest(".out-word");
  var description = outWordContainer.querySelector(".description");
  var seeText = button.querySelector("h4");

  if (description.style.height === "auto" && seeText.innerHTML === "See Less") {
    description.style.height = "10rem";
    outWordContainer.style.height = "10rem";
    description.style.overflow = "hidden";
    seeText.innerHTML = "See More";
  } else {
    description.style.height = "auto";
    outWordContainer.style.height = "auto";
    description.style.overflow = "visible";
    seeText.innerHTML = "See Less";
  }
}

async function GetData() {
  var id = document.getElementById("search").value;
  var title = document.getElementById("titlez");

  title.innerHTML = id;
  const url = "http://localhost:8080/api/v1/search?query=" + id;
  const api = await fetch(url, {
    method: "GET",
  })
    .then(function (Response) {
      if (Response.status == 200) {
        return Response.json();
      } else alert("Failed to load");
    })
    .then((data) => {
      var holder = document.getElementById("result-sum");
      var output = "";
      for (let datas of data) {
        output += `
          <div class="out">
            <div class="out-image">
              <img
                src="${datas.thumbnailLink}"
                height="160px"
                width="110px"
              />
            </div>
            <div class="out-word">
              <div class="out-top">
                <div class="title">
                  <h3>${datas.title}</h3>
                </div>
                <div class="link">
                  <a href="${datas.webReadLink}" target="_blank"
                    ><img src="../image/book.png" height="30px"
                  /></a>
                  <a href="${datas.pdfLink}" target="_blank"
                    ><img src="../image/download-pdf.png" height="30px"
                  /></a>
                </div>
              </div>
              <div class="author">
                <span>${datas.author}</span><br />
                <span>Publisheed date: ${datas.publishedDate}</span><br />
                <span>*****</span>
              </div>
              <div class="description">
                <p style="font-size: 15px" class="text-content">
                  ${datas.description}
                </p>
              </div>
              <div class="more">
                <button onclick="showMore(this)">
                  <h4 id="more-text">See More</h4>
                </button>
              </div>
            </div>
          </div>`;
      }
      holder.innerHTML += output;
    })
    .catch((err) => console.log(err));
}
