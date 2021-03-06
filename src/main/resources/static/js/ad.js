function addUser(){
    console.log(updateRoles())
    let newUser = {
        name: document.getElementById('name').value,
        lastName: document.getElementById('lastName').value,
        age: document.getElementById('age').value,
        mail: document.getElementById('pass').value,
        roles: updateRoles().length < 2 ? [updateRoles()[0]] : [updateRoles()[0], updateRoles()[1]]
    }
    fetch("http://localhost:8080/api/addUser", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser),
    }).then(()=> {window.location.href = "/admin"})
}

function updateRoles() {
    return $('#select').val();
}