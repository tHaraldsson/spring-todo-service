# Spring Todo Service #

En webbtjänst byggd i Java Spring Boot som hanterar användare och deras att-göra-uppgifter.
Systemet har stöd för autentisering, användarroller (USER/ADMIN) samt CRUD-operationer för tasks.

### <u>Starta projektet</u> ###

Klona repot:

git clone https://github.com/tHaraldsson/spring-todo-service
cd spring-todo-service

Tjänsten körs på:

http://localhost:8080 - lokalt **(OBS kräver .env fil med
DB_USER
DB_PASSWORD
DB_URL)**

https://spring-todo-service.onrender.com - Live via Render

Exempel på API-anrop:
Skapa användare
POST /users/createuser
Content-Type: application/json

{
"email": "test@test.com",
"password": "hemligt"
}

Logga in (via headers)
POST /tasks/createtask
Headers:
email: test@test.com
password: hemligt
Body:
{
"title": "Köpa mjölk",
"description": "Kom ihåg att köpa mjölk på vägen hem"
}

Hämta mina todos
GET /tasks/mytasks
Headers:
email: test@test.com
password: hemligt

Admin – lista alla användare
GET /users/admin/getallusers
Headers:
email: admin@test.se
password: Admintest1

---
**Reflektion**

I detta projekt har vi lärt oss:

* Webbtjänster: hur man bygger REST API:er med Spring Boot och hanterar requests/responses.

* Versionshantering: att samarbeta i GitHub, använda branches, commits och pull requests.

* Samarbete: att utveckla i team och använda GitHub Project och skriva gemensam dokumentation.

* God praxis i Java: att strukturera koden med serviceklasser, repositories och DTO:er,
  vilket gör projektet mer modulärt, testbart och lättare att underhålla.

* Databaser: hur man modellerar relationer mellan användare och tasks,
  använder JPA/Hibernate för att interagera med databasen och implementerar CRUD-operationer via repository-lager.

---
