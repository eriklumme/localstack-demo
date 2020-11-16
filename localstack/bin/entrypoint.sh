#!/usr/bin/env bash

printf "Running 'entrypoint.sh'"

readonly REGION=eu-west-2
readonly ENDPOINT_URL=http://localhost:4566
readonly FILE_BUCKET=file-bucket
readonly FROM_EMAIL=from@example.com

set -x

aws configure set default.region $REGION
aws configure set aws_access_key_id test
aws configure set aws_secret_access_key test
aws configure set default.output json

# A S3 bucket for testing
aws s3api create-bucket --bucket $FILE_BUCKET --endpoint $ENDPOINT_URL
# Verifies an e-mail address, so that we can "send" e-mails from this address
aws ses verify-email-identity --email-address $FROM_EMAIL --endpoint-url=$ENDPOINT_URL
