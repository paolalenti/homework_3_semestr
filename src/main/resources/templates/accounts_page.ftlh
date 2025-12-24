<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accounts</title>
</head>
<body>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>
    <#list accounts as account>
        <tr>
            <td>${account.firstName}</td>
            <td>${account.lastName}</td>
            <td>${account.email}</td>
        </tr>
    </#list>
</table>
</body>
</html>