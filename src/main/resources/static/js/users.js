refreshTable();

async function refreshTable() {
    let tableBody = document.getElementById("usersTableBody");
    tableBody.innerHtml = "";

    let response = await fetch("/users/getAll");
    let users = await response.json();

    for(let i = 0; i < users.length; i++) {
        let row = tableBody.insertRow(i);
        fillRow(users[i], row);
    }
}

function fillRow(user, row) {
    row.innerHTML =
    `
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
    </tr>
    `;
}