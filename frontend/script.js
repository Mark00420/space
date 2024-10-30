document.getElementById('nameForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('name').value;
  
    fetch('/api/names', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ name: name }),
    })
    .then(response => response.json())
    .then(data => {
      alert('Name submitted: ' + data.name);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  });
  