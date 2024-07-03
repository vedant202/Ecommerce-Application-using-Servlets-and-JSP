console.log(products)

let html ="";

html+=products.map(i=>`<tr>
                <td>${i?.id}</td>
                <td>${i?.title}</td>
                <td>${i?.brand}</td>
                <td>${i?.category}</td>
                <td>${i?.availabilityStatus}</td>
                <td>${i?.stock}</td>
                <td>${i?.price}</td>
                <td>
                    <a href="http://localhost:8080/FilterTuts/admin/update/product?id=${i?.id}"><button class="actionButton">Update</button></a>
                    <a href="http://localhost:8080/FilterTuts/admin/delete/product?id=${i?.id}"><button class="actionButton">Delete</button></a>
                </td>
            </tr>`
)

document.getElementById("tableBody").innerHTML = html.replaceAll(",","");