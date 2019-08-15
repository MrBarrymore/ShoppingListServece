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
            <div class="collapse <#if purchaseObject??>show</#if>" id="collapseAddPurchase">
                <div class="form-group mt-3">

                    <form method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                                   value="<#if purchaseObject??>${purchaseObject.name}</#if>" name="name" placeholder="Введите название товара" />
                            <#if nameError??>
                                <div class="invalid-feedback">
                                    ${nameError}
                                </div>
                            </#if>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control ${(categoryError??)?string('is-invalid', '')}"
                                    name="cat" placeholder="Выберете категорию товара">
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
                            <input type="text" class="form-control" name="date" placeholder="Дата покупки">
                        </div>

                        <div class="well">
                            <div id="datetimepicker3" class="input-append">
                                <input data-format="hh:mm:ss" type="text"></input>
                                <span class="add-on">
                                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                                  </i>
                                </span>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <input type="hidden" name="id" value="<#if purchaseObject??> ${purchaseObject.id}</#if>" />
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>

                </div>
            </div>

        </div>


<#include "parts/purchaseTable.ftl" />


    </main>

</@c.page>

