pipeline {
    // Makes our pipeline run on any node
    // agent any
    agent any 

    stages  {
         stage('gradle build') {
               steps{
						sh 'chmod +x ./gradlew'
                        sh './gradlew clean build' //
                    } 
            }
        }
    }        
