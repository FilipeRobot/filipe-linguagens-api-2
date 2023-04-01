# API de Linguagens de programação

uma API feita na imersão java usando Spring boot

```

GET

- (/) - Pagina de boas vindas
- (/linguagens) - Lista de linguagens registradas no banco
- (/linguagens/{id}) - Linguagem encontrada a partir do ID

POST

- (/linguagens) - Registrar/Cadastrar linguagem no banco

PUT

- (/linguagens/{id}) - Atualizar Linguagem encontrada a partir do ID

DELETE

- (/linguagens/{id}) - Deleta Linguagem informada a partir do ID


```

Modelo dos dados esperados para serem cadastrados/atualizados no banco

```json
{
  "title": "Java",
  "url": "URL/<ImageName>.png",
  "ranking": Numero_do_ranking
}
```
