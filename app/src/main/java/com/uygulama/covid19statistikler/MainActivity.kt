package com.uygulama.covid19statistikler

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var dunyaVaka:TextView
    lateinit var dunyaKurtulan:TextView
    lateinit var dunyaOlum:TextView
    lateinit var ulkeVaka:TextView
    lateinit var ulkeKurtulan:TextView
    lateinit var ulkeOlum:TextView
    lateinit var ulkeVakaBugün:TextView
    lateinit var ulkeKurtulanBugün:TextView
    lateinit var ulkeOlumBugün:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dunyaVaka = findViewById(R.id.dunyaVaka)
        dunyaOlum = findViewById(R.id.dunyaOlum)
        dunyaKurtulan = findViewById(R.id.dunyaKurtulan)
        ulkeVaka = findViewById(R.id.ulkeVaka)
        ulkeKurtulan = findViewById(R.id.ulkeKurtulan)
        ulkeOlum = findViewById(R.id.ulkeOlum)
        ulkeVakaBugün = findViewById(R.id.bugünVaka)
        ulkeKurtulanBugün = findViewById(R.id.bugünKurtulan)
        ulkeOlumBugün = findViewById(R.id.bugünOlen)


        getDunya()
        getTurkey()
    }

    fun getDunya() {
        val myUrl = "https://disease.sh/v3/covid-19/all"
        val myRequest = StringRequest(
            myUrl,
            { response ->
                try {

                    val myJsonObject = JSONObject(response)


                    val dunyaVakax:Int = myJsonObject.getInt("cases")
                    val dunyaOlumx:Int = myJsonObject.getInt("deaths")
                    val dunyaKurtulanx:Int =myJsonObject.getInt("recovered")

                    dunyaVaka.text = dunyaVakax.toString()
                    dunyaOlum.text = dunyaOlumx.toString()
                    dunyaKurtulan.text = dunyaKurtulanx.toString()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { }
        )


        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(myRequest)



    }


    fun getTurkey(){

        val myUrl = "https://disease.sh/v3/covid-19/countries/tr"
        val myRequest = StringRequest(
            myUrl,
            { response ->
                try {

                    val myJsonObject = JSONObject(response)


                    val ulkevaka:Int = myJsonObject.getInt("cases")
                    val ulkeolum:Int = myJsonObject.getInt("deaths")
                    val ulkekurtulan:Int =myJsonObject.getInt("recovered")

                    val ulkevakabugun:Int = myJsonObject.getInt("todayCases")
                    val ulkeolumbugun:Int = myJsonObject.getInt("todayDeaths")
                    val ulkekurtulanbugun:Int = myJsonObject.getInt("todayRecovered")

                    ulkeVaka.text = ulkevaka.toString()
                    ulkeOlum.text = ulkeolum.toString()
                    ulkeKurtulan.text = ulkekurtulan.toString()

                    ulkeVakaBugün.text = ulkevakabugun.toString()
                    ulkeKurtulanBugün.text = ulkekurtulanbugun.toString()
                    ulkeOlumBugün.text = ulkeolumbugun.toString()



                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { }
        )


        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(myRequest)

    }

}