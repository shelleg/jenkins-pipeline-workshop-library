def call()
{
    withDockerRegistry([ credentialsId: "dockerHub", url: "" ]){
        // version eq latest git tag
        docker.image("${env.IMAGE}").push()
        // override the latest tag
        docker.image("${env.IMAGE_LATEST}").push()
    }
}