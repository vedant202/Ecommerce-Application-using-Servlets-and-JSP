console.log("hii",products);
let str=``;
console.log("vedant 212")

let priceFilter = []
let cate = []
let brandArr = []

let prodsArray = [];
prodsArray = products;


const filterPrice = document.getElementById("priceFilter");
const filterCate = document.getElementById("filterCate");
const filterBrand = document.getElementById("filterBrand");

let filterPriceStr = ``;

//This function setting innerHtml inside cards
function productsSetInnerHtml(products){
	let str=``;
	
	products.forEach((i,j)=>{
		str+=`<div  class="card" id="card${j}">
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
				
	})
	document.getElementById("cards").innerHTML=str;
}


console.log("Max product Price "+maxPriceProduct)
let div = 0;
for(let i=2;i<=maxPriceProduct;i++){
	if(Math.ceil(maxPriceProduct)/i==5){
		div = i;
		break;
	}
}

for (let i=0;i<=maxPriceProduct;i+=div){
	filterPriceStr += `<div>
		<input type="checkbox" value="${i}-${i+div}"> <label>${i} to ${i+div}</label>
	</div>`;
}
filterPrice.innerHTML = filterPriceStr;

let filterCateStr = ``;

categories.forEach(i=>{
	filterCateStr += `<div>
		<input type="checkbox" value="${i}"> <label>${i}</label>
	</div>`;
})
filterCate.innerHTML = filterCateStr;

let filterBrandStr = ``;

brands.forEach(i=>{
	filterBrandStr += `<div>
		<input type="checkbox" value="${i}"> <label>${i}</label>
	</div>`;
})
filterBrand.innerHTML = filterBrandStr;


document.getElementById("priceFilter").addEventListener("change",(e)=>{
	console.log(e.target,e.target.checked)
	let pricestr = "";
	if(e.target.checked){
		priceFilter.push(e.target.value);
		pricestr = priceFilter.join("-");
	}else{
		priceFilter = priceFilter.filter(i=>i!==e.target.value)
		pricestr = priceFilter.join("-");
	}
	fetch("http://localhost:8080/FilterTuts/products?stay=true&&price="+priceFilter.join('-')+"&&categories="+cate.join("-")+"&&brands="+brandArr.join('-'))
	.then(resp=>resp.json()).then(data=>{
		console.log(data);
		prodsArray = data?.products;
	
	productsSetInnerHtml(data?.products);
	})
	console.log(priceFilter)
});

document.getElementById("filterCate").addEventListener("change",(e)=>{
	console.log(e.target,e.target.checked);
	if(e.target.checked){
		cate.push(e.target.value);
	}
	else{
		cate = cate.filter(i=>i!==e.target.value)
	}

	fetch("http://localhost:8080/FilterTuts/products?stay=true&&price="+priceFilter.join('-')+"&&categories="+cate.join("-")+"&&brands="+brandArr.join('-'))
	.then(resp=>resp.json()).then(data=>{
		console.log(data);
		prodsArray = data?.products;
	productsSetInnerHtml(data?.products);

})
})

filterBrand.addEventListener("change",(e)=>{
	console.log(e.target,e.target.checked);
	if(e.target.checked){
		brandArr.push(e.target.value);
	}
	else{
		brandArr = brandArr.filter(i=>i!==e.target.value)
	}

	fetch("http://localhost:8080/FilterTuts/products?stay=true&&price="+priceFilter.join('-')+"&&categories="+cate.join("-")+"&&brands="+brandArr.join('-'))
	.then(resp=>resp.json()).then(data=>{
		console.log(data);
		prodsArray = data?.products;
	productsSetInnerHtml(data?.products);

})
})

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

  let page = 1;

  async function handlePrev(){
	console.log("Prev Btn clicked");
	page = page-1;
	const response=await fetch("http://localhost:8080/FilterTuts/product/page?p="+page);
	const data = await response.json();
	console.log(data);

	if(!data?.prvPageAva){
		document.getElementById("prev").disabled = true;
	}else{
		document.getElementById("prev").disabled = false;
	}
	if(!data?.nextPageAva){
		document.getElementById("next").disabled = true;
	}else{
		document.getElementById("next").disabled = false;
	}

	
	prodsArray = data?.products;
	productsSetInnerHtml(data?.products);

  }

  async function  handleNext(){
	console.log("Next Btn clicked")
	page = page+1;
	const response=await fetch("http://localhost:8080/FilterTuts/product/page?p="+page);
	const data = await response.json();
	console.log(data)
	if(!data?.prvPageAva){
		document.getElementById("prev").disabled = true;
	}else{
		document.getElementById("prev").disabled = false;
	}
	if(!data?.nextPageAva){
		document.getElementById("next").disabled = true;
	}else{
		document.getElementById("next").disabled = false;
	}

	
	prodsArray = data?.products;
	productsSetInnerHtml(data?.products);

  }

  
  productsSetInnerHtml(products);

document.getElementById("sortLabel").addEventListener("mouseover",(e)=>{
	console.log("mouseover")
	document.querySelector(".sortDropDown").classList.add("show");
})
document.querySelector(".sort").addEventListener("mouseleave",(e)=>{
	console.log("mouseleave")
	document.querySelector(".sortDropDown").classList.remove("show");
})
let temp=[]
temp = [...prodsArray];
document.querySelectorAll(".sortValue").forEach(i=>i.addEventListener("click",(e)=>{
	console.log(e.target,e.target.innerText)
	console.log(prodsArray)
	document.getElementById("sortBy").innerText=e.target.innerText

	if(Number.parseInt(e.target.attributes.getNamedItem("list-data").value)===-1){
		console.log("Ascending")
		productsSetInnerHtml(prodsArray.sort((a,b)=>a.price-b.price))
	}else if(Number.parseInt(e.target.attributes.getNamedItem("list-data").value)===1){
		console.log("Descending");
		productsSetInnerHtml(prodsArray.sort((a,b)=>b.price-a.price))


	}else{
		console.log("Normal")
		productsSetInnerHtml(temp)

	}
}))