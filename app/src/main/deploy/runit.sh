#!/bin/sh
set -e

exec chpst -u $APP_ALIAS /opt/pointcoin/$APP_ALIAS/app.jar

