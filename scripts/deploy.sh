```bash
#!/bin/bash

# Modernized EGP Application Deployment Script
# Supports JBoss AS 7.x and WebLogic 12c

set -euo pipefail

# Configuration
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
EAR_FILE="$PROJECT_ROOT/egp-ear/target/egp-ear-1.0.0-SNAPSHOT.ear"

# Environment configurations using associative arrays
declare -A JBOSS_CONFIGS=(
    [dev]="localhost:9999"
    [test]="egp-test.internal.corp:9999"
    [prod]="egp-prod.internal.corp:9999"
)

declare -A WEBLOGIC_CONFIGS=(
    [dev]="t3://localhost:7001"
    [test]="t3://egp-test.internal.corp:7001"
    [prod]="t3://egp-prod.internal.corp:7001"
)

# Logging function
log() {
    local LEVEL=$1
    shift
    local TIMESTAMP
    TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")
    echo "[$TIMESTAMP] [$LEVEL] $*"
}

# Usage function
usage() {
    cat <<EOF
Usage: $(basename "$0") [OPTIONS]

Options:
  -e, --env ENV     Specify the environment (dev, test, prod)
  -s, --server SERVER     Specify the server type (jboss, weblogic)
  -h, --help        Show this help message and exit

Examples:
  $(basename "$0") --env dev --server jboss
EOF
    exit 1
}

# Parsing arguments
ENV=""
SERVER=""

while [[ $# -gt 0 ]]; do
    case "$1" in
        -e|--env)
            ENV="$2"
            shift 2
            ;;
        -s|--server)
            SERVER="$2"
            shift 2
            ;;
        -h|--help)
            usage
            ;;
        *)
            log "ERROR" "Unknown option: $1"
            usage
            ;;
    esac
done

# Validate arguments
if [[ -z "$ENV" || -z "$SERVER" ]]; then
    log "ERROR" "Environment and server type must be specified."
    usage
fi

if [[ ! "${JBOSS_CONFIGS[$ENV]+_}" && ! "${WEBLOGIC_CONFIGS[$ENV]+_}" ]]; then
    log "ERROR" "Invalid environment: $ENV"
    usage
fi

deploy_to_jboss() {
    local HOST_PORT=${JBOSS_CONFIGS[$ENV]}
    log "INFO" "Deploying to JBoss at $HOST_PORT"
    # Example deployment command (update to match your setup)
    curl --fail -F "file=@$EAR_FILE" "http://$HOST_PORT/management-upload"
}

deploy_to_weblogic() {
    local SERVER_URL=${WEBLOGIC_CONFIGS[$ENV]}
    log "INFO" "Deploying to WebLogic at $SERVER_URL"
    # Example deployment command (update to match your setup)
    java weblogic.Deployer -adminurl $SERVER_URL -username myuser -password mypassword -deploy $EAR_FILE
}

# Main deployment logic
case "$SERVER" in
    jboss)
        deploy_to_jboss
        ;;
    weblogic)
        deploy_to_weblogic
        ;;
    *)
        log "ERROR" "Unknown server type: $SERVER"
        usage
        ;;
esac

log "INFO" "Deployment successful"
```