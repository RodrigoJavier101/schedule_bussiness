package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.retrofit

data class QuotePhrase(
    var success: Total,
    var contents: Quotes,
    var baseurl: String,
    var copyright: Year_Url
)

data class Total(
    var total: Int
)

data class Quotes(
    var quote: String,
    var length: String,
    var author: String,
    var tags: List<String>,
    var category: String,
    var language: String,
    var date: String,
    var permalink: String,
    var id: String,
    var background: String,
    var title: String
)

data class Year_Url(
    var year: Int,
    var url: String
)