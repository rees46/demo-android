#!/bin/bash
echo "${PLAY_ACCOUNT_AS_BASE64}" | base64 --decode > ../app/play-account.json
echo "${KEYSTORE_AS_BASE64}" | base64 --decode > ../app/keystore.jks
echo "${GOOGLE_SERVICES_FILE_AS_BASE64}" | base64 --decode > ../app/google-services.json
