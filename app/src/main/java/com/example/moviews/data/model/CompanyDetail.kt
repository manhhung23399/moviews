package com.example.moviews.data.model

import org.json.JSONObject

data class CompanyDetail(
    val company: Company,
    val homePage: String,
    val headquarter: String,
    val country: String
) {
    constructor(jsonObject: JSONObject) : this(
        company = Company(jsonObject),
        homePage = jsonObject.getString(COMPANY_HOME_PAGE),
        headquarter = jsonObject.getString(COMPANY_HEADQUARTER),
        country = jsonObject.getString(COMPANY_COUNTRY)
    )

    companion object {
        const val COMPANY_HOME_PAGE = "homepage"
        const val COMPANY_HEADQUARTER = "headquarters"
        const val COMPANY_COUNTRY = "origin_country"
    }
}
