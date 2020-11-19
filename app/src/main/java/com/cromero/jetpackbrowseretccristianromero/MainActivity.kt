package com.cromero.jetpackbrowseretccristianromero

import android.R.attr
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_calc.*
import kotlinx.android.synthetic.main.fragment_fr_camera.*


class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //Botones Menu Principal
    fun onBrowserClick(view: View){

    }

    fun onCameraClick(view: View){
        Navigation.findNavController(view).navigate(R.id.acCamera)
    }

    fun onCalcClick(view: View){
        Navigation.findNavController(view).navigate(R.id.acCalc)
    }


    //Botones Modo camara
    @RequiresApi(Build.VERSION_CODES.M)
    fun onPhotoClick(view: View){
        if(packageManager.checkPermission(CAMERA_SERVICE,"si") != PackageManager.PERMISSION_GRANTED){

            val CODIGO_CAMARA = 1
            requestPermissions(arrayOf(CAMERA_SERVICE), CODIGO_CAMARA)
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }

    }

    //Botones Modo Calcular
    fun onCalcularClick(view : View){
        val num1 = Integer.parseInt(tf_Num1.text.toString())
        val num2 = Integer.parseInt(tf_Num2.text.toString())

        var resul = 0

        when(rg_Operacion.checkedRadioButtonId){
            R.id.rb_Suma -> resul = num1 + num2
            R.id.rb_Resta -> resul = num1 - num2
            R.id.rb_Multi -> resul = num1 * num2
            R.id.rb_Divi -> resul = num1 / num2
        }

        val bundle = bundleOf(Pair("RESULT", resul))
        Navigation.findNavController(view).navigate(R.id.acMulti, bundle)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)

        }

    }

}