#!/usr/bin/env bash
# Levanta todo el proyecto en GitHub Codespaces:
#   1) MySQL 8 en Docker   2) Backend Spring Boot (8080)   3) Frontend Angular (4200)
# Uso:  bash iniciar.sh
set -e
RAIZ="$(cd "$(dirname "$0")" && pwd)"

echo "== 1) MySQL en Docker =="
if docker ps -a --format '{{.Names}}' | grep -qx mysql-alumnos; then
  docker start mysql-alumnos >/dev/null
else
  docker run --name mysql-alumnos \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=alumnosdb \
    -p 3306:3306 \
    -d mysql:8
fi
until docker exec mysql-alumnos mysql -uroot -proot -e "SELECT 1" alumnosdb >/dev/null 2>&1; do sleep 2; done
echo "MySQL listo"

echo "== 2) Backend Spring Boot =="
cd "$RAIZ/backend"
chmod +x mvnw
pkill -f "spring-boot:run" 2>/dev/null || true
nohup ./mvnw spring-boot:run > "$RAIZ/backend.log" 2>&1 &
until grep -q "Started AlumnosApplication" "$RAIZ/backend.log"; do
  if grep -q "BUILD FAILURE\|APPLICATION FAILED" "$RAIZ/backend.log"; then
    echo "Error en el backend, revise backend.log"; exit 1
  fi
  sleep 3
done
echo "Backend en http://localhost:8080/api/alumnos"

# El puerto 8080 debe ser público para que Angular (otro origen) lo consuma
if [ -n "$CODESPACE_NAME" ]; then
  gh codespace ports visibility 8080:public -c "$CODESPACE_NAME" >/dev/null 2>&1 || true
fi

echo "== 3) Frontend Angular =="
cd "$RAIZ/frontend"
[ -d node_modules ] || npm install
pkill -f "ng serve" 2>/dev/null || true
nohup npx ng serve --host 0.0.0.0 --port 4200 > "$RAIZ/frontend.log" 2>&1 &
until grep -q "Local:" "$RAIZ/frontend.log"; do sleep 3; done
echo "Frontend en el puerto 4200 (pestaña Ports > 4200 > abrir en el navegador)"
