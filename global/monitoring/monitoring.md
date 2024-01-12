# Setting up Monitoring System for Our Application

## Overview

In order to effectively monitor our application, we'll be deploying Grafana, Prometheus, and the Postgres Exporter using the provided `docker-compose.yml` file. Each of these components plays a crucial role in collecting, storing, and visualizing metrics, ensuring a comprehensive monitoring solution.

## Components

### Grafana

**What is Grafana?**
Grafana is an open-source platform for monitoring and observability, allowing us to create customizable and interactive dashboards. It integrates with various data sources, including Prometheus, to visualize and analyze metrics effectively.

**Configuration:**
Navigate to `./global/monitoring/grafana/provision/datasources/datasources.yml` and set the Prometheus port to ensure seamless communication between Grafana and Prometheus.

### Prometheus

**What is Prometheus?**
Prometheus is an open-source monitoring and alerting toolkit designed for reliability and scalability. It collects metrics from monitored targets, stores them, and provides a powerful query language for analysis.

**Configuration:**
Access `./global/monitoring/prometheus/prometheus.yml` to configure Prometheus. Set up the target ports for application metrics via the actuator. Additionally, configure the Prometheus server to utilize the Postgres Exporter for reading metrics from the PostgreSQL database.

### Postgres Exporter for Prometheus

**Why use Postgres Exporter for Prometheus?**
Postgres Exporter acts as an intermediary, facilitating the extraction of metrics from a PostgreSQL database, making them available for Prometheus to scrape. This integration ensures that we can monitor the performance and health of our PostgreSQL database effectively.

**Configuration:**
Ensure that the Prometheus server is configured in `prometheus.yml` to collect metrics from the Postgres Exporter, thus enabling Prometheus to retrieve detailed insights into the PostgreSQL database.

### Grafana Setup with Prometheus

1. **Access Grafana UI:**
    - Visit `localhost:3000` in your browser.

2. **Set Up Prometheus Data Source:**
    - Open Grafana settings, go to "Data Sources."
    - Add a new Prometheus data source with URL `http://prometheus:9090`.
    - Save & Test.

3. **Import Spring Boot Dashboard:**
    - Go to "+" > "Import" in Grafana.
    - Enter dashboard ID `19004`, load the configuration.
    - Configure "Prometheus" data source, click "Import."

4. **Import PostgreSQL Dashboard:**
    - Similarly, go to "+" > "Import."
    - Enter dashboard ID `9628`, load the configuration.
    - Configure "Prometheus" data source, click "Import."

5. **Verify Dashboards:**
    - Open the Spring Boot and PostgreSQL dashboards in Grafana.

Replace `localhost` and adjust ports as needed based on your environment.

## Implementation Steps

1. Run `docker-compose.yml` to deploy Grafana, Prometheus, and the Postgres Exporter.
2. Configure Grafana to communicate with Prometheus by setting the correct port in `datasources.yml`.
3. Adjust Prometheus settings in `prometheus.yml` to specify target ports for application metrics and set up Postgres Exporter.
4. Utilize the Prometheus query language to extract meaningful insights from the collected metrics.

By following these steps, we establish a robust monitoring system that empowers us to track and analyze the performance of our application efficiently. The integration of Grafana, Prometheus, and the Postgres Exporter ensures comprehensive monitoring coverage, enhancing our ability to proactively manage and optimize our application's health.
