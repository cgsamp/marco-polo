kubectl create secret docker-registry gcp-artfact-repository \
  --docker-server="https://us-central1-docker.pkg.dev" \
  --docker-username=_json_key \
  --docker-email=[service account email] \
  --docker-password="$(cat secrets/registry-readonly-serviceaccount.json)"
