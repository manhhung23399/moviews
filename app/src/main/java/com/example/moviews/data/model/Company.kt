package com.example.moviews.data.model

import org.json.JSONObject

data class Company(
    val id: Int,
    val logo: String,
    val name: String
) {
    constructor(jsonObject: JSONObject) : this(
        id = jsonObject.getInt(COMPANY_ID),
        logo = jsonObject.getString(COMPANY_LOGO),
        name = jsonObject.getString(COMPANY_NAME)
    )

    companion object {
        const val COMPANY = "production_companies"
        const val COMPANY_ID = "id"
        const val COMPANY_LOGO = "logo_path"
        const val COMPANY_NAME = "name"
    }
}
