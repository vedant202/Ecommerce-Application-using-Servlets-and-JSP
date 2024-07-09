console.log("hii",products);
let str=``;
console.log("vedant 212")
// products.forEach((i,j)=>{
// 	str+=`<div  class="card" id="card${j}">
// 	<div><img style="object-fit: contain" src="${i.images[0]}" alt="" width=250px height=250px/> </div>
// 				<div id="cardHeader">
// 					<h3>${i.title ?? i.brand}</h3>
// 				</div>
// 				<div id="cardBody">
// 					<p>${i.title ?? i.brand}</p>
// 					<div><h5>$. ${i?.price}</h5></div>
// 				</div>
// 				<div id="cardFooter">
// 					<Button class="cardBtn" id="cartBtn">Add To Cart</Button>
// 				</div>
// 			</div>`
			
// })
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

	str=""
	data?.products.forEach((i,j)=>{
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

	str=""
	data?.products.forEach((i,j)=>{
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
