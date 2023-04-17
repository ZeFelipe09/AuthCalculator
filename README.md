# Calculadora com autenticação JWT

**Login:** </br>
Para fazer login e conseguir um token JWT basta acessar http://localhost:8080/login e passar um login válido no banco de dados


**Cálculo:** </br>

Para fazer o cálculo basta fazer a chamada de um post para http://localhost:8080/calc/operation e passar no body o seguinte json

{
    "expressao": "calculo"
}

**IMPORTANTE:** </br>

É necessário uma conexão local no banco MySql para o funcionamento correto da aplicação






