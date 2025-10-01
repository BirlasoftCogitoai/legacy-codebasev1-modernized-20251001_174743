```bash
#!/bin/bash

# Modern EGP Application Build Script
# Supports both JBoss AS 7.x and WebLogic 12c deployments

set -euo pipefail

# Configuration
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
BUILD_DIR="$PROJECT_ROOT/target"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Logging function
log() {
    echo -e "${GREEN}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

warn() {
    echo -e "${YELLOW}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

error() {
    echo -e "${RED}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
    exit 1
}

# Build function
build_project() {
    log "Starting build process..."
    mvn clean install || error "Maven build failed"
    log "Maven build completed successfully"
}

# Deploy function
deploy_project() {
    local server=$1
    log "Deploying to ${server} server..."
    if [ "${server}" == "JBoss" ]; then
        scp target/egp-portal.war user@jboss-server:/path/to/deploy || error "Deployment to JBoss failed"
    elif [ "${server}" == "WebLogic" ]; then
        scp target/egp-portal.war user@weblogic-server:/path/to/deploy || error "Deployment to WebLogic failed"
    else
        error "Unknown server: ${server}"
    fi
    log "Deployment to ${server} completed successfully"
}

# Main script execution
build_project

# Deployment prompt
read -p "Deploy to which server? (JBoss/WebLogic): " server
deploy_project "${server}"
```