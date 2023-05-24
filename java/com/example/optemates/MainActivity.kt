package com.example.optemates

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.optemates.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import okhttp3.internal.notify
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private  lateinit var  binding : ActivityMainBinding
    private  lateinit var viewModel : viewModelUser
    private  lateinit var adapter: UserAdapter
//    private  lateinit var  adapter: MyAdapter

    private  val daftarData = arrayListOf(
        Pair("Konstantin", "asldkfjlksjdklfj"),
        Pair("Augustus", ";alkjfsj;fj;skfjjk"),
        Pair("Julius", "jfdieuyqa;mvz,"),
        Pair("Justiniani", "ueqokvbanaslkj"),
        Pair("Ptolomeus", "aksjdfoieejj")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        adapter = MyAdapter()
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnitemCLickCallback{
            override fun onItemClicked(data: User) {
                Log.d("Extra di intent", data.login.toString())
                Intent( this@MainActivity, UserDetailedActivity::class.java).also {
                    it.putExtra(UserDetailedActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(viewModelUser::class.java)
        viewModel.getSearchUsers().observe(this) {

            binding.apply {
                RV2.layoutManager = LinearLayoutManager(this@MainActivity)
                RV2.setHasFixedSize(true)
                RV2.adapter = adapter
            }


            if (it != null) {
                adapter.setList(it)
            }
        }


//        ambilDataPengguna()

//        showRV()



    }

    private fun showRV() {


//        val recyclerView: RecyclerView = findViewById(R.id.RV2)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter  = MyAdapter(daftarData, this)
//        recyclerView.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menuop, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.cari)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint="Input Nama"
        searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.setSearchUsers(query)
//                    viewModel.setDummyUsers(query)
                    Log.d("submitted", query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TextChanged", "Search")
                return false
            }
        })
        return true
    }

    private fun cariUser(cari : String) {

        Log.d("Method Cari", cari)
        binding.apply {
            viewModel

        }
    }


    private fun ambilDataPengguna() {
        val client = AsyncHttpClient()
        val params = RequestParams()
        val url = "https://api.github.com/search/users"

        params.put("q", "sidiqpermana")
        client.addHeader("Authorization", "Bearer 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")
//        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
        client.get(url,params, object  : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val hasil = String((responseBody!!))

                Log.d("Hasil respone", hasil)


                try {
                    val responseObject = JSONObject(hasil)

                    val langsungaja = responseObject

//                    tvUtama.text= langsungaja.toString()
                }catch (e: Exception){

                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onfailure", error.toString())
            }


        }


        )

    }
}
