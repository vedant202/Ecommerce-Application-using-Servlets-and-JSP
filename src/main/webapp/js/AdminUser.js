function tableInnerHtml(users) {
  let html = "";

  html += users.map(
    (i) => `<tr>
                <td>${i?.id}</td>
                <td>${i?.name}</td>
                <td>${i?.email}</td>
                <td>${i?.address}</td>
                <td>${i?.state}</td>
                <td>${i?.city}</td>
                <td>${i?.phone}</td>
                <td>${i?.role}</td>
                <td>
                    <a href="http://localhost:8080/FilterTuts/admin/update/product?id=${i?.id}"><button class="actionButton">Update</button></a>
                    <a href="http://localhost:8080/FilterTuts/admin/delete/product?id=${i?.id}"><button class="actionButton">Delete</button></a>
                </td>
            </tr>`
  );

  document.getElementById("tableBody").innerHTML = html.replaceAll(",", "");
}

console.log(users);

tableInnerHtml(users);

let search = document.getElementById("search");
search.addEventListener("input", (e) => {
  console.log(e.target.value);
  let u = users.filter(
    (i) =>
      i.name.toLowerCase().includes(e.target.value?.toLowerCase()) ||
      i?.email?.toLowerCase().includes(e.target.value?.toLowerCase())
  );

  tableInnerHtml(u);
});