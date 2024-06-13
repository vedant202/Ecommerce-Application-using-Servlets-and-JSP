console.log("hii",products);
let str=``;
console.log("vedant 21")
products.forEach((i,j)=>{
	str+=`<div class="card" id="card${j}">
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
			
})
document.getElementById("cards").innerHTML=str;
