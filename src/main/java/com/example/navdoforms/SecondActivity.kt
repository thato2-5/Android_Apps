package com.example.navdoforms

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navdoforms.databinding.ActivitySecondBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.IOException

class SecondActivity : AppCompatActivity() {
    var TimeStamp: String? = null
    val fileformat = ".jpg"

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Place radio group buttons in a variable here:
        val exhaustMufflerCaptureGroup = binding.exhaustMufflerCaptureGroup
        val capturePhotoGroup1 = binding.capturePhotoGroup1
        val capturePhotoGroup2 = binding.capturePhotoGroup2
        val capturePhotoGroup3 = binding.capturePhotoGroup3
        val capturePhotoGroup4 = binding.capturePhotoGroup4

        // Place image capture buttons in a variable here:
        val photoOfExhaustMufflerButton = binding.photoOfExhaustMuffler
        val photoOfExhaustPipeButton = binding.photoOfExhaustPipe
        val photoOfExhaustPipeButton1 = binding.capturePhoto3
        val photoOfExhaustPipeButton2 = binding.capturePhoto4
        val photoOfExhaustPipeButton3 = binding.capturePhoto5

        // Get reference to the ImageView
        val myImageView = binding.exhaustMufflerImageView
        val myImageView1 = binding.photoOfEquipmentImageView
        val myImageView2 = binding.exhaustPipeImageView
        val myImageView3 = binding.exhaustPipeImageView1
        val myImageView4 = binding.exhaustPipeImageView2
        val myImageView5 = binding.exhaustPipeImageView3

        // Place image request response variable here:
        var capturePhotoButtonRequest = false
        var capturePhotoButtonRequest1 = false
        var capturePhotoButtonRequest2 = false
        var capturePhotoButtonRequest3 = false
        var capturePhotoButtonRequest4 = false

        // Get the reference to the Spinner
        val myCustomerNames = binding.myCustomerNames

        // Register the launcher
        val cameraActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val timestamps = data?.getStringExtra(CameraActivity.EXTRA_TIMESTAMPS)?.toList() ?: emptyList()
                TimeStamp = timestamps.toString()
                TimeStamp = removeBracketsDeleteAt(TimeStamp.toString())
                val TimeStampWithoutCommas = TimeStamp.toString().replace(",", "")
                // val TimeStampWithoutCommasAndDashes = TimeStampWithoutCommas.replace("-", "")
                val TimeStampWithoutCommasAndDashesAndSpaces = TimeStampWithoutCommas.replace(" ", "")
                var filePath = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath1 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath2 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath3 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath4 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                val filePath5 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                Toast.makeText(this, "Photo path: $filePath", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Photo path: $filePath")

                /*
                imagePathView.text = filePath
                val imageUri = Uri.parse(filePath)
                myImageView.setImageURI(imageUri)
                myImageView.visibility = View.VISIBLE

                 */
                val imageId1 = getImageIdFromPath(this, filePath1)

                if (imageId1 != null) {
                    // You have the image ID!
                    Log.d("MainActivity", "Image ID: $imageId1")
                    Toast.makeText(this, "Image ID: $imageId1", Toast.LENGTH_SHORT).show()
                    // Now you can use the imageId with loadImageFromSharedStorage
                    loadImageFromSharedStorage(this, imageId1, myImageView1)
                    myImageView1.visibility = View.VISIBLE
                } else {
                    // Image not found in MediaStore
                    Log.e("MainActivity", "Image not found in MediaStore")
                    Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                }

                if (capturePhotoButtonRequest == true) {
                    val imageId = getImageIdFromPath(this, filePath)

                    if (imageId != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId, myImageView)
                        myImageView.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest1 == true) {
                    val imageId2 = getImageIdFromPath(this, filePath2)

                    if (imageId2 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId2")
                        Toast.makeText(this, "Image ID: $imageId2", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId2, myImageView2)
                        myImageView2.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest2 == true) {
                    val imageId3 = getImageIdFromPath(this, filePath3)

                    if (imageId3 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId3")
                        Toast.makeText(this, "Image ID: $imageId3", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId3, myImageView3)
                        myImageView3.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest3 == true) {
                    val imageId4 = getImageIdFromPath(this, filePath4)

                    if (imageId4 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId4")
                        Toast.makeText(this, "Image ID: $imageId4", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId4, myImageView4)
                        myImageView4.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest4 == true) {
                    val imageId5 = getImageIdFromPath(this, filePath5)

                    if (imageId5 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId5")
                        Toast.makeText(this, "Image ID: $imageId5", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId5, myImageView5)
                        myImageView5.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }





                /*
                // Now you have the timestamps in MainActivity
                Log.d("MainActivity", "Received timestamps: $timestamps")
                // You can use this list as needed
                Toast.makeText(this, "Photo timestamps: $timestamps", Toast.LENGTH_SHORT).show()
                 */
            }
        }

        // Enter radio button selection logic here:
        exhaustMufflerCaptureGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.exhaustMufflerCaptureYes -> {
                    binding.photoOfExhaustMuffler.visibility = View.VISIBLE
                    capturePhotoButtonRequest = true
            }
                R.id.exhaustMufflerCaptureNo -> {
                    binding.photoOfExhaustMuffler.visibility = View.GONE
                    capturePhotoButtonRequest = false
                }
            }
        }

        // Place radio button selection logic here:
        capturePhotoGroup1.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto1Yes -> {
                    binding.photoOfExhaustPipe.visibility = View.VISIBLE
                    capturePhotoButtonRequest1 = true
                }

                R.id.capturePhoto1No -> {
                    binding.photoOfExhaustPipe.visibility = View.GONE
                    capturePhotoButtonRequest1 = false
                }
            }
        }

        // Place radio button selection logic here
        capturePhotoGroup2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto2Yes -> {
                    binding.capturePhoto3.visibility = View.VISIBLE
                    capturePhotoButtonRequest2 = true
                }

                R.id.capturePhoto2No -> {
                    binding.capturePhoto3.visibility = View.GONE
                    capturePhotoButtonRequest2 = false
                }
            }
        }

        // Place radio button selection logic here
        capturePhotoGroup3.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto3Yes -> {
                    binding.capturePhoto4.visibility = View.VISIBLE
                    capturePhotoButtonRequest3 = true
                }

                R.id.capturePhoto3No -> {
                    binding.capturePhoto4.visibility = View.GONE
                    capturePhotoButtonRequest3 = false
                }
            }
        }

        // Place radio button selection logic here
        capturePhotoGroup4.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto4Yes -> {
                    binding.capturePhoto5.visibility = View.VISIBLE
                    capturePhotoButtonRequest4 = true
                }

                R.id.capturePhoto4No -> {
                    binding.capturePhoto5.visibility = View.GONE
                    capturePhotoButtonRequest4 = false
                }
            }
        }

        // Place image capture button logic here:
        photoOfExhaustMufflerButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place image capture button logic here:
        photoOfExhaustPipeButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place image capture button logic here:
        photoOfExhaustPipeButton1.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place image capture button logic here:
        photoOfExhaustPipeButton2.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place image capture button logic here:
        photoOfExhaustPipeButton3.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }


        // Set the adapter for the Spinner
        val customerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.customer_names,
            android.R.layout.simple_spinner_item
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.customer_names,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner
            myCustomerNames.adapter = adapter
        }

        // Set up item selection listener
        myCustomerNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Show selected item in a Toast message
                Toast.makeText(
                    this@SecondActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }

        // Get the reference to the Spinner
        val myInspectedBy = binding.myInspectedBy

        // Set the adapter for the Spinner
        val inspectedByAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.inspected_by_names,
            android.R.layout.simple_spinner_item
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.inspected_by_names,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner
            myInspectedBy.adapter = adapter
        }

        // Set up item selection listener
        myInspectedBy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Show selected item in a Toast message
                Toast.makeText(
                    this@SecondActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }

        val navView: BottomNavigationView = binding.navView

        // Place radio buttons in a variable here:
        val selectedRadioButtonId = binding.inspectionAssistantGroup
        val selectedCapturePhoto1RadioButtonId = binding.capturePhotoGroup1
        val selectedCapturePhoto2RadioButtonId = binding.capturePhotoGroup2
        val selectedCapturePhoto3RadioButtonId = binding.capturePhotoGroup3
        val selectedCapturePhoto4RadioButtonId = binding.capturePhotoGroup4

        // Place submit button in a variable here:
        val submitButton = binding.submitBtn1

        // Place photoOfEquipment button in a variable here:
        val photoOfEquipmentButton = binding.photoOfEquipmentInspected

        // Place photoOfEquipment button logic here:
        photoOfEquipmentButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        selectedRadioButtonId.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.inspectionAssistantYes -> {
                    binding.InspectionAssistant.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }

                R.id.inspectionAssistantNo -> {
                    binding.InspectionAssistant.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        selectedCapturePhoto1RadioButtonId.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto1Yes -> {
                    binding.photoOfExhaustPipe.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }
                R.id.capturePhoto1No -> {
                    binding.photoOfExhaustPipe.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        selectedCapturePhoto2RadioButtonId.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto2Yes -> {
                    binding.capturePhoto3.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }

                R.id.capturePhoto2No -> {
                    binding.capturePhoto3.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        selectedCapturePhoto3RadioButtonId.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto3Yes -> {
                    binding.capturePhoto4.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }
                R.id.capturePhoto3No -> {
                    binding.capturePhoto4.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        selectedCapturePhoto4RadioButtonId.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhoto4Yes -> {
                    binding.capturePhoto5.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }

                R.id.capturePhoto4No -> {
                    binding.capturePhoto5.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Place submit button logic here:
        submitButton.setOnClickListener {
            showSaveOptionsDialog()
        }

            val navController = findNavController(R.id.nav_host_fragment_activity_second)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }

    private fun removeBracketsDeleteAt(input: String): String {
        val stringBuilder = StringBuilder(input)
        var index = stringBuilder.indexOf("[")
        while (index != -1) {
            stringBuilder.deleteCharAt(index)
            index = stringBuilder.indexOf("[")
        }
        index = stringBuilder.indexOf("]")
        while (index != -1) {
            stringBuilder.deleteCharAt(index)
            index = stringBuilder.indexOf("]")
        }
        return stringBuilder.toString()
    }

    private fun getImageIdFromPath(context: Context, filePath: String): Long? {
        val contentResolver: ContentResolver = context.contentResolver
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = MediaStore.Images.Media.DATA + " = ?"
        val selectionArgs = arrayOf(filePath)
        var imageId: Long? = null

        var cursor: Cursor? = null
        try {
            cursor = contentResolver.query(
                uri,
                projection,
                selection,
                selectionArgs,
                null
            )

            if (cursor != null && cursor.moveToFirst()) {
                val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                imageId = cursor.getLong(idColumnIndex)
                Log.d("ImageId", "Found image ID: $imageId for path: $filePath")
            } else {
                Log.w("ImageId", "No image found for path: $filePath")
            }
        } catch (e: Exception) {
            Log.e("ImageId", "Error getting image ID: ${e.message}")
        } finally {
            cursor?.close()
        }

        return imageId
    }

    fun loadImageFromSharedStorage(context: Context, imageId: Long, imageView: ImageView) {
        val contentResolver: ContentResolver = context.contentResolver
        val imageUri: Uri = ContentUris.withAppendedId(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageId
        )
        try {
            val bitmap: Bitmap? = contentResolver.openInputStream(imageUri)?.use {
                BitmapFactory.decodeStream(it)
            }
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.options_nav_menu, menu)
            return true
        }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_dashboard -> {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_notifications -> {
                    val intent = Intent(this, ThirdActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_birth_certificates -> {
                    val intent = Intent(this, BirthCertificates::class.java)
                    startActivity(intent)
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }
        }

    private fun showSaveOptionsDialog() {
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Save Form As")
            .setMessage("How would you like to save this form ?")
            .setPositiveButton("Complete") { dialog, which ->
                // Save as Complete
                saveForm("Complete")
            }
            .setNeutralButton("Incomplete") { dialog, which ->
                // Save as Incomplete
                saveForm("Incomplete")
            }
            .setNegativeButton("Critical") { dialog, which ->
                // Save as Critical
                saveForm("Critical")
            }
            .create()

        dialog.show()
    }

    private fun saveForm(status: String) {
        // Implement your save form logic here
        when (status) {
            "Complete" -> {
                // Save as Complete
                Toast.makeText(this, "Form saved as Complete", Toast.LENGTH_SHORT).show()
            }

            "Incomplete" -> {
                // Save as Incomplete
                Toast.makeText(this, "Form saved as Incomplete", Toast.LENGTH_SHORT).show()
            }

            "Critical" -> {
                // Save as Critical
                Toast.makeText(this, "Form saved as Critical", Toast.LENGTH_SHORT).show()
            }
        }
    }


}