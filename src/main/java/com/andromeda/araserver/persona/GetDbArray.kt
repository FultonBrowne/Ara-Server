package com.andromeda.araserver.persona

import com.andromeda.araserver.skills.SkillsModel
import com.andromeda.araserver.util.CosmosClients
import com.andromeda.araserver.util.DatabaseClient
import com.andromeda.araserver.util.OutputModel
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.gson.Gson
import com.microsoft.azure.documentdb.FeedOptions
import com.microsoft.azure.documentdb.PartitionKey
import org.json.JSONObject
import java.util.*

class GetDbArray {
    fun likes(search: String, cc: Locale): String? {
        return DatabaseClient().getInLikesForm("level", search, cc)
    }
}