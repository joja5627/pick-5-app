export CONFG_SERVER_PORT="8888"
export EUREKA_SERVER_PORT="8761"
export ZUUL_SERVER_PORT="8080"

export REGISTRATION_APP_PORT="8003"
export USER_APP_PORT="8002"

echo "CONFG_SERVER_PORT="$CONFG_APP_PORT >> "./docker-compose/.env"
echo "EUREKA_SERVER_PORT="$EUREKA_SERVER_PORT >>  "./docker-compose/.env"
echo "ZUUL_SERVER_PORT="$ZUUL_ZERVER_PORT >> "./docker-compose/.env"
echo "REGISTRATION_APP_PORT="$REGISTRATION_APP_PORT >> "./docker-compose/.env"
echo "USER_APP_PORT="$USER_APP_PORT >> "./docker-compose/.env"