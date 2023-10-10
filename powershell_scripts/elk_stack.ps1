$ElasticSearchPath = "C:\Program Files\elk\elasticsearch-7.17.8\bin\elasticsearch.bat"
$LogstashPath = "C:\Program Files\elk\logstash-7.17.8\bin\logstash.bat"
$LogstashConfigPath = "C:\Program Files\elk\logstash-7.17.8\config\logstash.conf"
$KibanaPath = "C:\Program Files\elk\kibana-7.17.8\bin\kibana.bat"

if (Test-Path -Path $ElasticSearchPath -PathType Leaf)
{
    Start-Process -FilePath $ElasticSearchPath
    Write-Host "Waiting for Elasticsearch to start..."
    Start-Sleep -Seconds 10
}
else
{
    Write-Host "ElasticSearch batch file not found at $ElasticSearchPath."
}


if (Test-Path -Path $KibanaPath -PathType Leaf)
{
    Start-Process -FilePath $KibanaPath
}
else
{
    Write-Host "Kibana batch file not found at $KibanaPath."
}

if (Test-Path -Path $LogstashPath -PathType Leaf)
{
    & $LogstashPath -f $LogstashConfigPath --config.reload.automatic

}
else
{
    Write-Host "Logstash batch file not found at $LogstashPath."
}
