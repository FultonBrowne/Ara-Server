//add user
db.getSiblingDB('ara').createUser({user:'ara', pwd:'thepassword', roles:[{role:'readWrite',db:'ara'}]});
