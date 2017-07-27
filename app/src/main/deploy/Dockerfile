# Usage:
#
# docker volume create log-data-volume
#
# docker run --rm com.upbchain.pointcoin/pointcoin-example-micro-app:1.0-SNAPSHOT \
#       encrypt pasword="<jasypt password>" input="<your password>"
#
# docker run -d -p 3306:3306 \
#       --name pointcoindb-mysql \
#       -e MYSQL_ROOT_PASSWORD=Rdisfun4Root \
#       -e MYSQL_DATABASE=pointcoindb \
#       -e MYSQL_USER=pointcoinapiremote \
#       -e MYSQL_PASSWORD=Rdisfun4pointcoinapiremote \
#       mysql:latest
#
# docker run --name example-micro-local-v1.0 --link pointcoindb-mysql:mysql -p 8080:8080 \
#       -e JASYPT_ENCRYPTOR_PASSWORD="<jasypt password>"  \
#
#       -v log-data-volume:/var/log/pointcoin \
#       com.upbchain.pointcoin/pointcoin-example-micro-app:1.0-SNAPSHOT
#
# docker run --name example-micro-remote-v1.0 -p 8080:8080 --restart=always \
#       -e JASYPT_ENCRYPTOR_PASSWORD="<jasypt password>"  \
#
#       -e POINTCOIN_MYSQL_HOST="<mysql db ipaddress>" "\
#       -e POINTCOIN_MYSQL_PORT=<mysql port> \
#       -e POINTCOIN_MYSQL_DATABASE=<pointcoindb> \
#       -e POINTCOIN_MYSQL_USER=<pointcoinapiremote> \
#       -e POINTCOIN_MYSQL_PASSWORD=ENC(jasypt encrypted password)  \
#
#       -v log-data-volume:/var/log/pointcoin \
#       com.upbchain.pointcoin/pointcoin-example-micro-app:1.0-SNAPSHOT
#
FROM kevinwangcy/upchain:pointcoin-app-base-latest
MAINTAINER Kevin C.Y. Wang <kevin.wang.cy@gmail.com>

# APP_ALIAS, lower case preferred, used to created user, group and installation folder etc.
ARG POINTCOINAPP_NAME=micro-example
ENV APP_ALIAS=$POINTCOINAPP_NAME

# LOG_PATH
ENV LOG_PATH=/var/log/pointcoin/$APP_ALIAS

# Jasypt Password Settings
ENV JASYPT_ENCRYPTOR_PASSWORD=youneverknow

COPY target/app.* /opt/pointcoin/$APP_ALIAS/
COPY target/*.sh /tmp/

RUN chmod +x /tmp/prepare.sh && \
    chmod +x /tmp/runit.sh && \
    sh /tmp/prepare.sh && \
    ls -alF /home/pointcoin/ && \
    mkdir /etc/service/pointcoin-$APP_ALIAS && \
    cp /tmp/runit.sh /etc/service/pointcoin-$APP_ALIAS/run && \
    rm -rf /tmp/* /var/tmp/*

# Use baseimage-docker's init system.
CMD ["/sbin/my_init"]
