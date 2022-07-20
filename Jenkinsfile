pipeline {
    // Makes our pipeline run on any node
    // agent any
    agent any 

    stages  {
         stage('gradle build') {
               steps{
                        sh './gradlew clean build --no-daemon' //run a gradle task
                    } 
            }
        }
    }        
