#!/bin/bash
database=TubeCentric
wait_time=15s
username=sa
password=Password1!

# wait for the SQL to come online
echo Database intialization will start in $wait_time
sleep $wait_time
echo Creating database: $database

# run the init script to create the database
/opt/mssql-tools/bin/sqlcmd -S 0.0.0.0 -U $username -P $password -i /startup-script/mssql_start_queries.sql
