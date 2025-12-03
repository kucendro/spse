<form action="post">
    <input type="text" name="check">
    <label for="email">Email:
        <input type="email" name="e-mail" id="email" required placeholder="example@email.com" autocomplete="email">
    </label>
    <label for="password">Heslo:
        <input type="password" name="password" id="password" required minlength="8" maxlength="25">
    </label>
    <label for="passwordConf">Heslo Znovu:
        <input type="password" name="passwordConf" id="passwordConf" required minlength="8" maxlength="25">
    </label>
    <input type="submit" value="registration" name="registration">
</form>

<?php
if (isset($_POST['registration'])) {
    if (!empty(($_POST['check']))) {
        http_response_code(400);
        header('Location:404.php');
    }
}