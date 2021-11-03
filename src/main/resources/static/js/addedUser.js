"use strict";

document.getElementById("backButton").onclick = function() {
    window.location.replace("/ui/users/getAll");
}

document.getElementById("saveUserButton").onclick = function() {
    let form = document.forms.userForm;
    let user = getUserFromForm(form);
    saveUser(user).
        then(response => {
            //refreshForm(response);
            //alert("User saved successfully");
            alert(response);
        }).
        catch(error => alert(error));
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
        if(response.ok) return response.json();
        else return response.text();
    });
}

function refreshForm(user) {
    let form = document.forms.userForm;
    form.elements.name.value = user.name;
}

function getUserFromForm(form) {
    let user = {};
    user.name = form.elements.name.value;
    return user;
}