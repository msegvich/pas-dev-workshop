There are three options for running this example.

1. Cloud Foundry (Pivotal Application Service) using the Python buildpack
From within the src folder run the following:
`cf push <APP-NAME> -c "python app.py"`

2. Cloud Foundry (Pivotal Application Service) using docker image
#### Build the Image
From within the src directory
`docker build -t <$DOCKER_ACCOUNT/$DOCKER_REPO:$IMG_TAG> .`
`docker push <TAG>`
#### Push It
`cf push <APP-NAME> --docker-image <$DOCKER_ACCOUNT/$DOCKER_REPO:$IMG_TAG>`

3. Kubernetes such as using PKS (Pivotal Container Service)
Follow the steps for building the image described above.
Execute the following:
`kubectl create deployment --image=$DOCKER_ACCOUNT/$DOCKER_REPO:$IMG_TAG`
`kubectl expose deployment <DEPLOYMENT_NAME> --type=LoadBalancer --name=pa-lb --port=5000`
