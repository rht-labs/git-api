# Development on OpenShift

## Getting Started With Helm

This directory contains a Helm chart which can be used to deploy a development version of this app for rapid testing.

Before you use it, you will need to download & install Helm 3.

If you are not familiar with Helm - how to configure it and run - you can start with this quickstart:

[https://helm.sh/docs/intro/quickstart](https://helm.sh/docs/intro/quickstart)

## Using This Chart

1. Clone the target repo:

```
git clone https://github.com/rht-labs/lodestar-git-api
```

2. Change into to the `deployment` directory:

```
cd lodestar-git-api/deployment
```

3. Deploy using the following Helm command:

```shell script
helm template . \
  --values values-dev.yaml \
  --set git.uri=https://github.com/rht-labs/lodestar-git-api.git \
  --set git.ref=master \
  --set configRepositoryId=<your-config-repository-id> \
  --set deployKey=<your-gitlab-deploy-key-id> \
  --set gitLabApiUrl=<your-gitlab-base-url> \
  --set gitLabPersonalAccessToken=<your-gitlab-personal-access-token> \
  --set engagementsRepositoryId=<your-gitlab-group-id> \
| oc apply -f -
```

It accepts the following variables

| Variable  | Use  |
|---|---|
| `git.uri`  | The HTTPS reference to the repo (your fork!) to build  |
| `git.ref`  | The branch name to build  |
| `configRepositoryId`  | The GitLab ID of the config repository  |
| `deployKey`  | The ID of the GitLab deploy key to enable on newly-created repositories  |
| `gitLabApiUrl`  | The base URL of the GitLab instance to use  |
| `gitLabPersonalAccessToken`  | The access token to use to auth against GitLab  |
| `engagementsRepositoryId`  | The ID of the GitLab group under which to create new projects  |

This will spin up all of the usual resources that this service needs in production, plus a `BuildConfig` configured to build it from source from the Git repository specified. To trigger this build, use `oc start-build omp-git-api`.

## Config Map Configurations

Please note that there is a configuration for both, webhooks and the runtime configuration.  These are provided as samples or subsets and will not be kept current with the values configured in the test/integration/production environments.

These ConfigMaps have already been configured and provided when deployed into the official OpenShift environments.  
