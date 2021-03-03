
    createTable();
  function createTable() {
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
                object.roles.forEach((value)=> {
                    roles += '<span>';
                    roles += value.role.replaceAll('ROLE_', '');
                    roles += ' ';
                    roles += '</span>';
                })
                const tr = document.createElement('tr');
                tr.innerHTML = '<td>' + object.id + '</td>' +
                    '<td>' + object.name + '</td>' +
                    '<td>' + object.lastName + '</td>' +
                    '<td>' + object.age + '</td>' +
                    '<td>' + object.mail + '</td>' +
                    '<td>' + roles + '</td>' +
                    '<td>' + "<button id=\"editUser\" onclick=\"updateModal("+object.id+")\" type=\"button\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\" data-target=\"#myModal\" >Edit</button>" + '</td>' +
                     '<td>' + "<button type=\"button\" class=\"btn btn-danger btn-lg\" data-toggle=\"modal\">Delete</button>" + '</td>';
                table.appendChild(tr);
            })
        })
    });
}


   function updateModal(id) {
        fetch("http://localhost:8080/api/findUser/" + id, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }). then(result => {
            result.json().then(t => {
                document.getElementById('id').value =  t.id;
                document.getElementById('name').value =  t.name;
                document.getElementById('lastName').value = t.lastName;
                document.getElementById('age').value = t.age;
                document.getElementById('mail').value = t.mail;
            })
        });

    }

  function buttonEdit(){
    let user = {
        id : document.getElementById('id').value,
        name : document.getElementById('name').value,
        lastName : document.getElementById('lastName').value,
        age : document.getElementById('age').value,
        mail : document.getElementById('mail').value,
    }
    fetch("http://localhost:8080/api/editUser", {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    let table = document.getElementById('example')
      $("#myModal .close").click()
     //  while(table.rows.length > 1){
     //      table.deleteRow(1);
     //  }
     //
    // setTimeout(createTable, 300)
};