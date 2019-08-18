<#include "security.ftl">

<#--Таблица вывода списка покупок-->
<link rel="stylesheet" type="text/css" href="/static/css/table.css">

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
                    <span class="info-subtitle">Название покупки:</span>
                        <span class="info-body">${purchase.name}</span>
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
                    <span class="info-subtitle">${purchase.cost}</span>
                </p>
                <p class="second-row">
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
                <span> <#if purchase.isBought()> Куплено <#else>Не куплено</#if></span>
            </td>

            <td class="purchase-actions " rowspan="1" scope="col ">
                <div class="d-flex flex-column">
                    <a class="btn btn-secondary"
                       href="/confirm-purchase/${purchase.author.id}?purchase=${purchase.id}"> Отметить как купленную </a>
                    <a class="btn btn-secondary mt-2"
                       href="/user-purchase/${purchase.author.id}?purchase=${purchase.id}"> Редактировать покупку </a>
                    <a class="btn btn-secondary mt-2"
                       href="/delete-purchase/${purchase.author.id}?purchase=${purchase.id}"> Удалить покупку </a>
                </div>
            </td>

        </tr>
        </tbody>
    <#else>
    </#list>
</table>