refreshTable();

document.getElementById("addUserButton").onclick = function() {
    window.location.replace("/ui/users/getAddedForm");
}
document.getElementById("refreshTableButton").onclick = refreshTable;

async function refreshTable() {
    let tableBody = document.createElement("tbody");

    let response = await fetch("/users/getAll");
    let users = await response.json();

    for(let i = 0; i < users.length; i++) {
        let row = tableBody.insertRow(i);
        fillRow(users[i], row);
    }

    document.getElementById("usersTableBody").replaceWith(tableBody);
    tableBody.id = "usersTableBody";
}

function fillRow(user, row) {
    row.insertAdjacentHTML(
        "beforeend",
        `
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td><button class="buttonUpdate"><img src="/imgs/pen.svg"/></button></td>
        </tr>
        `
    );

    row.lastElementChild.firstElementChild.onclick = function() {
        window.location.replace("/ui/users/getUpdateForm");
    }
}