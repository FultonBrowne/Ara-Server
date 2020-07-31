#! /bin/sh

domain=$1

keycloakPass=$(openssl rand -base64 32)

mongoUserPass=$(openssl rand -base64 32)

mongoAdminPass=$(openssl rand -base64 32)

sqlPass=$(openssl rand -base64 32)

echo "keycloak pass is $keycloakPass"

echo "mongo user password is $mongoUserPass"

echo "mongo admin password is $mongoAdminPass"

echo "sql password is $sqlPass"

