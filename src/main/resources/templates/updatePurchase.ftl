<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

<form method="post" action="/updatePurchase" enctype="multipart/form-data">
    <div class="form-group">
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
               value="<#if purchase??>${purchase.name}</#if>"
               name="name"
               placeholder="Введите название товара" />
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
    </div>
    <div class="form-group">
        <input type="text"
               class="form-control ${(categoryError??)?string('is-invalid', '')}"
               value="<#if purchase??>${purchase.category}</#if>"
               name="category"
               placeholder="Выберете категорию товара" />
        <#if categoryError??>
            <div class="invalid-feedback">
                ${categoryError}
            </div>
        </#if>
    </div>
    <div class="form-group">
        <input type="description"
               value="<#if purchase??>${purchase.description}</#if>"
               class="form-control" name="description" placeholder="Опишите покупку">
    </div>
    <div class="form-group">
        <input type="text"
               value="<#if purchase??>${purchase.cost}</#if>"
               class="form-control" name="cost" placeholder="Стоимость">
    </div>

    <div class="form-group">
        <input type="date"
<#--              value="${purchase.category}"-->
              value="2019-08-27"
              name="purchaseDate">
        <#if purchaseDateError??>
            <div class="invalid-feedback">
                ${purchaseDateError}
            </div>
        </#if>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="hidden" name="id" value="<#if purchase??> ${purchase.id}</#if>" />

    <div class="form-group">
        <button type="submit" class="btn btn-primary">Сохранить</button>
        <a class="btn btn-secondary"
           href="/main"> Вернутся к спику покупок </a>
    </div>
</form>

</@c.page>