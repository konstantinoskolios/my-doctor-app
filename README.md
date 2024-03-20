# Project Setup and Development Guide

## Cloning the Project
1. Use the command `git clone https://github.com/SylvanasGr/my-doctor-app` to download and clone the project.
2. Open the project located at `../my-doctor-app`.

## Setting Up Keycloak
3. Follow the instructions provided in [`my-doctor-app/api-gateway/keycloak.md`](path-to-keycloak.md).

## Starting Microservices
4. Start the microservices in the following sequence:
    - a) discovery-server
    - b) configuration-service
    - c) api-gateway
    - d) my-doctor-app-service
    - e) payment-service
    - f) appointment-service

## Initial Setup (Skip if already completed)
5. Open a terminal and navigate to `~/my-doctor-app-service/liquibase/changelog`. Execute `liquibase update` to create the schemas.
6. Run the script located at `./global/bash_scripts/citizen_mock_data.sh`.

## Development Steps
7. Select a random record from the 'citizen' table and assume the identity of that user. (`SELECT * FROM citizen ORDER BY random() LIMIT 1`)
8. Visit http://localhost:8500/login, select 'Login' -> 'Keycloak', then choose 'Register'.
9. Provide your real information, excluding the data from the user selected in step 7.
10. Upon successful login, navigate to the 'User Page' to validate your existence by providing your tax number and social number to the system.
11. Enter the tax number and social number as indicated in step 7.
12. If the record from step 7 now reflects your data, it simulates the process of mock data generation, similar to a real scenario on the Greek Government site.
13. Navigate to the 'Doctors' tab to request an appointment.
14. Choose 'Schedule Appointment' and select a date and time.
15. Go to the 'Appointments' tab to view the status of your appointment.
16. Open another browser, log in as the chosen doctor from step 15, and navigate to 'Appointments' to accept the appointment.
17. Refresh the page to validate that the appointment has been accepted (Status: Accepted), and check the 'Payments' tab to view the payment history.
18. You can also verify the same process from the perspective of a doctor.
19. As a doctor, add the patient for the appointment using the 'Add Patient' button and prescribe necessary medications (Add Prescriptions).
20. Check the associated prescriptions from the 'Prescription' tab as a user.

## Tools Installation
To efficiently work on the project, ensure you have the following tools installed:

1. **Git**: Version control system for tracking changes in source code during development.
   - [Download Git](https://git-scm.com/downloads)

2. **Gradle**: Build automation tool for managing dependencies and building projects.
   - [Download Gradle](https://gradle.org/install/)

3. **JDK 17**: Java Development Kit, the environment required to develop and run Java applications.
   - [Download JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

4. **Docker Desktop**: Platform for building, sharing, and running containerized applications.
   - [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)

5. **Liquibase**: Database schema change management tool for tracking, managing, and applying database changes.
   - [Download Liquibase](https://www.liquibase.com/download)

6. **Python**: Optional, but useful for various scripting and development tasks.
   - [Download Python](https://www.python.org/downloads/)

Make sure to install the appropriate versions compatible with your operating system. Additionally, consider using an Integrated Development Environment (IDE) for a more efficient development experience. JetBrains IntelliJ IDEA is recommended, but feel free to choose the IDE you're most comfortable with.
