#!/usr/bin/env bash
tar -xzvf website.tar.gz

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion
nvm install node
npx http-server website > access.log &
chmod +x ./gradlew 
./gradlew run --args="-u  http://localhost:8080/kayako.com"

