## Create MySQL Database
This is optional, just in case you don't have MySQL Database on hand,
you can use below docker command to bring up a MySQL Database for
your micro-service pretty easy.
```bash
docker run -d -p 3306:3306 \
    --name mysql-example1 \
    -e MYSQL_ROOT_PASSWORD=Rdisfun4Root \
    -e MYSQL_DATABASE=example1 \
    -e MYSQL_USER=example1 \
    -e MYSQL_PASSWORD=123456 \
    mysql:latest

```

## Setup Environment
Below environment variables are neccessary on the host you intend to 
run this micro-service.

```bash
export JASYPT_ENCRYPTOR_PASSWORD=youneverknow

export POINTCOIN_APP_ALIAS=EXAMPLE1

export POINTCOIN_MYSQL_HOST=127.0.0.1
export POINTCOIN_MYSQL_PORT=3306
export POINTCOIN_MYSQL_DATABASE=example1
export POINTCOIN_MYSQL_USER=example1
export POINTCOIN_MYSQL_PASSWORD=ENC(Q29LMOQyfatdOUO4IyUwbQ==)
# export POINTCOIN_MYSQL_PASSWORD=123456

export POINTCOIN_UAA_TOKENKEY_URI=http://127.0.0.1:8080/uaa/oauth/token_key

```

## Start App with mysql profile

With above environment variable set up, you simply need run below command to 
bring up this micro-service.
```bash
./pointcoin-example-micro-app.jar
```



## How to Encrypt Password
You may want to use different password, they you can use below command
to get your encrypted password for the variables.
```bash
docker run --name app-base-tmp --rm \
    -e APP_ALIAS=EXAMPLE1 \
    -e JASYPT_ENCRYPTOR_PASSWORD=youneverknow \
    kevinwangcy/upchain:pointcoin-app-base-latest \
    encrypt input=123456

```

Note that above command assume you have docker installed the host
you are going to run above command. It would take longer time in
the first run as it has to pull the image. I would suggest you pull
the image first and only start run above command after it fully downloaded.

