loadUser(getUserIdFromUrl()).
    then(response => refreshForm(response)).
    catch(error => alert(error));

document.getElementById("saveUserButton").onclick = function() {
    let form = document.forms.userForm;
    let user = getUserFromForm(form);
    saveUser(user).
        then(response => {
            refreshForm(response);
            alert("User update successfully");
        }).
        catch(error => alert(error));
}

document.getElementById("backButton").onclick = function() {
    window.location.replace("/ui/users/getAll");
}


function getUserIdFromUrl() {
    return new URL(window.location.href).searchParams.get("id");
}

function refreshForm(user) {
    let form = document.forms.userForm;
    form.elements.id.value = user.id;
    form.elements.name.value = user.name;
}

async function loadUser(userId) {
    return fetch(`/users/getById?id=${userId}`).
                then(response => {
                    if(response.ok) return response;
                    else throw new Error("Error");
                }).
                then(response => response.json());
}

async function saveUser(user) {
    return fetch(
        "/users/update",
        {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }
    ).
    then(response => {
        if(response.ok) return response;
        else throw new Error("Error");
    }).
    then(response => response.json());
}

function getUserFromForm(form) {
    let user = {};
    user.id = form.elements.id.value;
    user.name = form.elements.name.value;
    return user;
}