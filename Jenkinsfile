pipeline {
  agent {
    docker {
      image 'maven:3.6.1-jdk-8-alpine'
      args '-v ${HOME}/mvn/settings.xml:/usr/share/maven/ref/settings.xml' 
    }
  }
  stages {
    stage("build & SonarQube analysis") {
      steps {
        withSonarQubeEnv('Sonar') {
          sh 'mvn --version'
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
