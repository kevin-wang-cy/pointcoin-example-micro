## Create MySQL Database
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
./pointcoin-example-micro-app.jar


## How to Encrypt Password
```bash
docker run --name app-base-tmp --rm \
    -e APP_ALIAS=EXAMPLE1 \
    -e JASYPT_ENCRYPTOR_PASSWORD=youneverknow \
    kevinwangcy/upchain:pointcoin-app-base-latest \
    encrypt input=123456

```
