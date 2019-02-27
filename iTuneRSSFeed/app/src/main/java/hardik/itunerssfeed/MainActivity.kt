package hardik.itunerssfeed

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import android.support.v7.widget.GridLayoutManager



class MainActivity : AppCompatActivity() {
    lateinit var progress:ProgressBar
    lateinit var rv_media_list: RecyclerView
    var arrayList_details:ArrayList<Model> = ArrayList();
    //OkHttpClient creates connection pool between client and server
     lateinit var adapter : CustomAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress=findViewById(R.id.progress)
        rv_media_list=findViewById(R.id.rv_media_list)
        rv_media_list.setHasFixedSize(true)
        //rv_media_list.setLayoutManager(LinearLayoutManager(this))

        val mLayoutManager = GridLayoutManager(this, 2)
        rv_media_list.setLayoutManager(mLayoutManager)
        loadUrlData("https://rss.itunes.apple.com/api/v1/us/music-videos/top-music-videos/all/10/explicit.json")
    }


    private fun loadUrlData(URL_DATA: String) {
        val stringRequest = StringRequest(Request.Method.GET,
                URL_DATA, Response.Listener { response ->
            progress.visibility =View.GONE

            try {

                val jsonObject = JSONObject(response)
                val jsonFeedObject = jsonObject.getJSONObject("feed")


                val array = jsonFeedObject.getJSONArray("results")

                for (i in 0 until array.length()) {

                    val jo = array.getJSONObject(i)

                    val items = Model(jo.getString("artistName"), jo.getString("name"),
                            jo.getString("artworkUrl100"))
                    arrayList_details.add(items)

                }

                adapter = CustomAdapter(arrayList_details, applicationContext)
                rv_media_list.setAdapter(adapter)

            } catch (e: JSONException) {

                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> Toast.makeText(this@MainActivity, "Error" + error.toString(), Toast.LENGTH_SHORT).show() })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
