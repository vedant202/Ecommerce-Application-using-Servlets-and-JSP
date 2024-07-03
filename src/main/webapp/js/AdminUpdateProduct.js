console.log(product);

document.getElementById("title").value = product?.title;
document.getElementById("description").value = product?.description;
document.getElementById("category").value = product?.category;
document.getElementById("discountPercentage").value = product?.discountPercentage;
document.getElementById("rating").value = product?.rating;
document.getElementById("stock").value = product?.stock;
document.getElementById("tags").value = Array.isArray(product?.tags)?product?.tags.join():product?.tags;
document.getElementById("brand").value = product?.brand;
document.getElementById("sku").value = product?.sku;
document.getElementById("weight").value = product?.weight;
document.getElementById("warrantyInformation").value = product?.warrantyInformation;
document.getElementById("image-url").value = product?.image-url[0];
document.getElementById("shippingInformation").value = product?.shippingInformation;
document.getElementById("availabilityStatus").value = product?.availabilityStatus;
document.getElementById("returnPolicy").value = product?.returnPolicy;
document.getElementById("minimumOrderQuantity").value = product?.minimumOrderQuantity;
document.getElementById("price").value = product?.price;
// document.getElementById("title").value = product?.title;

