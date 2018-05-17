def call() {
docker.withRegistry("https://registry.hub.docker.com", "dockerHub"){
                	// version eq latest git tag
                	docker.image("$IMAGE").push()
                	// override the latest tag
                	docker.image("${env.IMAGE_LATEST}").push()
}
