<form action="post">
    <input type="text" name="username">
    <input type="submit" value="Submit">
</form>

<?php

var_dump($_POST);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    var_dump($_POST['username']);
}