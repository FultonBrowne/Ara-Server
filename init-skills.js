//add user
let ara = db.getSiblingDB('ara')
ara.createUser(
   {
      user:'ara',
      pwd:'thepassword',
      roles:[
         {
            role:'readWrite',
            db:'ara'
         }
      ]
   }
);
//add search data
let data = []
for (i of data){
   let toInsert = {
      "_id": Math.round(Math.random()).toString()
      "document": i
   }
   ara.search.insertOne(toInsert)
}
