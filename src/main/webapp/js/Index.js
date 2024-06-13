console.log("In index.jsp");
console.log(products);

let html = "";
let beautyInnerHtml=``;
Object.keys(products).forEach((i,j)=>{
    

	products[i].forEach((i,j)=>{
        html+=`<div class="card" id="card${j}">
        <div><img style="object-fit: contain" src="${i.images[0]}" alt="" width=250px height=250px/> </div>
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
                </div>`
                
    });
    beautyInnerHtml += `
    <h1>${i.toUpperCase()} CATEGEORIES</h1>
            <div id="products">
                <div id="cards">
                        ${html}
                </div>
            </div>`
			
})



document.getElementById('categeories').innerHTML=beautyInnerHtml;
