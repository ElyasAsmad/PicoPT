package com.elyasasmad.my.prayertimes.data.remote

object HttpRoutes {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    const val POSTS = "$BASE_URL/posts"

//    const val ESOLAT_URL = "https://www.e-solat.gov.my/index.php?r=esolatApi/TakwimSolat&period=today&zone=JHR04"

    const val PICOSOLAT_URL = "https://picosolat.krypton.my/api/waktusolat/daily/JHR04"

}