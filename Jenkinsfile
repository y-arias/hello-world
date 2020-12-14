pipeline {
  agent {
    docker {
      image 'maven:3-alpine' 
      args '-v /root/.m2:/root/.m2' 
    }
  }
  stages {
    stage("build & SonarQube analysis") {
      steps {
#        withSonarQubeEnv('Sonar') {
           sh 'mvn --version'
#          sh 'mvn clean package sonar:sonar'
#        }
      }
    }
#    stage("Quality Gate") {
#      steps {
#        timeout(time: 1, unit: 'HOURS') {
#          waitForQualityGate abortPipeline: true
#        }
#      }
#    }
  }
}