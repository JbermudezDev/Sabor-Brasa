function filtrarProductos() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchBar");
    filter = input.value.toLowerCase();
    table = document.querySelector("table");
    tr = table.getElementsByTagName("tr");

    for (i = 1; i < tr.length; i++) { // Empieza en 1 para evitar el encabezado
        td = tr[i].getElementsByTagName("td")[1]; // Columna del nombre del producto
        if (td) {
            txtValue = td.textContent || td.innerText;
            tr[i].style.display = txtValue.toLowerCase().includes(filter) ? "" : "none";
        }
    }
}
function buscarProductos() {
    let input = document.getElementById("search-input").value.toLowerCase();
    let filas = document.querySelectorAll("tbody tr");

    filas.forEach(fila => {
        let textoFila = fila.textContent.toLowerCase();
        fila.style.display = textoFila.includes(input) ? "" : "none";
    });
}
