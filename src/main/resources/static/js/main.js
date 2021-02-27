// let json = fetch()
//     .then(resp => resp.json())



fetch("http://localhost:8080/api/users", {
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }}).then(res => {
    res.json().then(t => {
        console.log(t);
        let table = document.getElementById('example');
    t.forEach((object) => {
        let roles = '';
        object.roles.forEach((value)=> {
            roles += '<span>';
            roles += value.role.replaceAll('ROLE_', '');
            roles += ' ';
            roles += '</span>';
        })
        let tr = document.createElement('tr');
        tr.innerHTML = '<td>' + object.id + '</td>' +
            '<td>' + object.name + '</td>' +
            '<td>' + object.lastName + '</td>' +
            '<td>' + object.age + '</td>' +
            '<td>' + object.mail + '</td>' +
            '<td>' + roles + '</td>';


        table.appendChild(tr);
    })
    })
});


// const res = fetch('http://localhost:8080/api/users');

// const data = JSON.parse(res);
// function append_json_data() {
//     let table = document.getElementById('example');
//     promise.forEach((object) => {
//         let tr = document.createElement('tr');
//         tr.innerHTML = '<td>' + object.id + '</td>' +
//             '<td>' + object.name + '</td>' +
//             '<td>' + object.lastName + '</td>' +
//             '<td>' + object.age + '</td>';
//         '<td>' + object.mail + '</td>';
//         table.appendChild(tr);
//     });


