package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import kotlin.random.Random

class HelloThings {
    val hello1 = OutputModel(
        "hi",
        "hi",
        "hi",
        "https://arafilestore.file.core.windows.net/ara-server-files/pablo-gentile-3MYvgsH1uK0-unsplash.jpg?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D",
        "Hello Human",
        ""
    )
    val hello2 = OutputModel(
        "hey, there",
        "hi",
        "hi",
        "https://arafilestore.file.core.windows.net/ara-server-files/pablo-gentile-3MYvgsH1uK0-unsplash.jpg?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D",
        "Hey, there",
        ""
    )
    val hello3 = OutputModel(
        "hello, how can I help?",
        "hi",
        "hi",
        "https://arafilestore.file.core.windows.net/ara-server-files/pablo-gentile-3MYvgsH1uK0-unsplash.jpg?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D",
        "hello, how can I help?",
        ""
    )
    val list = listOf(hello1, hello2, hello3).shuffled(Random(1))
}