# Start Keycloak

- cd ../root/docker-compose.yml
- docker compose up -d
- login to keycloak : http://localhost:8080/admin/master/console/ (port is different based on docker-compose)
- username: admin
- password: admin

# Create Realm

- springboot-microservice-realm
- issuer: http://localhost:8080/realms/springboot-microservice-realm
- token-endpoint: http://localhost:8080/realms/springboot-microservice-realm/protocol/openid-connect/token

# Create Clients

- microservice-auth
- enable Client-Authentication
- enable only 'Service Account Roles' from the 'Authentication Flow'
- set rootUrl: http://localhost:9999 (note this is #gateway url/port)
- set homeUrl: http://localhost:9999 (note this is #gateway url/port)
- client secret: i9y07waNc2xWTfuRgE46yt6OKAq32T0t

# Create User

- create user: konstantinos-user
- create admin: konstantinos-admin

# Create Role

- create role: admin
- create role: user