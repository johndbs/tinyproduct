pipeline{
    agent any
    tools{
        maven 'Maven3'
        jdk 'jdk-21'
    }
    environment{
        /**
        Add in the Jenkins, the following environment variables:
            NEXUS_VERSION = "nexus3"
            NEXUS_PROTOCOL = "http"
            NEXUS_URL = "nexus:8081"
            NEXUS_REPOSITORY = "java_app"

            SONAR_URL = "http://sonarqube:9000"

            DOCKER_URL = "https://index.docker.io/v1/"
            DOCKER_REPOSITORY_NAME = the Docker Hub username

        Add in the Jenkins, the following credentials:
            GITHUB_CRED = the generated credential id for the GitHub
            NEXUS_CRED = the generated credential id for the Nexus repository
            SONAR_CRED = the generated credential id for the SonarQube
            DOCKER_CRED = the generated credential id for the Docker Hub
        */


        GIT_URL = 'https://github.com/johndbs/tinyproduct'
        GIT_BRANCH_NAME = "${env.BRANCH_NAME}"


        MVN_SETTINGS_FILE_NAME = "mvn-nexus-settings"
        MVN_REPOSITORY_ID = "nexus"



        SONAR_PROJECT_KEY = 'tinyproduct'
        SONAR_PROJECT_NAME = 'tinyproduct'

        DOCKER_ARTIFACT_NAME = 'tinyproduct'
    }



    stages{
        stage('Checkout'){
            steps{
                sh 'echo "Step 1 : Checkout the repository"'
                /*
                checkout scmGit(
                    branches: [[name: "${GIT_BRANCH_NAME}"]],
                    userRemoteConfigs: [[url: "${GIT_URL}",
                        credentialsId: 'GITHUB_CRED']]
                )
                */
                checkout scm

            }
        }
        stage('Build'){
            steps{
                sh 'echo "Step 2 : Building the application"'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test'){
            steps{
                sh 'echo "Step 3 : Testing the application"'
                sh 'mvn verify'
            }
        }
        stage('Sonar'){
            steps{
                sh 'echo "Step 4 : Running SonarQube analysis"'
                withCredentials([string(credentialsId: 'SONAR_CRED', variable: 'SONAR_TOKEN')]){
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONAR_URL} \
                        -Dsonar.login=${SONAR_TOKEN} \
                        -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                        -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    """
                }


            }
        }

        stage('Publish to Nexus'){
            steps{

                script{
                    sh 'echo "Step 5 : Publishing the application to Nexus"'

                    def pomGroupId = sh(script: 'mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout', returnStdout: true).trim()
                    def pomArtifactId = sh(script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true).trim()
                    def pomVersion = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true).trim()
                    def pomPackaging = sh(script: 'mvn help:evaluate -Dexpression=project.packaging -q -DforceStdout', returnStdout: true).trim()


                    sh "echo 'groupId: ${pomGroupId}'"
                    sh "echo 'artifactId: ${pomArtifactId}'"
                    sh "echo 'version: ${pomVersion}'"
                    sh "echo 'packaging: ${pomPackaging}'"

                    configFileProvider(
                        [configFile(fileId: "${env.MVN_SETTINGS_FILE_NAME}", variable: 'MAVEN_SETTINGS')]) {
                            withCredentials([usernamePassword(credentialsId: 'NEXUS_CRED', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                                sh "echo 'NEXUS_USERNAME: ${NEXUS_USERNAME}'"
                                sh """
                                mvn -s $MAVEN_SETTINGS deploy:deploy-file \
                                -Durl=${NEXUS_PROTOCOL}://${NEXUS_URL}/repository/${NEXUS_REPOSITORY}/ \
                                -DrepositoryId=${MVN_REPOSITORY_ID} \
                                -Dfile=target/${pomArtifactId}-${pomVersion}.${pomPackaging} \
                                -DgroupId=${pomGroupId} \
                                -DartifactId=${pomArtifactId} \
                                -Dversion=${pomVersion} \
                                -Dpackaging=${pomPackaging}
                                """
                            }

                    }


                }


            }
        }

        stage('Docker build') {
            steps {
                script {
                    sh 'echo "Step 6 : Building the Docker image"'
                    withDockerRegistry(toolName: 'docker', credentialsId: 'DOCKER_CRED', url: "${DOCKER_URL}") {
                        sh "docker build -t ${env.DOCKER_ARTIFACT_NAME}:latest ."
                        sh "docker tag ${env.DOCKER_ARTIFACT_NAME}:latest ${DOCKER_REPOSITORY_NAME}/${env.DOCKER_ARTIFACT_NAME}:latest"
                        sh "docker push ${DOCKER_REPOSITORY_NAME}/${env.DOCKER_ARTIFACT_NAME}:latest"
                    }
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    sh 'echo "Step 7 : Pushing the Docker image to Docker Hub"'
                    withDockerRegistry(toolName: 'docker', credentialsId: 'DOCKER_CRED', url: "${DOCKER_URL}") {
                        sh "docker push ${DOCKER_REPOSITORY_NAME}/${env.DOCKER_ARTIFACT_NAME}:latest"
                    }
                }
            }
        }

    }

    post {
        always {
            echo 'Cleaning up workspace'
            cleanWs()
        }
        success {
            echo 'Build SUCCEDDED'

        }
        failure {
            echo 'Build FAILED.'
        }
    }

}
