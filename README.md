# GraphQLMutations-SpringBoot
H2 Database, Sping Web, Sping Data JPA, Spring for GraphQL

Contains:
- Create Mutation passing each parameter
- Create Mutation passing Input parameter
- Batch Create Mutation passing a list of resources.

### Ref
https://youtu.be/u3FFRq3-0CM?si=BnX5vS5vA8KWUMXp

## Maven project bootstrapped with start.sping.io
https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.3.2&packaging=jar&jvmVersion=17&groupId=com.jorgeherreria&artifactId=mutations&name=mutations&description=Learning%20GraphQL%20Mutations%20with%20Spring%20Boot&packageName=com.jorgeherreria.mutations&dependencies=web,graphql,data-jpa,h2


## Graphiql 
http://localhost:8080/graphiql?path=/graphql
Invokaction expamples
```
query FindAllBooks{
 findAllBooks{
  id
  title
  author
  reviews{
    title
    comment
  }
}
}
mutation CreateBook{
  createBook(title:"Lord of the Rings" pages:35 author:"Jorge Herreria"){
    id
    title
    author
  }
}
mutation AddBook{
  addBook(book: {
    title:"Si lo consigo"
    pages: 2
    author: "Jorge Herreria"
  }){
    id
    title
  }
}
mutation BatchCreate{
  batchCreate(books:[
    { title: "uno" pages:1 author: "Pet"},
    { title: "dos" pages:2 author: "Booh"},
    { title: "tres" pages:3 author: "Mac"},
    
  ]){
    id
    title
  }
}
```