console.log("In index.jsp");
console.log(products);

let html = "";
let beautyInnerHtml = ``;

function cardClickHandler(id) {
  console.log("Card clicked :- ", id);
  window.location.href="http://localhost:8080/FilterTuts/product?id="+id;
}

Object.keys(products).forEach((a, b) => {
  console.log(a);

  products[a].forEach((i, j) => {
    html += `<div onclick="cardClickHandler(${
      i.id
    })" class="card" id="card${j}">
        <div><img style="object-fit: contain" src="${
          i.images[0]
        }" alt="" width=250px height=250px/> </div>
                    <div id="cardHeader">
                        <h3>${i.title ?? i.brand}</h3>
                    </div>
                    <div id="cardBody">
                        <p>${i.title ?? i.brand}</p>
                        <div><h5>$. ${i?.price}</h5></div>
                    </div>
                    <div id="cardFooter">
                        <Button class="cardBtn" id="cartBtn">Add To Cart</Button>
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
