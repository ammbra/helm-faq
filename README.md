# Intructions to Run this sample

Prerequisites: 
* Docker image pushed to a container registry.
* If you are using a private container registry, please create a secret to pull that image, eg:

```sh
kubectl create secret docker-registry regsecret --docker-server=<<docker_registry>> --docker-password=<<password>> --docker-username=iamapikey --docker-email=a@b.com
```


## One chart per service approach

* Install postgress helm chart, eg:

```sh
helm install --name db --set postgresqlUsername=postgres,postgresqlPassword=postgres,postgresqlDatabase=faq_demo,persistence.enabled=false stable/postgresql
```

* Modify the faq chart values or values.dev.yaml to have the correct docker image location (and valid pull secret).
* Install the faq chart, eg:

```sh
helm install --name=simple ./faq
```