# DesafioRenner
 
O Desafio foi realizado com JAVA, RestAssured, Cucumber e BDD.
Os resultados de teste estão na pasta output, no arquivo report.html
A classe que executa os testes está na pasta test\java\cucumberTest\TestRunner

Algumas observações encontradas na exexução de testes:

1-Conforme o documento de especificação da API ao tentar incluir uma simulação com um cpf existente deveria ser retornado:
 o status code: 409 e mensagem:"CPF já existente" 
 Porém para esse teste encontrou um defeito na documentação, pois o retorno é status code 400 e mensagem: {"mensagem":"CPF duplicado"}

2-Ao incluir uma simulação com cpf no formato incorreto deveria ser retorno erro 400 e o motivo do erro;
 Porém está sendo permitido incluir cpf com formato incorreto.
 
3-Ao realizar o delete de uma simulação não existente é retornado o status code 200, na documentação é informado que o correto status code é 404 e não é apresentado mensagem de simulação não existente.
4-Ao Realizar o delete de uma simulação existente é retornado o status code 200, na documentação é informado que o correto status code é 204 

5- Ao realizar um consulta na restrição do CPF é retornado a mensagem "O CPF 97093236014 tem problema",  porém na documentação está drecrito ""O CPF
99999999999 possui restrição" 
 
