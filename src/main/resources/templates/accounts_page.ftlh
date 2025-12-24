<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accounts</title>
</head>
<body>
<form action="/accounts" method="get">
    <input type="text" name="q" value="${q!}" placeholder="Search...">
    <button type="submit">Search</button>
</form>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>
    <#list accounts as account>
        <tr>
            <td><a href="/accounts/${account.id}">${account.firstName}</a></td>
            <td>${account.lastName}</td>
            <td>${account.email}</td>
            <td>
                <form action="/accounts/${account.id}/delete" method="post" style="display:inline;">
                    <button type="submit">Delete</button>
                </form>
            </td>
            <td>
                <a href="/accounts/${account.id}/edit">Edit</a>
            </td>

        </tr>
    </#list>
</table>
<form action="/accounts" method="post">
    <input name="firstName" type="text" placeholder="Enter firstName...">
    <input name="lastName" type="text" placeholder="Enter lastName...">
    <input name="email" type="email" placeholder="Enter email...">
    <input name="password" type="password" placeholder="Enter password...">
    <input name="age" type="number" placeholder="Enter age...">
    <input type="submit" value="Add user...">
</form>
</body>
</html>