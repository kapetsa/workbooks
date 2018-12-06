<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#include "parts/security.ftl">

<@c.page>
<style>
    body{
        background: linear-gradient(lightseagreen,lightskyblue);
    }
</style>
<div align = "center"><b> Hello, ${name}</b></div>
<div>
    <div align = "right"><@l.logout/></div>
</div>
<div align="center">All Users</div>
<div>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th><a class="shift-icon"></a> </th>
            <th>Username</th>
            <th>Date of registration</th>
            <th>Date of last enter</th>
            <th>Active</th>
            <th>Action</th>
        </tr>

<#list allUsers as user>
<tr>
    <th><input type="checkbox" name="${user.getId()}" ></th>
    <th>${user.username}</th>
    <th>${user.dateOfReg}</th>
    <th>${user.lastEnter}</th>
    <th>${user.active?then("Active", "Blocked")}</th>
    <th><button onclick="window.location.href='/delete/${user.getId()}'">Удалить</button></th>
</tr>
</#list>
    </table>
</div>
</@c.page>