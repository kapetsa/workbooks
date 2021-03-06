<#include "parts/security.ftl">
<#import  "parts/common.ftl" as c>

<#if isAuthor || isAdmin>
<@c.page>
<h5>${name}</h5>
<div>${author}</div>
<div>${spec}</div>
<div>${description}</div>
<div>${body}</div>

<form> <h5>Комментарии</h5>

<#list comments as comment>

    <div class="card my-4" style="width: 60rem;">
        <div class="card-header">
          ${comment.author}
        </div>
        <div class="card-body">
            <p class="card-text">${comment.text}</p>
        </div>

    </div>
<#else >
<div>
    Нет комментариев
</div>
</#list>

    <input type="text" class="form-control"
               value="<#if message??>${message.text}</#if>" name="text" placeholder="Оставить комментарий...">
          <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
          </#if>
    <button type="submit">Добавить</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</@c.page>
</#if>
