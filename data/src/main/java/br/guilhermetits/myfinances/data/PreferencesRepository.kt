package br.guilhermetits.myfinances
        .data

import android.content.SharedPreferences
import br.guilhermetits.common.fromJson
import br.guilhermetits.common.toJson
import com.google.gson.Gson

class PreferencesRepository(val sharedPreferences: SharedPreferences,
                            val gson: Gson) {

    fun <T : Any> putValue(key: String, value: T) {
        sharedPreferences.edit()
                .putString(key, value.toJson(gson))
                .apply()
    }

    inline fun <reified T : Any> getValue(key: String): T? {
        return sharedPreferences.getString(key, "")
                .takeIf { !it.isEmpty() }
                ?.fromJson(gson)
    }

    inline fun <reified T : Any> getValue(key: String, default: T): T {
        return this.getValue(key) ?: default
    }
}