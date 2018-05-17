def call()
{
    // Build the docker image using a Dockerfile
    docker.build("${env.IMAGE}")
    // override the latest tag
    docker.build("${env.IMAGE_LATEST}")
}