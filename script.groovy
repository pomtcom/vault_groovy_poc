import groovy.json.JsonSlurper

print 'vault groovy poc is starting'


node {

    stage('get role_id') {
        print 'getting role_id'
    }

    stage('create secret_id'){
        print 'creating secret_id'

        // POST
    def post = new URL("http://10.198.105.221:8200/v1/auth/approle/role/vault_poc_role/secret-id").openConnection();
    def message = '{"message":"this is a message"}'
    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("X-Vault-Token", "s.3aVA6ckaOumc6N7WHTZHZ34a")
    post.getOutputStream().write(message.getBytes("UTF-8"));
    // println(postRC);
    if(post.getResponseCode().equals(200)) {
        println(post.getInputStream().getText());
        // JSONObject responseJson = JSON.parse(post.getInputStream().getText());
        // def secret_id = responseJson['data']['secret_id'];
        def slurped = new JsonSlurper().parseText(post.getInputStream().getText())
        print('secret_id is ' + secret_id['data']);
    }else{
        println('http error response code ' + post.getResponseCode());
    }


    }

    stage('generate role_token'){
        print 'generating role_token'
    }

    
}