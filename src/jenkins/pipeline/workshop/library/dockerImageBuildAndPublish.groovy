package jenkins.pipeline.workshop.library

class dockerImageBuildAndPublish implements Serializable {

    def script
    def image
    def imageLatest

    dockerImageBuildAndPublish(script) {
        this.script = script
    }

    void run() {
        script.timestamps() {
            runImpl()
        }
    }

    void runStage(String name, Closure stage) {
        script.echo "--- Start stage [$name] ---"
        script.stage(name, stage)
        script.echo "--- End stage [$name] ---"
    }

    void runImpl() {
        runStage('Setup', this.&setup)
        runStage('Build', this.&build)
        runStage('Publish', this.&publish)
    }

    void setup() {
        def gitCommitHash = script.sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
        def shortCommitHash = gitCommitHash.take(7)
        // calculate a sample version tag
        def VERSION = shortCommitHash
        // set the build display name
        script.currentBuild.displayName = "#${script.BUILD_ID}-${VERSION}"
        image = "${script.PROJECT}:${VERSION}"
        imageLatest = "${script.PROJECT}:latest"
    }

    void build() {
        // Build the docker image using a Dockerfile
        script.docker.build(image)
        // override the latest tag
        script.docker.build(imageLatest)
    }

    void publish() {
        script.withDockerRegistry([ credentialsId: "dockerHub", url: "" ]){
            // version eq latest git tag
            script.docker.image(image).push()
            // override the latest tag
            script.docker.image(imageLatest).push()
        }
    }
}
