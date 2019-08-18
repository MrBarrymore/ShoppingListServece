<#import "parts/common.ftl" as c>


<@c.page>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
    <form class="form-signin" method="post">
        <div class="text-center mb-4">
                <h1 class="h3 mb-3 font-weight-normal">Редактирование профиля пользователя ${username}</h1>
                <p>Введите новый адрес электронной почты или пароль</p>
        </div>
        ${message?ifExists}
        <div class="form-label-group">
            <input type="password" id="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Пароль">
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
            <label for="inputPassword">Пароль</label>
        </div>
        <div class="form-label-group">
            <input type="password" name="password2"
                   class="form-control ${(password2Error??)?string('is-invalid', '')}"
                   placeholder="Повторите пароль" />
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
            <label for="inputPassword">Повторите пароль</label>
        </div>

        <div class="form-label-group">
            <input type="email" name="email"
                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                   value="${email?ifExists}"
                   placeholder="Email address">
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
            <label for="inputEmail">Email адрес</label>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>
            <button class="btn-primary btn-block" type="submit"> Сохранить </button>
        </div>

    </form>

<#--    <h5>${username}</h5>-->
<#--    ${message?ifExists}-->
<#--    <form method="post">-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label">Password:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="password" name="password" class="form-control" placeholder="Password" />-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label">Email:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="email" name="email" class="form-control" placeholder="example: qwerty12345.gmail.com"  value="${email!''}"/>-->
<#--            </div>-->
<#--        </div>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--        <div>-->
<#--            <button class="btn btn-primary" type="submit">Сохранить</button>-->
<#--        </div>-->
    </form>

</@c.page>