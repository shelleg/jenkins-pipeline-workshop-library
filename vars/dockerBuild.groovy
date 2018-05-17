def call() {
  // Build the docker image using a Dockerfile
  docker.build("$IMAGE")
  // override the latest tag
  docker.build("${env.IMAGE_LATEST}")
}
