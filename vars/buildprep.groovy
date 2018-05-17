def call(String buildID, String project){
  // // calculate GIT lastest commit short-hash
  // gitCommitHash = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
  // shortCommitHash = gitCommitHash.take(7)
  // // calculate a sample version tag
  // VERSION = shortCommitHash
  // // set the build display name
  // currentBuild.displayName = "#${buildID}-${VERSION}"
  // IMAGE = "$project:$VERSION"
  // IMAGE_LATEST = "$project:latest"
}
