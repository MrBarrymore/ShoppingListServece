<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <main class="main ml-4 mr-4">

        <div class="form-row ml-2">
            <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Поиск по категории">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
            </div>
        </div>

        <div class="container ml-2">
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddPurchase" role="button" aria-expanded="false" aria-controls="collapseAddPurchase">
                Add new Message
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
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <h3>Список покупок:</h3>
        <div class="card-columns ml-2">
             <#list purchases as purchase>
                  <div class="card bg-light mb-3 bg-secondary mb-3" style="max-width: 18rem;">
                      <div class="card-header"><i>${purchase.name}</i></div>
                      <div class="card-header">
                          <h5 class="card-title"><i>${purchase.category}</i></h5>
                          <p class="card-text"></p>
                      </div>
                      <div class="card-footer bg-transparent"> <i>${purchase.purchaseDate}</i> <strong>${purchase.authorName}</strong></div>
                  </div>
             <#else>
                 No purchases
             </#list>
        </div>

    </main>
</@c.page>