<#import  "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout/>
</div>
<div>
    <form method="post">
        <input type="text" name="name" placeholder="Название конспекта" />
        <input type="text" name="spec" placeholder="Специальность">
        <input type="text" name="description" placeholder="Описание">
        <input type="text" name="tag" placeholder="Тэг">
        <input type="text" name="body" placeholder="Tекст">
        <button type="submit">Добавить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>

</@c.page>