# Start Keycloak

- cd ../root/docker-compose.yml
- docker compose up -d
- login to keycloak : http://localhost:8080/admin/master/console/ (port is different based on docker-compose)
- username: admin
- password: admin

# Create Realm

- doctor-app-realm
- issuer: http://localhost:8080/realms/doctor-app-realm
- token-endpoint: http://localhost:8080/realms/doctor-app-realm/protocol/openid-connect/token

# Create Clients

- doctor-app
- enable Client-Authentication
- enable only 'Service Account Roles' from the 'Authentication Flow'
- set rootUrl: http://localhost:9999 (note this is #gateway url/port)
- set homeUrl: http://localhost:9999 (note this is #gateway url/port)
- client secret: xxxxxxxxxxxxxxxxxx #secret will be different every time

# Create Role

- create role: doctor-app-admin 
- create role: doctor-app-user 
- create role: doctor-app-super-user 

# Create Groups

- create group: admins   -> role mapping -> doctor-app-admin
- create group: citizens -> role mapping -> doctor-app-user
- create group: doctors  -> role mapping -> doctor-app-super-user

# Create Admin

- create admin with valid email ending in the pattern *@admin.com
- select 'Email Verified'
- Join Groups > admin

# Create Super-User

- create admin with valid email ending in the pattern *@doctorapp.com
- select 'Email Verified'
- Join Groups > doctors

# Create Super-User Attributes

- Users > Attributes 
- Add attribute for 'speciality' in order to descript the category of the super-user meaning 'Cardiologist,Pathologist' and etc.

# Add Attributes Mapper In Order To Convert Attribute to Json Format

- Clients > doctor-app > Client Scopes > doctor-app-dedicated > Add mapper > By configuration > User attribute
- Add "speciality" attribute for name, user attribute and token-claim-name and press save

# Realm Settings

- Enable User Registration -> Default Groups -> Add groups -> citizens

# Add Identity Providers

- Need to provide your personal auth2 client/secret in order to test it.
- Facebook
- Github
- Google