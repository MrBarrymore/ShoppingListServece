<#--Таблица вывода списка покупок-->
<link rel="stylesheet" type="text/css" href="/static/css/table.css">

<h3>Список покупок:</h3>

<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Наименование</th>
        <th scope="col">Дополнительно</th>
        <th scope="col">Статус</th>
        <th scope="col">Действие</th>
    </tr>
    </thead>
    <#list purchases as purchase>
        <tbody>
        <tr class="order-head">
            <td class="purchase-info" colspan="2" scope="col">
                <p class="first-row">
                    <span class="info-subtitle">Id покупки:</span> <span class="info-body">${purchase.id}</span>
                </p>
                <p class="second-row" >
                    <span class="info-subtitle"><i>${purchase.purchaseDate}</i></span>
                </p>
            </td>
            <td class="extra-purchase-info" colspan="1" scope="col">
                <p class="first-row">
                    <span class="info-subtitle"></span>
                </p>
                <p class="second-row">
                    <span class="info-subtitle"></span>
                </p>
            </td>
            <td class="" scope="col">
                <p class="first-row" colspan="1">
                    <span class="info-subtitle">Стоимость:</span>
                </p>
                <p class="second-row">
                    <span class="info-subtitle">${purchase.cost}</span>
                </p>
            </td>
        </tr>

        <tr class="purchase-order">
            <td class="product-sets" colspan="1" scope="col">
                <div class="product-left">

                </div>
                <div class="product-right">
                    <span class="info-subtitle">Категория:</span>
                    <p class="purchase-category">${purchase.category}</p>

                </div>
            </td>

            <td class="purchase-action" rowspan="1" scope="col">
                <p class="purchase-description">${purchase.description}</p>
                <p class="purchase-author">${purchase.getAuthorName()}</p>
            </td>
            <td class="purchase-status" rowspan="1" scope="col">
                <span>Не куплено</span>
            </td>
            <td class="purchase-actions" rowspan="1" scope="col">
                <button type="button" class="btn btn-secondary">Подтвердить покупку</button>
                <button type="button" class="btn btn-secondary">Редактировать</button>
            </td>
        </tr>
        </tbody>
    <#else>
    </#list>
</table>