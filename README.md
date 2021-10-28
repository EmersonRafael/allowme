# Allowme

Configuração de base no arquivo application.properties

```sh
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/challenge-db
spring.datasource.username=allowme
spring.datasource.password=password
```
## Requisitos

# Banco de dados

[Scripts das tabelas](https://github.com/EmersonRafael/allowme/tree/master/src/main/resources/Scripts)

# Teste gerarBilling 

```sh
curl --location --request POST 'http://localhost:8080/allowMe/gerarBilling' \
--header 'Authorization: Basic dGVtcGVzdDp0ZW1wZXN0' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=D1B4763CFDD44417ABBD9B1C2DC0D11D' \
--data-raw '{
    "start":"2021-10-28",
    "end": "2021-10-28"
}'
```
# Testes Unitários

Executar a classe SystemLogApplicationTests.java com JUnit Teste para validar todos os endpoints da classe LogControle.java

Emerson Rafael Maia Melo – emerson.rafael.maia.melo@gmail.com.

[![LinkedIn](https://camo.githubusercontent.com/c456ce1e22c379a6ff198bbb3a2d96f24fc94408/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d4c696e6b6564496e2d626c61636b2e7376673f7374796c653d666c61742d737175617265266c6f676f3d6c696e6b6564696e26636f6c6f72423d353535)](https://www.linkedin.com/in/emerson-rafael-20479461/)

