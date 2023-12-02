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
