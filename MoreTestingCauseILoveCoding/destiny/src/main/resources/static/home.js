const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'Password',
  database: 'destinyapi'
});

connection.connect();

connection.query('SELECT token FROM access_token', function (error, results, fields) {
  if (error) throw error;
  const accessToken = results[0].token;
  // use the accessToken in your API requests
  const headers = new Headers({
    'Authorization': 'Bearer ' + accessToken,
    'Content-Type': 'application/x-www-form-urlencoded'
  });
  
  const requestOptions = {
    method: 'GET',
    headers: headers,
  };
  
  fetch('https://www.bungie.net/Platform/User/GetMembershipsForCurrentUser/', requestOptions)
    .then(response => response.json())
    .then(data => {
      console.log(data);
    })
    .catch(error => console.error(error));
});

connection.end();