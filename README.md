# security-jwt-quickstart project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

Application based on https://quarkus.io/guides/security-jwt tutorial

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
./mvnw quarkus:dev
```

## Usage

First, call `register` endpoint (GET) with the user data to receive the JWT Token

```
http://127.0.0.1:8080/register?email=<USER_EMAIL>&username=<USER_NAME>&birthdate=<BIRTH_DATE_YYYY-MM-DD>
```

After, add the token to the header `Authorization: Bearer <TOKEN>` and send it to the secured endpoint (GET)

```
http://127.0.0.1:8080/secured/winners
```

Calling to `verify` endpoint (GET) will result in a `403 - Forbidden error`. This route is allowed just for Admin.

```
http://127.0.0.1:8080/secured/verify
```

## Creating the public and private keys

First, it is necessary to generate a base key to be signed:

```
openssl genrsa -out baseKey.pem
```

From the base key generate the PKCS#8 private key:

```
openssl pkcs8 -topk8 -inform PEM -in baseKey.pem -out privateKey.pem -nocrypt
```

Using the private key you could generate a public (and distributable) key

```
openssl rsa -in baseKey.pem -pubout -outform PEM -out publicKey.pem
```
