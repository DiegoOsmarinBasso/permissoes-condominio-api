# permissoes-condominio-api
Retorna a lista de permissões mais altas que determinado usuário possui em cada condomínio.

## Instalação
Tenha maven 3.5 e Java 1.11 instaldos e abra um terminal na pasta que deseja copiar o projeto
<br/>
`git clone https://github.com/DiegoOsmarinBasso/permissoes-condominio-api.git`
<br/>
`cd permissoes-condominio-api`
<br/>
`mvn spring-boot:run`
<br/>

## Utilização
Acesso: Após instalar (passo acima) abra um navegador, acesse http://localhost:8080/swagger-ui.html e clique em `condominio-permissoes-controller`, após em `GET` e após em `Try it out`
<br/>
Opcional: você pode informar o arquivo contendo a base de dados, este arquivo deve existir dentro da pasta `arquivos` que fica na raiz do projeto.
Caso não seja informado, o sistema irá procurar por um arquivo com o nome padrão `base_usuarios.txt`.
<br/>
Obrigatório: informar o e-mail do usuário
