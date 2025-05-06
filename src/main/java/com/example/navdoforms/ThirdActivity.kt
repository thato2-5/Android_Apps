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
import com.example.navdoforms.databinding.ActivityThirdBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.IOException

class ThirdActivity : AppCompatActivity() {
    var TimeStamp: String? = null
    val fileformat = ".jpg"

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Place radio group buttons in a variable here:
        val equipmentInspectedGroup = binding.equipmentInspectedGroup
        val installationPhotoGroup1 = binding.installationPhotoGroup1
        val installationPhotoGroup2 = binding.installationPhotoGroup2
        val installationPhotoGroup3 = binding.installationPhotoGroup3
        val installationPhotoGroup4 = binding.installationPhotoGroup4
        val installationPhotoGroup5 = binding.installationPhotoGroup5
        val installationPhotoGroup6 = binding.installationPhotoGroup6
        val installationPhotoGroup7 = binding.installationPhotoGroup7
        val installationPhotoGroup8 = binding.installationPhotoGroup8
        val installationPhotoGroup9 = binding.installationPhotoGroup9
        val installationPhotoGroup10 = binding.installationPhotoGroup10

        // Place photo capture image view here:
        val myImageView = binding.equipmentInspectedImageView
        val myImageView2 = binding.installationImageView1
        val myImageView3 = binding.installationImageView2
        val myImageView4 = binding.installationImageView3
        val myImageView5 = binding.installationImageView4
        val myImageView6 = binding.installationImageView5
        val myImageView7 = binding.installationImageView6
        val myImageView8 = binding.installationImageView7
        val myImageView9 = binding.installationImageView8
        val myImageView10 = binding.installationImageView9
        val myImageView11 = binding.installationImageView10

        // Place photo capture state variables here:
        val photoOfEquipmentButton = binding.equipmentInspectedButton
        val photoOfInstallationButton1 = binding.captureInstallationPhoto1
        val photoOfInstallationButton2 = binding.captureInstallationPhoto2
        val photoOfInstallationButton3 = binding.captureInstallationPhoto3
        val photoOfInstallationButton4 = binding.captureInstallationPhoto4
        val photoOfInstallationButton5 = binding.captureInstallationPhoto5
        val photoOfInstallationButton6 = binding.captureInstallationPhoto6
        val photoOfInstallationButton7 = binding.captureInstallationPhoto7
        val photoOfInstallationButton8 = binding.captureInstallationPhoto8
        val photoOfInstallationButton9 = binding.captureInstallationPhoto9
        val photoOfInstallationButton10 = binding.captureInstallationPhoto10

        // Place image capture request launcher here:
        var capturePhotoButtonRequest = false
        var capturePhotoButtonRequest2 = false
        var capturePhotoButtonRequest3 = false
        var capturePhotoButtonRequest4 = false
        var capturePhotoButtonRequest5 = false
        var capturePhotoButtonRequest6 = false
        var capturePhotoButtonRequest7 = false
        var capturePhotoButtonRequest8 = false
        var capturePhotoButtonRequest9 = false
        var capturePhotoButtonRequest10 = false

        // Get the reference to the Spinner
        val myCustomerNames = binding.myCustomerTechnical

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
                var filePath5 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath6 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath7 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath8 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath9 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                var filePath10 = "/storage/emulated/0/Pictures/CameraX_Image/".plus(TimeStampWithoutCommasAndDashesAndSpaces).plus(fileformat)
                Toast.makeText(this, "Photo path: $filePath", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Photo path: $filePath")

                /*
                imagePathView.text = filePath
                val imageUri = Uri.parse(filePath)
                myImageView.setImageURI(imageUri)
                myImageView.visibility = View.VISIBLE

                 */
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

                if (capturePhotoButtonRequest == true) {
                    val imageId1 = getImageIdFromPath(this, filePath1)

                    if (imageId1 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId1")
                        Toast.makeText(this, "Image ID: $imageId1", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId1, myImageView2)
                        myImageView2.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest2 == true) {
                    val imageId2 = getImageIdFromPath(this, filePath2)

                    if (imageId2 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId2")
                        Toast.makeText(this, "Image ID: $imageId2", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId2, myImageView3)
                        myImageView3.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest3 == true) {
                    val imageId3 = getImageIdFromPath(this, filePath3)

                    if (imageId3 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId3")
                        Toast.makeText(this, "Image ID: $imageId3", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId3, myImageView4)
                        myImageView4.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest4 == true) {
                    val imageId4 = getImageIdFromPath(this, filePath4)

                    if (imageId4 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId4")
                        Toast.makeText(this, "Image ID: $imageId4", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId4, myImageView5)
                        myImageView5.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest5 == true) {
                    val imageId5 = getImageIdFromPath(this, filePath5)

                    if (imageId5 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId5")
                        Toast.makeText(this, "Image ID: $imageId5", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId5, myImageView6)
                        myImageView6.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest6 == true) {
                    val imageId6 = getImageIdFromPath(this, filePath6)

                    if (imageId6 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId6")
                        Toast.makeText(this, "Image ID: $imageId6", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId6, myImageView7)
                        myImageView7.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest7 == true) {
                    val imageId7 = getImageIdFromPath(this, filePath7)

                    if (imageId7 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId7")
                        Toast.makeText(this, "Image ID: $imageId7", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId7, myImageView8)
                        myImageView8.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest8 == true) {
                    val imageId8 = getImageIdFromPath(this, filePath8)

                    if (imageId8 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId8")
                        Toast.makeText(this, "Image ID: $imageId8", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId8, myImageView9)
                        myImageView9.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButtonRequest9 == true) {
                    val imageId9 = getImageIdFromPath(this, filePath9)

                    if (imageId9 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId9")
                        Toast.makeText(this, "Image ID: $imageId9", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId9, myImageView10)
                        myImageView10.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                if (capturePhotoButtonRequest10 == true) {
                    val imageId10 = getImageIdFromPath(this, filePath10)

                    if (imageId10 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId10")
                        Toast.makeText(this, "Image ID: $imageId10", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId10, myImageView11)
                        myImageView11.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT)
                            .show()
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

        // Place radio button logic here:
        equipmentInspectedGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.equipmentInspectedYes -> {
                    photoOfEquipmentButton.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }

                R.id.equipmentInspectedNo -> {
                    photoOfEquipmentButton.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup1.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes1 -> {
                    photoOfInstallationButton1.visibility = View.VISIBLE
                    capturePhotoButtonRequest = true
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                }

                R.id.installationPhotoNo1 -> {
                    photoOfInstallationButton1.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes2 -> {
                    photoOfInstallationButton2.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest2 = true
                }
                R.id.installationPhotoNo2 -> {
                    photoOfInstallationButton2.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest2 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup3.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes3 -> {
                    photoOfInstallationButton3.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest3 = true
                }

                R.id.installationPhotoNo3 -> {
                    photoOfInstallationButton3.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest3 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup4.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes4 -> {
                    photoOfInstallationButton4.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest4 = true
                }

                R.id.installationPhotoNo4 -> {
                    photoOfInstallationButton4.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest4 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup5.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes5 -> {
                    photoOfInstallationButton5.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest5 = true
                }

                R.id.installationPhotoNo5 -> {
                    photoOfInstallationButton5.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest5 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup6.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes6 -> {
                    photoOfInstallationButton6.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest6 = true
                }

                R.id.installationPhotoNo6 -> {
                    photoOfInstallationButton6.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest6 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup7.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes7 -> {
                    photoOfInstallationButton7.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest7 = true
                }
                R.id.installationPhotoNo7 -> {
                    photoOfInstallationButton7.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest7 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup8.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes8 -> {
                    photoOfInstallationButton8.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest8 = true
                }

                R.id.installationPhotoNo8 -> {
                    photoOfInstallationButton8.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest8 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup9.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes9 -> {
                    photoOfInstallationButton9.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest9 = true
                }

                R.id.installationPhotoNo9 -> {
                    photoOfInstallationButton9.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest9 = false
                }
            }
        }

        // Place radio button logic here:
        installationPhotoGroup10.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.installationPhotoYes10 -> {
                    photoOfInstallationButton10.visibility = View.VISIBLE
                    //Toast.makeText(this, "Yes Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest10 = true
                }
                R.id.installationPhotoNo10 -> {
                    photoOfInstallationButton10.visibility = View.GONE
                    //Toast.makeText(this, "No Selected", Toast.LENGTH_SHORT).show()
                    capturePhotoButtonRequest10 = false
                }
            }
        }

        // Place photo capture button logic here:
        photoOfEquipmentButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton1.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton2.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton3.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton4.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton5.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton6.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton7.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton8.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton9.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        photoOfInstallationButton10.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Set the adapter for the spinner
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
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            myCustomerNames.adapter = adapter
        }

        // Set up Item Selection Listener
        myCustomerNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle Item Selection
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // Show Selected Item in a Toast Message
                Toast.makeText(
                    this@ThirdActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle No Selection
            }
        }

        // Get the reference to the Spinner
        val myInspectedBy = binding.installedByExhaust

        // Set the adapter for the spinner
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
                // Show selected item in a toast message
                Toast.makeText(
                    this@ThirdActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }

        val navView: BottomNavigationView = binding.navView

        // Place submit button in a variable here:
        val submitButton = binding.submitBtn

        // Place submit button logic here:
        submitButton.setOnClickListener {
            showSaveOptionsDialog()
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_third)
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

    // Place save form logic here:
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
}