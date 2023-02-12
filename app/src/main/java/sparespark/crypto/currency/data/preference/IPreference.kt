package sparespark.crypto.currency.data.preference

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

const val LAST_FETCHED_TIME = "LAST_FETCHED_TIME"

@RequiresApi(Build.VERSION_CODES.O)
@Singleton
class IPreference @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)


    fun updateLastFetchedTime(query: String) {
        prefs.edit().putString(LAST_FETCHED_TIME, query).apply()
    }

    private fun getLastFetchedTime(): String? {
        return prefs.getString(LAST_FETCHED_TIME, null)
    }

    fun lastFetchedIsSixHoursAgo(): Boolean {
        if (getLastFetchedTime() == null) return true
        return try {
            val sixHoursAgo = LocalDateTime.now(ZoneId.systemDefault()).minusHours(6)
            val fetchedTime = LocalDateTime.parse(getLastFetchedTime())
            fetchedTime.isBefore(sixHoursAgo)
        } catch (ex: Exception) {
            true
        }
    }
}