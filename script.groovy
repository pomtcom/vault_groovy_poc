import groovy.json.JsonSlurper

print 'vault groovy poc is starting'
def role_id = params.role_id

node {

    stage('get role_id') {
        print 'getting role_id'
        print 'role id is ' + role_id
    }

    stage('create secret_id'){
        print 'creating secret_id'
        // POST
        def post = new URL("http://10.198.105.221:8200/v1/auth/approle/role/vault_poc_role/secret-id").openConnection();
        def message = '{}'
        post.setRequestMethod("POST")
        post.setDoOutput(true)
        post.setRequestProperty("X-Vault-Token", "s.3aVA6ckaOumc6N7WHTZHZ34a")
        post.getOutputStream().write(message.getBytes("UTF-8"));
        // println(postRC);
        if(post.getResponseCode().equals(200)) {
            def jsonResponse = post.getInputStream().getText() ;
            def jsonSlurped = new JsonSlurper().parseText(jsonResponse);
            
            // to add try catch for accessing json
            def secret_id = jsonSlurped['data']['secret_id'];
            print('secret_id is ' + secret_id)

        }else{
            println('http error response code ' + post.getResponseCode());
        }
    }

    stage('generate role_token'){
        print 'generating role_token'
        print 'secret_id is ' + secret_id
        def post = new URL("http://10.198.105.221:8200/v1/auth/approle/login").openConnection();
        def message = '{"role_id": "' + role_id + '",' + '"secret_id": "' + secret_id + '"}';
        post.setRequestMethod("POST");
        post.setDoOutput(true);
        print('message to send is ' + message);
    }

    
}