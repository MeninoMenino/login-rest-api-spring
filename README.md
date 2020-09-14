# login-rest-api-spring

Descrição: Rest API desenvolvida com Spring, usando Hibernate/JPA para persistência de dados, e JWT para autenticação de login usando token de acesso.

-----Endpoints-----

POST /usuarios/{email} - Insere um novo usuário com os dados informados no corpo da requisição.
Corpo da Requisição: "email", "nome", "senha", "telefones"[{"ddd", "numero", "tipo"}]

POST /login - Autentica os dados enviados no corpo da requisição e retorna um token de acesso, que expira em 30 minutos.
Corpo da Requisição: "email", "senha"

-----Operações apenas com autenticação de token-----

GET /usuarios - Lista todos os usuários do sistema

GET /usuarios/{email} - Retorna o usuário com o e-mail informado (caso haja)

PUT /usuarios/{email} - Altera os dados do usuário com o e-mail informado. 
Corpo da Requisição: "email", "nome", "senha", "telefones"[{"ddd", "numero", "tipo"}]

DELETE /usuarios/{email} - Deleta o registro com o e-mail informado.
