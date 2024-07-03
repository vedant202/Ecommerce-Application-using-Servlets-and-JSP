console.log(products)

function cateClick(){
    
        console.log("cate clicked");
        if(cateList.classList.contains('visible')){
            console.log("visible contains")
            cateList.classList.remove('visible');
        }else{
            console.log("visible not contains")
            cateList.classList.add('visible')
        }
    
}


let cateList = document.getElementById("cateList");
console.log(cateList.getElementsByClassName("anchor")[0])
cateList.getElementsByClassName("anchor")[0].setAttribute("onclick","cateClick()")

// Getting unique categoeries
let uniqueCate = new Set(products.map(i=>i.category));
console.log("uniqueCate",uniqueCate)
let htmlCate="";
uniqueCate.forEach((i)=>{htmlCate+=`<li><input type="checkbox" />${i}</li>`})
document.getElementById('cateItems').innerHTML=htmlCate;

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

let search = document.getElementById("search");
search.addEventListener("input",(e)=>{
    console.log(e.target.value);
    let prods = products.filter(i=>(i.title.toLowerCase().includes(e.target.value?.toLowerCase()) || i?.brand?.toLowerCase().includes(e.target.value?.toLowerCase())))
    let html =""
    html += prods.map(i=>`<tr>
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
    </tr>`)

    document.getElementById("tableBody").innerHTML = html.replaceAll(",","");

})


let headId = document.getElementById("id");
let headTitle = document.getElementById("title")
let headBrand = document.getElementById("brand")
let headCate = document.getElementById("cate")
let headStatus = document.getElementById("status")
let headStock = document.getElementById("stock")
let headPrice = document.getElementById("price")


let sorted=products;
let toggle = false;
headId.addEventListener("click",(e)=>{
    console.log("Id toggle")
    
    let html =""
    if(toggle){
        sorted = sorted.sort((a,b)=>a.id-b.id);
        toggle=false;
    }else{
        sorted = sorted.sort((a,b)=>b.id-a.id);
        toggle=true;
    }
    html += sorted.reverse().map(i=>`<tr>
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
    </tr>`)

    document.getElementById("tableBody").innerHTML = html.replaceAll(",","");
})
toggle=false;
headTitle.addEventListener("click",(e)=>{
    console.log("title toggle")
    if(toggle){
        sorted = sorted.sort((a,b)=>b.title[0].codePointAt()-a.title[0].codePointAt());
        toggle=false;

    }else{
        sorted = sorted.sort((a,b)=>a.title[0].codePointAt()-b.title[0].codePointAt());
        toggle=true;
    }
    
    
    let html =""
    html += sorted.map(i=>`<tr>
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
    </tr>`)
    
    document.getElementById("tableBody").innerHTML = html.replaceAll(",","");
})
toggle=false;

headPrice.addEventListener("click",(e)=>{
    console.log("price toggle")
    
    let html =""
    if(toggle){
        sorted = sorted.sort((a,b)=>a.price-b.price);
        toggle=false;
    }else{
        sorted = sorted.sort((a,b)=>b.price-a.price);
        toggle=true;
    }
    html += sorted.reverse().map(i=>`<tr>
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
    </tr>`)

    document.getElementById("tableBody").innerHTML = html.replaceAll(",","");
})

toggle=false;

headStock.addEventListener("click",(e)=>{
    console.log("stock toggle")
    
    let html =""
    if(toggle){
        sorted = sorted.sort((a,b)=>a.stock-b.stock);
        toggle=false;
    }else{
        sorted = sorted.sort((a,b)=>b.stock-a.stock);
        toggle=true;
    }
    html += sorted.reverse().map(i=>`<tr>
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
    </tr>`)

    document.getElementById("tableBody").innerHTML = html.replaceAll(",","");
})