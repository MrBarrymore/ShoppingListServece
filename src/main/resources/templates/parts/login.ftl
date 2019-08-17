<#macro login path isRegisterForm>

    <link rel="stylesheet" type="text/css" href="/static/css/login.css">

    <form class="form-signin" action="${path}" method="post">
        <div class="text-center mb-4">
            <#if isRegisterForm>
                <h1 class="h3 mb-3 font-weight-normal">Регистрация нового пользователя</h1>
                <p>Введите имя пользователя, ваш адрес электронной почты и придумайте пароль</p>
            <#else>
                <h1 class="h3 mb-3 font-weight-normal">Войти в личный кабинет</h1>
                <p>Введите имя пользователя и пароль, которые были указанны при регистрации</p>
            </#if>
        </div>

        <div class="form-label-group">

            <input type="text" id="username"
                   value="<#if user??>${user.username}</#if>"
                   name="username"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="Имя пользователя" required autofocus>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            <label for="inputUsername">Имя пользователя</label>
        </div>

        <div class="form-label-group">
            <input type="password" id="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Пароль" required>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            <label for="inputPassword">Пароль</label>
        </div>

        <#if isRegisterForm>
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
                <input type="email" id="inputEmail" name="email"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       value="<#if user??>${user.email}</#if>"
                       placeholder="Email address" required autofocus>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
                <label for="inputEmail">Email адрес</label>
            </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
            <#if !isRegisterForm><a href="/registration">Регистрация</a></#if>
        </div>
        <p class="mt-5 mb-3 text-muted text-center">&copy; 2019</p>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit" >Выйти</button>
    </form>
</#macro>