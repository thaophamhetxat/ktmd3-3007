<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Basic Table</h2>
    <form action="/home?action=createUser" method="post">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Password</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input placeholder="enter id" name="id"></td>
                <td><input placeholder="enter user" name="userName"></td>
                <td><input placeholder="enter password" name="passWord"></td>
                <td><input placeholder="enter email" name="email"></td>
            </tr>

            </tbody>
        </table>
        <button type="submit" class="btn btn-success">Create</button>
        <a href="/home?action=quit" class="btn btn-secondary">Quit</a>

    </form>
</div>

</body>
</html>
