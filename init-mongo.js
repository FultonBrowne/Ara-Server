db.createUser(
        {
            user: "ara",
            pwd: "thepassword",
            roles: [
                {
                    role: "readWrite",
                    db: "ara"
                }
            ]
        }
)
db.createCollection("test");
