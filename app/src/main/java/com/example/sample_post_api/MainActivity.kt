package com.example.sample_post_api
import PostApiResponse
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       val postButton=findViewById<Button>(R.id.postApiCall)
        postButton.setOnClickListener {
//            print("post button click")
            Log.i("", "post button click")
            makePostRequest()
        }

    }
  private  fun makePostRequest() {

         val apiService = RetrofitClient.createService(ApiInterface::class.java)

        // Request payload
        val postRequest = PostApiPayload(
            title = "foo",
            body = "bar",
            userId = 1
        )
        val retrofitData = apiService.createPost(postRequest)

        retrofitData.enqueue(object : retrofit2.Callback<PostApiResponse> {
            override fun onResponse(call: Call<PostApiResponse>, response: Response<PostApiResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.i("Success"," $responseBody")
//                    println("Post Created Successfully: $responseBody")
                } else {
                    Log.i("Error","${response.code()} - ${response.message()}",)
//                    println("Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostApiResponse>, t: Throwable) {
                Log.i("Error",t.message.toString(),)
            }
        })

    }
}