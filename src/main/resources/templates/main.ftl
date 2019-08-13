<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <main class="main ml-4 mr-4">

        <div class="form-row ml-2">
            <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Поиск по категории">
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
            </div>
        </div>

        <div class="container ml-2">
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddPurchase" role="button" aria-expanded="false" aria-controls="collapseAddPurchase">
                Добавить новую покупку
            </a>
            <div class="collapse" id="collapseAddPurchase">
                <div class="form-group mt-3">
                    <form method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="text" class="form-control" name="text" placeholder="Введите название товара" />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="cat" placeholder="Выберете категорию товара">
                        </div>
                        <div class="form-group">
                            <input type="description" class="form-control" name="description" placeholder="Опишите покупку">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="cost" placeholder="Стоимость">
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" name="date" placeholder="Дата покупки">
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>

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
                 No purchases
             </#list>
        </table>

        <ul class="list-group">
        </ul>

    </main>



</@c.page>

