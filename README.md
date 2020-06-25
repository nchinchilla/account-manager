README.md
Getting Started

API Rest for manage accounts and transaction. It provides four endpoints:

One to insert a new account, another to delete this account if this not have transaction associated.
In another hand one endpoint for create a transaction, and the last for list all de transaction for an account. And the last it is for list account by id.

Reference Documentation
 
API Rest The API documentation can be found here https://tecso-account-manager.herokuapp.com/swagger-ui.html#/

The following examples illustrate how to use the defined endpoints:

Request on create an account endpoint: curl -X POST "http://localhost:8080/tecso/account" -H "accept: /" -H "Content-Type: application/json" -d "{
"accountNumber":"10",	"balance" : 100.01, "currency": "DOLAR" }"

Request on delete an account endpoint: curl -X POST "http://localhost:8080/tecso/account/delete/{accountNumber}" -H "accept: /" -H "Content-Type: application/json" 

Request on create an transaction endpoint: curl -X POST "http://localhost:8080/tecso/transaction" -H "accept: /" -H "Content-Type: application/json" -d "{
	"accountNumber": "10", "transactionType":"DEBIT", "description":"chequear", "amount":100.00, "currency": "DOLAR" }"

Request on list account by accountNumber endpoint: curl --request GET "http://localhost:8080/tecso/account/10"

Request on listAccount endpoint: curl --request GET "http://localhost:8080/tecso/account"

Tests a set of unit tests can be found under /src/test/java

Use 'mvn test' for execute the unit tests

$ mvn test

Running the API There is two alternatives, both depend maven.

From Spring Boot Maven plugin $ mvn spring-boot:run

From Java (it requires Spring Boot Maven plugin to create an executable jar) $ java -jar target/hello-services-1.0-SNAPSHOT.jar

From Docker Desktop

Build an image docker build -t proj:account-manager
run container docker run -p 8080:8080 proj:account-manager


Extra Information

For this project, in the future I would prefer implements some upgrades. For example adding spring-security with jwtoken for more security, but i did not have time for this. In other hand with the limit for transaction and currency I would prefer use a database to persist the limit, and maybe springcloud to check the healthy of de service.

