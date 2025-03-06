const express = require('express');
const app = express();
const PORT = 3003;



app.use('/bitstream', (req, res) => {
  res.header("Content-Type",'application/json');
  res.sendFile("c:\\users\\pgb15001\\data\\BitcoinBitstream.json");
});

app.use('/bitcoin', (req, res) => {
    res.header("Content-Type",'application/json');
    res.sendFile("c:\\users\\pgb15001\\data\\Bitcoin.json");
  });
  
app.use('/user', (req, res) => {
	res.header("Content-Type",'application/json');
	res.sendFile("c:\\users\\pgb15001\\data\\User.json");
});
  
app.get('/test', (req, res) => res.json({ "answer": 42 }));

app.listen(PORT, () => {
    console.log('Server connected at:', PORT);
});