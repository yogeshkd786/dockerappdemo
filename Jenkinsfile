pipeline {
    agent {
        node {
            label 'testing'
        }
    }
    
    stages {       
        stage('Prepare') {
            steps {
                checkout([$class: 'GitSCM',
                branches: [[name: '*/master']],
                doGenerateSubmoduleConfigurations: false,
                submoduleCfg: [],
                userRemoteConfigs: [[
                    url: 'https://github.com/yogeshkd786/dockerappdemo.git']]
                ])
            }
        }
        stage ('Docker_Build') {
            steps {
                
                sh'''
                    # Build the image
                    whoami
                    pwd
                    
                    sudo docker build --file DockerFile . -t k8s-dockerappdemo
                '''
            }
        }
        stage ('Docker_Push') {
            steps {
                   withCredentials([string(credentialsId: "argocd-deploy-role", variable: 'ARGOCD_AUTH_TOKEN')]) {
                        sh '''
                        ARGOCD_SERVER="34.87.1.27"
                        APP_NAME="dockerappdemo"
                        
                        cat /tmp/aliz-tiket-sandbox-fb07afa6e061.json | sudo docker login -u _json_key --password-stdin https://gcr.io
                        sudo docker image list
                        sudo docker tag k8s-dockerappdemo gcr.io/aliz-tiket-sandbox/k8s-dockerappdemo:latest
                        sudo docker push gcr.io/aliz-tiket-sandbox/k8s-dockerappdemo:latest
                     
                        IMAGE_DIGEST=$(sudo docker image inspect gcr.io/aliz-tiket-sandbox/k8s-dockerappdemo:latest -f '{{join .RepoDigests ","}}')
                        # Customize image 
                        ARGOCD_SERVER=$ARGOCD_SERVER argocd app set $APP_NAME --kustomize-image $IMAGE_DIGEST --insecure
                        
                        # Deploy to ArgoCD
                        ARGOCD_SERVER=$ARGOCD_SERVER argocd app sync $APP_NAME --force --insecure
                        ARGOCD_SERVER=$ARGOCD_SERVER argocd app wait $APP_NAME --timeout 600 --insecure
                        '''
               }
                 
                     
                
                 
                
            }
        }
        
    }
}
