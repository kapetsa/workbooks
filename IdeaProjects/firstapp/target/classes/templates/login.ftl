<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div align = "center">Login page</div>
<@l.login "/login" />
<a href="/registration">Add new user</a>
</@c.page>