console.log("In index.jsp");
console.log(products);

let html = "";
let beautyInnerHtml = ``;

function cardClickHandler(id) {
  console.log("Card clicked :- ", id);
  window.location.href = "http://localhost:8080/FilterTuts/product?id=" + id;
}

async function handleClick(id) {
  console.log("Card id :- ", id);
  const response = await fetch("http://localhost:8080/FilterTuts/addcart",{
    method:"POST",
    headers:{
      "Content-type":"application/json"
    },
    body:JSON.stringify({"id":id})
  });

  const data = await response.json();
  console.log(data);
  if(data.status==="failure"){
    window.location.replace("http://localhost:8080/FilterTuts/login")
  }else{
		alert(`${data?.title} added successfully`)
	  }
}

Object.keys(products).forEach((a, b) => {
  console.log(a);

  products[a].forEach((i, j) => {
    html += `<div  class="card" id="card${j}">
        <div><img style="object-fit: contain" src="${
          i.images[0]
        }" alt="" width=250px height=250px/> </div>
                    <div style="cursor: pointer;" onclick="cardClickHandler(${
                      i.id
                    })" >
                      <div id="cardHeader">
                          <h3>${i.title ?? i.brand}</h3>
                      </div>
                      <div id="cardBody">
                          <p>${i.title ?? i.brand}</p>
                          <div><h5>$. ${i?.price}</h5></div>
                      </div>
                      </div>
                    <div id="cardFooter">
                        <input type="hidden" value="${i.id}" id="hiddenInput" />
                        <Button class="cardBtn" onClick="handleClick(${i.id})" id="cartBtn">Add To Cart</Button>
                    </div>
                </div>`;
  });
  
  beautyInnerHtml += `
    <h1>${a.toUpperCase()} CATEGEORIES</h1>
            <div id="products">
                <div id="cards">
                        ${html}
                </div>
            </div>`;
  html = ``;
});

document.getElementById("categeories").innerHTML = beautyInnerHtml;
