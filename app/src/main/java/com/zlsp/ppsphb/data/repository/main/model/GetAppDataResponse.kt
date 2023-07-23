package com.zlsp.ppsphb.data.repository.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.zlsp.ppsphb.data.repository.authority.model.AuthorityResponse
import com.zlsp.ppsphb.data.repository.police_act.models.PoliceActResponse


@Keep
data class GetAppDataResponse(
    @SerializedName("policeAct")
    val policeActResponse: PoliceActResponse,
    @SerializedName("authority")
    val listAuthority: List<AuthorityResponse>
)





