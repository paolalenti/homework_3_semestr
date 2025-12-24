<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${account.firstName} ${account.lastName}</title>
</head>
<body>
<h1>Profile</h1>

<p><strong>First Name:</strong> ${account.firstName}</p>
<p><strong>Last Name:</strong> ${account.lastName}</p>
<p><strong>Email:</strong> ${account.email}</p>

<a href="/accounts/${account.id}/edit">Edit</a>

<a href="/accounts">Back to list</a>

</body>
</html>
