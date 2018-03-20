package com.example.mark.kotlinapp

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
class MainActivity : AppCompatActivity() {




    private var editTextcn: EditText? = null
    private var editTextname: EditText? = null
    private var button: Button? = null
    private var pd: ProgressDialog? = null
    private val URL = "http://192.168.10.122:8080/kotlinApi/Controller.php";



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pd = ProgressDialog(this@MainActivity)
        editTextcn = findViewById<EditText>(R.id.editTextCn)
        editTextname = findViewById<EditText>(R.id.editTextName)
        button = findViewById<Button>(R.id.button)
        pd!!.setMessage("Sending data please wait..")

        button?.setOnClickListener() {
            sendData()
        }
    }

    private fun sendData() {
        pd!!.setMessage("Sending data please wait..")
        pd!!.show()

        val stringRequest = object: StringRequest(Request.Method.POST, URL,
                Response.Listener<String>{response ->

                     pd!!.dismiss()


//                    val obj = JSONObject(response)
//                    Toast.makeText(applicationContext, obj.getString("name"), Toast.LENGTH_LONG).show()

                    Toast.makeText(applicationContext, response, Toast.LENGTH_LONG).show()

                }, object: Response.ErrorListener{

            override fun onErrorResponse(p0: VolleyError?) {

                pd!!.dismiss()
                Toast.makeText(applicationContext, p0?.message, Toast.LENGTH_LONG).show()

            }
        })
        {
            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String, String>()
                params.put("Name", editTextname?.text.toString())
                return params
            }
        }

        VolleySingleton.instance?.addToRequestQueue(stringRequest)


    }
}
