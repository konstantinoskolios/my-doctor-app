# Liquibase Documentation by Konstantinos Kolios

## Introduction

Liquibase is an open-source database version control and schema migration tool. It empowers you to efficiently manage
database changes, maintain a comprehensive record of database schema history, and automate the application of
modifications to your database. This simplifies the process of database management and fosters effective collaboration
in projects driven by databases. Liquibase boasts compatibility with various database management systems, including
MySQL, PostgreSQL, Oracle, SQL Server, and more.

## Why Choose Liquibase?

When initially considering database schema version control tools, I contemplated using Flyway due to my familiarity with
it. However, after careful evaluation, I opted to explore Liquibase because of its versatility and rich feature set.
Liquibase supports multiple formats, including XML, JSON, YAML, and pure SQL, providing exceptional flexibility.

One standout feature of Liquibase is its robust rollback mechanism, which surpasses the capabilities of Flyway.
Liquibase offers several advantages:

- **Consistency:** Liquibase ensures that database changes are applied uniformly across different environments,
  facilitating seamless collaboration among development teams.

- **Database-Agnostic:** Liquibase supports a wide array of database management systems, offering the freedom to work
  with the platform that best suits your project.

- **Automation:** Liquibase streamlines the process of applying database changes, mitigating the risk of manual errors
  and saving valuable time during deployment and maintenance.

## Liquibase vs. Flyway

While both Liquibase and Flyway are powerful tools for managing database schema versions, Liquibase has distinct
advantages:

- **Versatility:** Liquibase's support for various formats and its comprehensive rollback mechanism provide greater
  flexibility and reliability.

- **Automation:** Liquibase's automation capabilities offer significant time savings and reduced risk.

- **Cross-Platform Support:** Liquibase's database-agnostic nature allows you to work with a wide range of database
  management systems.

## Best Practices

### Versioning Structure

To maintain an organized versioning structure, it is recommended to follow these guidelines:

1. **Version Directory:** Under the path 'liquibase_tool/changelog/versions,' each version is organized as a directory.

2. **Structure:** Within each version directory, maintain separate folders for 'inserts' and 'tables.'

3. **Inserts Folder:** The 'inserts' folder contains insert scripts primarily for testing and cleaner code organization.

4. **Tables Folder:** The 'tables' folder is crucial for housing changes related to creating database schemas, altering
   columns, and other essential modifications.

5. **Constraints:** If a version introduces numerous constraint rules, consider creating a separate 'constraints' folder
   to enhance clarity.

### Running Liquibase Scripts Manually

To manually run Liquibase scripts, follow these steps:

1. Navigate to the directory where your Liquibase scripts are located. Typically, this path is '../liquibase/changelog'.

2. Run the following command to apply the Liquibase scripts: __liquibase update__

This command will execute the pending database changes defined in your Liquibase scripts.

### Changelog-Tables.yaml

It is essential to include each new version in the 'changelog-tables.yaml' file to maintain a clear versioning log. This
practice helps ensure that the changelog-table is up-to-date with your project's progress.

### Rollback Scripts

Liquibase empowers you to write rollback scripts for a wide range of scenarios, including drop and insert cases, not
just for simple scenarios like creating or altering databases. While complex rollback implementations are not always
necessary, Liquibase provides the flexibility to handle them when required.

## Conclusion

Liquibase is a comprehensive tool for managing and evolving your database schema efficiently. Its advanced features,
flexibility, and automation capabilities contribute to smoother collaboration and guarantee the integrity of your
database-driven applications.

For in-depth information, practical examples, and advanced usage of Liquibase, please refer to the official Liquibase
documentation: [Liquibase Documentation](https://docs.liquibase.com/)



# PowerShell Flexibility
=======================

Harnessing the power of PowerShell scripting can significantly boost your productivity and streamline your development
process. Below, you'll find a PowerShell script designed to simplify the setup of the ELK Stack, a powerful combination
of Elasticsearch, Logstash, Kibana, and Filebeat for efficient log management.

## ELK Stack Automation Script (elk_stack.ps1)

This PowerShell script automates the deployment of the ELK Stack, comprising Elasticsearch, Logstash, Kibana, and
Filebeat. The script orchestrates the startup sequence as follows:

1. It initiates Elasticsearch with a 10-second delay to ensure a stable foundation.
2. Next, it launches Logstash, facilitating the ingestion of application logs into Elasticsearch.
3. Finally, it initiates Kibana, providing a user-friendly interface to query Elasticsearch and visualize log data.

**Important Note**: To complete the ELK Stack configuration, you'll also need to run Filebeat, a crucial component
responsible for fetching logs from your application and forwarding them to Logstash. Filebeat ensures that you have
access to the most recent log data, enhancing your debugging and analysis capabilities.

Here's a brief overview of the key components within this ELK Stack ecosystem:

1. **Elasticsearch**:
    - Elasticsearch is responsible for indexing and storing log data efficiently.
    - It requires input logs to create searchable indexes, making it a central component for log management.

2. **Logstash**:
    - Logstash plays a vital role in retrieving logs generated by your applications.
    - It serves as the intermediary between your application logs and Elasticsearch, facilitating log parsing and
      enrichment.

3. **Kibana**:
    - Kibana is the user interface (UI) tool that allows you to interact with Elasticsearch.
    - With Kibana, you can query Elasticsearch, create visualizations, and gain insights from your log data effectively.

4. **Filebeat**:
    - Filebeat serves as the mechanism for collecting logs from your applications.
    - It is a flexible agent that ensures the seamless and real-time forwarding of log data to Logstash, enabling you to
      stay up-to-date with your application's logs.

By automating the setup of the ELK Stack with this script, you can easily harness its capabilities for efficient log
management, troubleshooting, and analysis.


# Running the ELK Stack Application

In this guide, we'll explore two methods to run the ELK Stack application: one using Docker (for Linux systems) and
another for manual setup (for Windows systems).

## Using Docker (Linux System)

1. Locate the `docker-compose.yml` file in your project directory.

2. Uncomment all necessary lines within the `docker-compose.yml` file to pull images for Elastic, Logstash, and other
   components.

3. Run the script called `docker_start.sh`

4. Ensure you are using a Linux system, as there may be compatibility issues with Docker on Windows.

## Manual Setup (Windows System)

For successful manual setup of the ELK Stack on a Windows machine, follow these steps:

### Step 1: Download Required Components

1. Download Elasticsearch from [here](https://www.elastic.co/downloads/past-releases/elasticsearch-7-17-8).

2. Download Logstash from [here](https://www.elastic.co/downloads/past-releases/logstash-7-17-8).

3. Download Kibana from [here](https://www.elastic.co/downloads/past-releases/kibana-7-17-8).

4. Create a directory at `C:\Program Files\elk` and place the downloaded files in this directory.

### Step 2: Configure Filebeat

1. Download and install Filebeat from [here](https://www.elastic.co/downloads/beats/filebeat).

2. Place the configuration files, `filebeat.yml` and `logstash.conf`, in their respective directories:

    - For `logstash.conf`:
        - Go to `C:\Program Files\elk\logstash-7.17.8\config`.
        - Copy and paste the `logstash.conf` file from your project's
          directory (`my-doctor-app/elastic-logstash-kibana-filebeat`).

    - For `filebeat.yml`:
        - Go to `C:\Program Files\Filebeat`.
        - Copy and paste the `filebeat.yml` file from your project's
          directory (`my-doctor-app/elastic-logstash-kibana-filebeat`).

### Step 3: Install and Configure Filebeat

1. Open PowerShell as an administrator.

2. Navigate to `C:\Program Files\Filebeat\`

3. Run the following commands to configure Filebeat:

   ```powershell
   .\install-service-filebeat.ps1
   .\filebeat.exe modules list
   .\filebeat.exe modules enable nginx
   .\filebeat.exe setup -e

### Step 4: Set Execution Policy (if needed)

If required, you can set the execution policy for PowerShell by following these steps:

1. Open PowerShell as an administrator.

2. Navigate to `C:\Program Files\Filebeat\`.

3. Run the following command to set the execution policy:

   ```powershell
   Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope LocalMachine

With these steps, you should be able to successfully set up and run the ELK Stack on your Windows machine.

### Step 5: Run Elk Script

Execute the script located at `my-doctor-app/powershell_scripts/elk_stack.ps1`. You can refer to `powershell.md` for
further guidance on working with the ELK Stack.


Bash Flexibility
================

Incorporating the power of Bash scripting can significantly enhance productivity and streamline your development
process.
Here are some Bash scripts designed to automate tasks and expedite development:

## Docker Deployment Script (docker_start.sh)

This script simplifies the process of deploying a PostgreSQL database on your local machine.
It establishes a connection to the database and executes Liquibase scripts to set up the database schema effortlessly.

## Citizen Data Mocking Script (citizen_mock_data.sh)

This script employs Python's Faker library to generate mock data for citizens, creating a total of 5,000 records with
ease

## Start App Plus Data Script (start_app_plus_data.sh)

This comprehensive script encompasses the functionalities of the aforementioned scripts.
It not only deploys the database and generates mock citizen data but also builds and runs the application using Gradle (
gradlew).
It serves as a "plug and play" solution for those who prefer avoiding manual configurations and wish to quickly launch
the application.

These Bash scripts empower you to work more efficiently, automate repetitive tasks, and expedite the development
process.
