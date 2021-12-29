import com.frogobox.api.news.util.NewsConstant
import com.frogobox.api.news.util.NewsUrl
import sources.remote.ApiResponse
import response.ArticleResponse
import sources.ConsumeNewsApi

/*
 * Created by faisalamir on 23/12/21
 * coroutines-plyaground
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

fun main() {
    val consumeNewsApi = ConsumeNewsApi(NewsUrl.API_KEY) // Your API_KEY
    consumeNewsApi.getRxEverythings( // Adding Base Parameter on main function
        NewsConstant.CATEGORY_HEALTH,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        object : ApiResponse<ArticleResponse> {
            override fun onSuccess(data: ArticleResponse) {
                for (i in data.articles?.indices!!) {
                    println("${i + 1}.\t ${data.articles?.get(i)?.title}")
                }
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                // Your failed to do
                println("Failed")
            }

            override fun onShowProgress() {
                // Your Progress Show
                println("Show Progress")
            }

            override fun onHideProgress() {
                // Your Progress Hide
                println("Hide Progress")
            }

        })
}