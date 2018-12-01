#!/usr/bin/env bash

if [[ -n $COMMON_LOADED ]]; then
  # Already loaded
  return 0
fi

set -euo pipefail

## Variables
declare -r DEPENDENCIES_FILE=.deps.txt

## Helpers

function log() {
  echo "$@" >&2
}

function error() {
  log "[ERROR] $@"
}

function info() {
  log "[INFO] $@"
}

function die() {
  error "$1"
  return ${2:-255}
}

if [[ ${BASH_SOURCE[0]} != $0 ]]; then
  export -f error info die
else
  die "Incorrect usage.  This file should be sourced!" 254
fi

declare -r COMMON_LOADED=true

# common.sh
