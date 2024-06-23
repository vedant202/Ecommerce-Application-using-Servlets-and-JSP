console.log("cartsItems ", cartsItems);
document.getElementById("totalItems").innerText = cartsItems.totalItems;
document.getElementById("totalProducts").innerText = Object.keys(cartsItems?.listItems).length;

let html=``;
html +=Object.keys(cartsItems?.listItems).map((i) => 
    `
        <p>
            ${i} * ${cartsItems?.listItems[i]?.items} :- <span>$. ${cartsItems?.listItems[i]?.price}</span>
        </p>
    `
);

document.getElementById("items").innerHTML = html;
document.getElementById("totalPrice").innerText = cartsItems["Total Price"];
