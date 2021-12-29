package sources

import com.frogobox.api.news.util.NewsUrl
import response.ArticleResponse
import response.SourceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sources.remote.ApiClient
import sources.remote.ApiObserver
import sources.remote.ApiResponse
import sources.remote.NewsApiService


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

object NewsRepository : NewsDataSource {

    private val TAG = NewsRepository::class.java.simpleName

    override fun getTopHeadline(
        apiKey: String,
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: ApiResponse<ArticleResponse>
    ) {

        callback.onShowProgress()
        ApiClient
            .create<NewsApiService>(NewsUrl.BASE_URL)
            .getTopHeadline(apiKey, q, sources, category, country, pageSize, page)
            .enqueue(object : Callback<ArticleResponse> {

                override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                    callback.onHideProgress()
                    response.body()?.let { callback.onSuccess(it) }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    callback.onHideProgress()
                    callback.onFailed(500, t.localizedMessage)
                }

            })
    }

    override fun getEverythings(
        apiKey: String,
        q: String?,
        from: String?,
        to: String?,
        qInTitle: String?,
        sources: String?,
        domains: String?,
        excludeDomains: String?,
        language: String?,
        sortBy: String?,
        pageSize: Int?,
        page: Int?,
        callback: ApiResponse<ArticleResponse>
    ) {
        callback.onShowProgress()
        ApiClient
            .create<NewsApiService>(NewsUrl.BASE_URL)
            .getEverythings(
                apiKey,
                q,
                from,
                to,
                qInTitle,
                sources,
                domains,
                excludeDomains,
                language,
                sortBy,
                pageSize,
                page
            )
            .enqueue(object : Callback<ArticleResponse> {

                override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                    callback.onHideProgress()
                    response.body()?.let { callback.onSuccess(it) }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    callback.onHideProgress()
                    callback.onFailed(500, t.localizedMessage)
                }

            })
    }

    override fun getSources(
        apiKey: String,
        language: String,
        country: String,
        category: String,
        callback: ApiResponse<SourceResponse>
    ) {
        callback.onShowProgress()
        ApiClient
            .create<NewsApiService>(NewsUrl.BASE_URL)
            .getSources(apiKey, language, country, category)
            .enqueue(object : Callback<SourceResponse> {

                override fun onResponse(call: Call<SourceResponse>, response: Response<SourceResponse>) {
                    callback.onHideProgress()
                    response.body()?.let { callback.onSuccess(it) }
                }

                override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                    callback.onHideProgress()
                    callback.onFailed(500, t.localizedMessage)
                }

            })
    }

    override fun getRxTopHeadline(
        apiKey: String,
        q: String?,
        sources: String?,
        category: String?,
        country: String?,
        pageSize: Int?,
        page: Int?,
        callback: ApiResponse<ArticleResponse>
    ) {
        ApiClient
            .createRx<NewsApiService>(NewsUrl.BASE_URL)
            .getRxTopHeadline(apiKey, q, sources, category, country, pageSize, page)
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .subscribe(object : ApiObserver<ArticleResponse>() {
                override fun onSuccess(data: ArticleResponse) {
                    callback.onSuccess(data)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }
            })
    }

    override fun getRxEverythings(
        apiKey: String,
        q: String?,
        from: String?,
        to: String?,
        qInTitle: String?,
        sources: String?,
        domains: String?,
        excludeDomains: String?,
        language: String?,
        sortBy: String?,
        pageSize: Int?,
        page: Int?,
        callback: ApiResponse<ArticleResponse>
    ) {
        ApiClient
            .createRx<NewsApiService>(NewsUrl.BASE_URL)
            .getRxEverythings(
                apiKey,
                q,
                from,
                to,
                qInTitle,
                sources,
                domains,
                excludeDomains,
                language,
                sortBy,
                pageSize,
                page
            )
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .subscribe(object : ApiObserver<ArticleResponse>() {
                override fun onSuccess(data: ArticleResponse) {
                    callback.onSuccess(data)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }
            })
    }

    override fun getRxSources(
        apiKey: String,
        language: String,
        country: String,
        category: String,
        callback: ApiResponse<SourceResponse>
    ) {
        ApiClient
            .createRx<NewsApiService>(NewsUrl.BASE_URL)
            .getRxSources(apiKey, language, country, category)
            .doOnSubscribe { callback.onShowProgress() }
            .doOnTerminate { callback.onHideProgress() }
            .subscribe(object : ApiObserver<SourceResponse>() {
                override fun onSuccess(data: SourceResponse) {
                    callback.onSuccess(data)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }
            })
    }
}