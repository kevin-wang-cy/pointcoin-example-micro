#!/bin/sh

# create user $APP_ALIAS:$APP_ALIAS
addgroup --gid 9999 $APP_ALIAS
adduser --uid 9999 --gid 9999 --disabled-password --gecos "$APP_ALIAS" $APP_ALIAS
usermod -L $APP_ALIAS

# mkdir .$APP_ALIAS
mkdir -p /home/pointcoin/.$APP_ALIAS
chmod 700 /home/pointcoin/.$APP_ALIAS
chown $APP_ALIAS:$APP_ALIAS /home/pointcoin/.$APP_ALIAS

# mkdir /var/log/pointcoin/$APP_ALIAS
mkdir -p /var/log/pointcoin/$APP_ALIAS
chmod 700 /var/log/pointcoin/$APP_ALIAS
chown -R $APP_ALIAS:$APP_ALIAS /var/log/pointcoin/$APP_ALIAS
