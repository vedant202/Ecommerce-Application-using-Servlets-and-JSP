<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Product</title>
    
    <style>
        .tableDiv{
            overflow-x: auto;
        }
        .table{
            width: 100%;
            height: 100%;
            border: 1px solid;
            border-collapse: collapse;
            text-align: left;
            background-color: #212529;
            color: white;
            
        }
        

        th, td{
            border-bottom: 1px solid rgb(167, 164, 164);
            vertical-align: middle;
            padding: 13px;
        }
         tr:hover {
            background-color: rgb(53, 52, 52);
        }
        td > a > button{
            width: 80px;
            height: 30px;
            border-radius: 7px;
            background-color: rgb(43, 226, 43);
            color: white;
            font-weight: 500;
            cursor: pointer;

        }
        td > button:hover {
            background-color: green;
        }
    </style>
    
    <script type="text/javascript">
    	var products = <%= request.getAttribute("products") %>;
    	
    </script>
    
</head>
<body>
    <div>
    
    	<button><a href="/FilterTuts/admin/add_product">Add Product</a></button>
    	
    	
    	
    	<div class="tableDiv">
      <table border="0 0 1px solid" class="table">
     
        <thead>
        <tr><th style="text-align: center;" colspan="8"><h1>ALL PRODUCTS</h1></th></tr>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">TITLE</th>
            <th scope="col">BRAND</th>
            <th scope="col">CATEGEORY</th>
            <th>STATUS</th>
            <th>STOCK</th>
            <th>PRICE</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody id="tableBody">
            
        </tbody>
      </table>
    </div>
    </div>
    
    <script src="./js/AdminProduct.js" type="text/javascript"></script>
</body>
</html>
