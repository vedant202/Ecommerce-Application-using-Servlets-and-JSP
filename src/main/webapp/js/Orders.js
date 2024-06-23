console.log("orders", orders);

function removeCart(id){
	console.log("Remove Cart with id :- ",id);

	fetch("http://localhost:8080/FilterTuts/removeCart?id="+id,{
		method:"POST",

	}).then((resp)=>resp.json()).then(data=>{
		console.log(data)
		if(data?.success){
			window.location.reload();
		}
	})
}

function decreaseItem(id){
	console.log("Decrease item with id :- "+id);
	fetch("http://localhost:8080/FilterTuts/cartDecreaseItems?id="+id,{
		method:"POST",

	}).then((resp)=>resp.json()).then(data=>{
		console.log(data)
		console.log(document.getElementById("item"+id));
		document.getElementById(`item$${id}`).innerText=data?.items;
	})
}

function increaseItem(id){
	console.log("Increase item with id :- "+id);
	fetch("http://localhost:8080/FilterTuts/cartIncreaseItems?id="+id,{
		method:"POST",

	}).then((resp)=>resp.json()).then(data=>{
		console.log(data)
		console.log(document.getElementById("item"+id));
		document.getElementById(`item$${id}`).innerText=data?.items;
	})
}

function cardInnerHtml() {
  let html = "";
  if (orders === null || orders.length==0) {
    document.getElementById("container").innerHTML = "<h1>Orders is empty</h1>";
    return;
  }
	    console.log("element",orders);

  html += orders?.map((element) => {
    return `<div class="card">
				<div class="col1"><img style="width: 120px;" src="${element?.products?.images[0]}" alt="" /></div>
				<div class="col2">
					<div class="header">
						<div class="title">${element?.products?.title}</div>
						<div class="brand">${element?.products?.brand}</div>
					</div>
					<div class="main">
						<span>$. ${element?.products?.price}</span>
						<button class="minus" onclick={decreaseItem(${element?.id})}>-</button>
							<span>Qty:<span id="item$${element?.id}">${element?.items}</span></span>
						<button class="plus" onclick={increaseItem(${element?.id})}>+</button>
					</div>
					<div class="footer">
						<button onclick={removeCart(${element?.id})} class="remove">Remove</button>
					</div>
				</div>
			</div>`;
  });

  let parentHtml = `
    <div class="cards">
		
		${html}
		
			

		</div>
`;

  document.getElementById("container").innerHTML = parentHtml;
}

cardInnerHtml()
