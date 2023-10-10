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
