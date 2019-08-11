<#import "parts/common.ftl" as c>

<@c.page>

    <main class="main ml-4 mr-4">
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Sign Out"/>
        </form>

        <form method="post">
            <div>
                <label for="inputPurchase"> Введите название товара </label>
                <input type="text" name="text", placeholder="Введите название товара" />
            </div>
            <input type="text" name="cat", placeholder="Выберете категорию товара" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>

        <div>Список покупок</div>
        <form method="post" action="filter">
            <input type="text" name="filter">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Найти</button>
        </form>

        <div class="container">
            <div><h3>Список покупок:</h3></div>
            <#list purchases as purchase>
                <div>
                    <b>${purchase.id}</b>
                    <span>${purchase.name}</span>
                    <i>${purchase.category}</i>
                    <i>${purchase.purchaseDate}</i>
                </div>
            <#else>
                No purchases
            </#list>
        </div>
    </main>
</@c.page>