pipeline {
  agent none
  stages {
    stage("build & SonarQube analysis") {
      agent any
      steps {
        withSonarQubeEnv('sonar') {
          sh 'mvn clean package sonar:sonar'
        }
      }
    }
    stage("Quality Gate") {
      steps {
        timeout(time: 1, unit: 'HOURS') {
          waitForQualityGate abortPipeline: true
        }
      }
    }
  }
}