<#import  "parts/common.ftl" as c>
<#import  "parts/login.ftl" as l>


<@c.page>
  <div>
<@l.logout/>
  </div>
  <div>Список конспектов</div>
<#list workbooks as workbook>
     <div>
         <b>${workbook.id}</b>
         <b>${workbook.name}</b>
         <b>${workbook.description}</b>
         <strong>${workbook.authorName}</strong>
     </div>
<#else >
Нет конспектов
</#list>

  <a href="/createWB">Добавить конспект</a>
</@c.page>