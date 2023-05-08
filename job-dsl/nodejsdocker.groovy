job('NodeJS Docker example') {
    scm {
        git('https://github.com/saadousman/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('sdousman@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                       // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }  
    steps {
        dockerBuildAndPublish {
            repositoryName('sdousman/node-js-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockercreds')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
