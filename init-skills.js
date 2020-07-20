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
let data = [
      {
         "url": "SELFv1/skills/weather",
         "data":{
            "word": "weather",
            "type": {
               "dep": "nsubj",
               "pos": "NOUN",
               "tag": "NNS",
               "lemma": "weather"
            }
         }
      },
      {
         "url": "SELFv1/skills/timer",
         "data":{
            "word": "timer",
            "type": {
               "dep": "dobj",
               "pos": "NOUN",
               "tag": "NNS",
               "lemma": "timer"
            }
         }
      },
      {
         "url": "",
         "data":{
            "word": "call",
            "type": {
               "dep": "ROOT",
               "pos": "VERB",
               "tag": "VB",
               "lemma": "call"
            }
         }
      },
      {
         "url": "",
         "data":{
            "word": "text",
            "type": {
               "dep": "ROOT",
               "pos": "NOUN",
               "tag": "NN",
               "lemma": "text"
            }
         }
      },
      {
         "url": "",
         "data":{
            "word": "text",
            "type": {
               "dep": "compound",
               "pos": "NOUN",
               "tag": "NNS",
               "lemma": "text"
            }
         }
      }

   ]

for (i of data){
   let toInsert = {
      "_id": Math.round(Math.random() * 10000000).toString(),
      "document": i
   }
   ara.search.insertOne(toInsert)
}
