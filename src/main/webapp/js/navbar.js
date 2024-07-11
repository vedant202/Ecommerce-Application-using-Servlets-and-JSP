console.log("In navbar.js");

const inputSearch = document.querySelector("[data-input-search]");
console.log(`document.querySelector("[data-user-template]").content`,document.querySelector("[data-user-template]").content)
console.log(`document.querySelector("[data-user-template]").content.cloneNode(true).children`,document.querySelector("[data-user-template]").content.cloneNode(true).children)
const dataUserTemplate = document.querySelector("[data-user-template]").content.cloneNode(true).children[0];
console.log("dataUserTemplate",dataUserTemplate)
const dataList = dataUserTemplate
console.log("dataList",dataList)

console.log("inputSearch",inputSearch)

const inputSearchCont = document.querySelector("[data-input-search-container]");


inputSearch.onkeyup = function () {
    
  let input = inputSearch.value.trim();
  console.log(input);
  if(input.length===0){
    inputSearchCont.innerHTML=""
    
  }
  if (input) {
    if (input.length >= 2) {
      fetch("http://localhost:8080/FilterTuts/s?q=" + input.trim())
        .then((resp) => resp.json())
        .then((data) => {
          console.log(data);
          dataList.innerHTML=""
          data.forEach(element => {
            dataList.innerHTML += `<li><a href="http://localhost:8080/FilterTuts/product?id=${element?.id}">${element?.title}</li>`
          inputSearchCont.append(dataList)
          });
        });
    }
  } else {
    console.log("Search Input is empty");
  }
};
