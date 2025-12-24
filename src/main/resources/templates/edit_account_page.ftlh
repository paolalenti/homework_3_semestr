<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit ${account.firstName}</title>
</head>
<body>
<h1>Edit account</h1>

<form action="/accounts/${account.id}/edit" method="post">

    <input name="firstName" type="text" value="${account.firstName}" placeholder="First Name"><br>
    <input name="lastName" type="text" value="${account.lastName}" placeholder="Last Name"><br>
    <input name="email" type="email" value="${account.email}" placeholder="Email"><br>

    <button type="submit">Save changes</button>
</form>

<br>
<a href="/accounts/${account.id}">Back</a>

</body>
</html>
