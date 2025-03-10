#!/bin/bash
echo "${PLAY_ACCOUNT_AS_BASE64}" | base64 --decode > android/app/play-account.json
echo "${KEYSTORE_AS_BASE64}" | base64 --decode > android/app/keystore.jks
echo "${GOOGLE_SERVICES_FILE_AS_BASE64}" | base64 --decode > android/app/google-services.json
