# Intructions to Run this sample
Helm version used: 3.0.1.
Disclaimer: when this branch was pushed Helm3 was not unreleased.

Prerequisites: 
* Docker image built and pushed to a container registry.
* If you are using a private container registry, please create a secret to pull that image, eg:

```sh
kubectl create secret docker-registry regsecret --docker-server=<<docker_registry>> --docker-password=<<password>> --docker-username=iamapikey --docker-email=a@b.com
```


## Master chart approach
* Go to /chart folder and run 

```sh
helm repo update
helm dependency update
```
* Modify the faq chart values to have the correct docker image location (and valid pull secret).
* Install the charts, eg:

```sh
helm install master ./chart
```

[Go back to master](https://github.com/ammbra/helm-faq/tree/master)

