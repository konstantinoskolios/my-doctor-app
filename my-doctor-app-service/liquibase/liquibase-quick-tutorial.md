# Liquibase Schema Update Guide

To create or update a schema using Liquibase, follow these steps:

1. **Create a New Version:**
    - Add a folder under `liquibase/versions/vxxx` where `xxx` is your version number.

2. **Create a New Table:**
    - To add a new table, create a folder under the version directory named "table" with the schema.

3. **Modify Existing Table Schema:**
    - For existing table changes, add a folder under the version directory ("insert", "remove", or "edit").

4. **Update Changelog Configuration:**
    - Don't forget to include your folder in relevant changelog files, such as `changelog-master.yml`, `change-log-table.yml`, or `change-log-insert/edit/remove.yml`.

   Example (changelog-master.yml):
   ```yaml
   databaseChangeLog:
     - includeAll:
         path: liquibase/versions/vxxx/table/
     - includeAll:
         path: liquibase/versions/vxxx/insert/
     # Add more includes as needed


# Apply Changes

- After making changes, run liquibase update with the path to the changelog file:
   ``liquibase update --changeLogFile=../liquibase/changelog/changelog-master.yml``