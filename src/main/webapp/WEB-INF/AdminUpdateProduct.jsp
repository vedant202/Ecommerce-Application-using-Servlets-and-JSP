<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }
        .form-container h2 {
            margin-bottom: 20px;
            font-size: 24px; 
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input,
        .form-group textarea,
        .form-group select {
            width: 95%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        .form-group input[type="submit"] {
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 18px;
        }
        .form-group input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
<script >
	var product = <%= request.getAttribute("product") %>;
	console.log(product);

</script>

</head>

<body>
	<div class="form-container">
        <h2>Update Product</h2>
        
        <form action="/FilterTuts/admin/update/product" method="post">
        <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" autofocus="autofocus">
                
            </div>
            <div class="form-group">
                <label for="availabilityStatus">Availability Status</label>
                <input type="text" id="availabilityStatus" name="availabilityStatus">
            </div>
            <div class="form-group">
                <label for="brand">Brand</label>
                <input type="text" id="brand" name="brand">
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <input type="text" id="category" name="category">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" rows="3"></textarea>
            </div>
            <div class="form-group">
                <label for="discountPercentage">Discount Percentage</label>
                <input type="number" id="discountPercentage" name="discountPercentage" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="minimumOrderQuantity">Minimum Order Quantity</label>
                <input type="text" id="minimumOrderQuantity" name="minimumOrderQuantity">
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" id="price" name="price" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="rating">Rating</label>
                <input type="number" id="rating" name="rating" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="returnPolicy">Return Policy</label>
                <input type="text" id="returnPolicy" name="returnPolicy">
            </div>
            <div class="form-group">
                <label for="shippingInformation">Shipping Information</label>
                <input type="text" id="shippingInformation" name="shippingInformation">
            </div>
            <div class="form-group">
                <label for="sku">SKU</label>
                <input type="text" id="sku" name="sku">
            </div>
            <div class="form-group">
                <label for="stock">Stock</label>
                <input type="number" id="stock" name="stock" required>
            </div>
            <div class="form-group">
                <label for="tags">Tags</label>
                <input type="text" id="tags" name="tags">
            </div>
            
            <div class="form-group">
                <label for="warrantyInformation">Warranty Information</label>
                <input type="text" id="warrantyInformation" name="warrantyInformation">
            </div>
            <div class="form-group">
                <label for="weight">Weight</label>
                <input type="text" id="weight" name="weight">
            </div>
            <div class="form-group">
                <label for="image-url">Image Url</label>
                <input type="text" id="image-url" name="image-url">
                <input type="hidden" id="id" name="id" value=<%= request.getParameter("id") %>>
            </div>
            <div class="form-group">
                <input type="submit" value="UPDATE Product">
            </div>
        </form>
    </div>
    
    <script>

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
	document.getElementById("image-url").value = product?.images[0];
	document.getElementById("shippingInformation").value = product?.shippingInformation;
	document.getElementById("availabilityStatus").value = product?.availabilityStatus;
	document.getElementById("returnPolicy").value = product?.returnPolicy;
	document.getElementById("minimumOrderQuantity").value = product?.minimumOrderQuantity;
	document.getElementById("price").value = product?.price;
    </script>
    
<!-- 	        <script src="./js/AdminUpdateProduct.js" type="text/javascript"></script>
 -->	
</body>
</html>