print 'vault groovy poc is starting'


node {

    stage('get role_id') {
        print 'getting role_id'
    }

    stage('create secret_id'){
        print 'creating secret_id'
    }

    stage('generate role_token'){
        print 'generating role_token'
    }

    
}