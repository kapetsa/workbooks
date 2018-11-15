<#import  "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>


<@c.page>
Список пользователей

<table>
    <thead>
       <tr>
           <th>Логин</th>
           <th>Роль</th>
           <th></th>
       </tr>
    </thead>
    <tbody>
<#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">Редактировать</a> </td>
    </tr>

</#list>
    </tbody>
</table>
</@c.page>