<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <main class="main ml-4 mr-4">

            <div class="d-flex flex-column bd-highlight mb-3">
                <div class="p-2 border-secondary">
                     <label><h4>Фильтр списка покупок</h4></label>
                </div>
                <div class="p-2 border-secondary">
                    <div class="form-group col-md-6 col-mt-6">
                        <form method="get" action="/main" class="form-inline">
                            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Поиск по категории">
                            <button type="submit" class="btn btn-primary ml-2">Найти</button>
                        </form>
                    </div>
                </div>
                <div class="p-2 bd-dark">
                    <div class="form-group col-md-6 col-mt-6">
                        <form method="get" action="/main" class="form-inline">
                            <input type="hidden" name="relevant" name="" value=true>
                            <button type="submit" class="btn btn-primary ml-2">Актуальные покупки</button>
                        </form>
                    </div>
                </div>
            </div>

        <div class="container ml-2">
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddPurchase" role="button" aria-expanded="false" aria-controls="collapseAddPurchase">
                Добавить новую покупку
            </a>
            <div class="collapse<#if purchase??>-show</#if>" id="collapseAddPurchase">
                <div class="form-group mt-3">

                    <form method="post" enctype="multipart/form-data">

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
                            <input type="description" class="form-control" name="description" placeholder="Опишите покупку">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="cost" placeholder="Стоимость">
                        </div>

                        <div class="form-group">
                            <input type="date" name="purchaseDate">
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <input type="hidden" name="id" value="<#if purchase??> ${purchase.id}</#if>" />

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>

                </div>
            </div>

        </div>

    <h4>Список ваших покупок:</h4>

    <#include "parts/purchaseTable.ftl" />


    </main>

</@c.page>

