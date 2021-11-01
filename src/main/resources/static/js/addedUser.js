document.getElementById("saveUserButton").onclick = function() {
    let form = document.forms.userForm;
    let user = getUserFromForm(form);
    saveUser(user).
        then(response => {
            refreshForm(response);
            alert("User saved successfully");
        }).
        catch(error => alert(error));
}

document.getElementById("backButton").onclick = function() {
    window.location.replace("/ui/users/getAll");
}


function refreshForm(user) {
    let form = document.forms.userForm;
    form.elements.name.value = user.name;
}

async function saveUser(user) {
    return fetch(
        "/users/add",
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
    user.name = form.elements.name.value;
    return user;
}