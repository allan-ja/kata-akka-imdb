# Kata IMDB

## Setup
First you need to download the data file. You can use the shell script made for that.

```shell script
./download_data.sh
```

You also need to give the RabbitMQ URI as an environment variable. For development purpose, I set up a free instance on 
[CloudAMQP](https://www.cloudamqp.com/)

```shell script
export RABBITMQ_URI="amqp://USERNAME:PWD@HOST"
````

## To start

To start the application with `sbt`

```shell script
sbt run
```



