# Домашняя работа 6-10
# 6. 
HW6
# 7.
Идею 7.1 я понял, но страница не открывается(
# 8,9,10
Делал на Spring Boot с использованием lombok
  HW9:
  Для использования SpringData JPA в папке UsersRepository расширял класс JPARepository, в свою очередь он расширяет еще два класса, Pingin и Еще какой-то, один из них как раз использует CRUDRepository
  HW10:
  В папке security
  SecurityConfig указывает на то, при каких условиях доступны те или иные пути, пример из дз очень упрощен, вот пример из семестровки, на его основе поясню, что делает SecurityConfig
 
 <code>
 http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration","/login" +
                        "").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//.defaultSuccessUrl("/main", true)
                .failureUrl("/login")
                .and()
                .logout().logoutSuccessUrl("/login");
 </code>
 Т.е Мы разрешаем доступ к login и registraton всем пользователям, которые еще не зарегестировались, вошли в систему, а все остальные запросы должны быть аутентифицированы + мы указываем путь для формы логина
 В CustomAuthProvider мы уже проверяем ip адрес, если он из России, то мы не позволяем войти пользователю, т.к. ip 127.0.0.1, то подставляю другие адреса для теста, узнаю код страны через api сервиса dadata и 2ip, в ответ получаю JSON и получаю из него код страны
