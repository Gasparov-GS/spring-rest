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
        let idForModal = "";
        idForModal++;
        let roles = '';
        let buttonEdit = "<button id=\"editUser\" onclick=\"updateUser("+object.id+")\" type=\"button\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#myModal\"" +
            " >Edit</button>";
        let buttonDelete = "<button type=\"button\" class=\"btn btn-danger btn-lg\" data-toggle=\"modal\"" +
            " onclick='del'>Edit</button>"

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
            '<td>' + roles + '</td>' +
            '<td>' + buttonEdit + '</td>' +
            '<td>' + buttonDelete + '</td>';
        table.appendChild(tr);




    })
    })
});

    function updateUser(id) {

        document.getElementById('id').value = id;


        // let form = document.getElementById('formEdit')
        //
        //
        // let label = document.createElement('label');
        // label.innerHTML = '<label for="id">' + "ID" + '</label>';
        //
        // let input = document.createElement('input');
        // input.innerHTML = '<input class="form-control card-form" type="text" name="id" th:value=""  id="id" readonly>'
        //
        //
        // form.appendChild(label);
        // form.appendChild(input);
    }



