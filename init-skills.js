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
for (let i in data){
   let toInsert = {
      "_id": "hvhjvkguk"
      "document": i
   }
   ara.search.insertOne(toInsert)
}
