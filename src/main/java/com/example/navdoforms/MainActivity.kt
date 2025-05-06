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
import com.example.navdoforms.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var TimeStamp: String? = null
    val fileformat = ".jpg"
    var capturePhotoButton1Request = false
    var capturePhotoButton2Request = false
    var capturePhotoButton3Request = false
    var capturePhotoButton4Request = false
    var capturePhotoButton5Request = false
    var capturePhotoButton7Request = false
    var capturePhotoButton8Request = false
    var capturePhotoButton9Request = false
    var capturePhotoButton10Request = false
    var capturePhotoButton11Request = false
    var capturePhotoButton12Request = false
    var capturePhotoButton13Request = false
    var capturePhotoButton14Request = false
    var capturePhotoButton15Request = false


    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get reference to the ImageView
        val myImageView = binding.photoOfEqupmentImageView
        val myImageView1 = binding.imagePathView1
        val myImageView2 = binding.imagePathView2
        val myImageView3 = binding.imagePathView3
        val myImageView4 = binding.imagePathView4
        val myImageView5 = binding.imagePathView5
        val myImageView6 = binding.imagePathView6
        val myImageView7 = binding.imagePathView7
        val myImageView8 = binding.imagePathView8
        val myImageView9 = binding.imagePathView9
        val myImageView10 = binding.imagePathView10
        val myImageView11 = binding.imagePathView11
        val myImageView12 = binding.imagePathView12
        val myImageView13 = binding.imagePathView13
        val myImageView14 = binding.imagePathView14
        val imagePathView = binding.imagePathView

        // Place radio buttons in a variable here:
        val capturePhotoGroup5 = binding.exhaustMufflerCaptureGroup
        val capturePhotoGroup6 = binding.capturePhotoGroup6
        val capturePhotoGroup8 = binding.capturePhotoGroup8
        val capturePhotoGroup7 = binding.exhaustMufflerCaptureYes
        val capturePhotoGroup9 = binding.capturePhotoGroup9
        val capturePhotoGroup18 = binding.capturePhotoGroup18
        val capturePhotoGroup10 = binding.capturePhotoGroup10
        val capturePhotoGroup11 = binding.capturePhotoGroup11
        val capturePhotoGroup12 = binding.capturePhotoGroup12
        val capturePhotoGroup13 = binding.capturePhotoGroup13
        val capturePhotoGroupPlant1 = binding.capturePhotoGroupPlant1
        val restrictionIndicatorPhotoGroup = binding.restrictionIndicatorPhotoGroup
        val vacuatorValvesPhotoGroup = binding.vacuatorValvesPhotoGroup
        val captureOtherObservationPhotoGroup = binding.captureOtherObservationPhotoGroup

        // Place capturePhotoButton variable here:
        val capturePhotoButton1 = binding.capturePhotoButton1
        val capturePhotoButton2 = binding.capturePhotoButton2
        val capturePhotoButton3 = binding.capturePhotoButton3
        val capturePhotoButton4 = binding.capturePhotobutton4
        val capturePhotoButton5 = binding.capturePhotobutton5
        val capturePhotoButton7 = binding.capturePhotoButton7
        val capturePhotoButton8 = binding.capturePhotoButton8
        val capturePhotoButton9 = binding.capturePhotoButton9
        val restrictionIndicatorPhotoButton = binding.restrictionIndicatorPhotoButton
        val primaryFuelPhotoButton = binding.primaryFuelPhoto
        val oilFilterPhotoButton = binding.oilFilterPhotoBtn
        val photoOfBypassOilFilter = binding.photoOfBypassOilFilter
        val photoOfHydraulicOilFilter = binding.photoOfHydraulicFilter
        val photoOfTransmissionOilFilter = binding.photoOfTransmissionFilter
        val submitButton = binding.submitBtn1
        val photoOfEquipmentButton = binding.photoOfEquipment

        // Get the reference to the Spinner
        val myCustomerNames = binding.myCustomerNamesPlant
        val myAirCleanerModels = binding.myAirCleanerModels
        val myAirCleanerUnits = binding.myAirCleanerUnits

        // Place the logic for radio button groups here:
        capturePhotoGroup5.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes1 -> {
                    capturePhotoButton1.visibility = View.VISIBLE
                    capturePhotoButton1Request = true
                }
                R.id.capturePhotoNo1 -> {
                    capturePhotoButton1.visibility = View.GONE
                    capturePhotoButton1Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup6.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes2 -> {
                    capturePhotoButton2.visibility = View.VISIBLE
                    capturePhotoButton2Request = true
                }
                R.id.capturePhotoNo2 -> {
                    capturePhotoButton2.visibility = View.GONE
                    capturePhotoButton2Request = false
                    }
                }
            }

        // Place the logic for radio button groups here:
        capturePhotoGroup8.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes3 -> {
                    capturePhotoButton3.visibility = View.VISIBLE
                    capturePhotoButton3Request = true
                }
                R.id.capturePhotoNo3 -> {
                    capturePhotoButton3.visibility = View.GONE
                    capturePhotoButton3Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup9.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoButtonYes4 -> {
                    capturePhotoButton4.visibility = View.VISIBLE
                    capturePhotoButton4Request = true
                }

                R.id.capturePhotoButtonNo4-> {
                    capturePhotoButton4.visibility = View.GONE
                    capturePhotoButton4Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroupPlant1.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoButton5Yes -> {
                    capturePhotoButton5.visibility = View.VISIBLE
                    capturePhotoButton5Request = true
                }
                R.id.capturePhotoButton5No -> {
                    capturePhotoButton5.visibility = View.GONE
                    capturePhotoButton5Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        restrictionIndicatorPhotoGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.restrictionIndicatorPhotoYes -> {
                    restrictionIndicatorPhotoButton.visibility = View.VISIBLE
                    capturePhotoButton11Request = true
                }

                R.id.restrictionIndicatorPhotoNo -> {
                    restrictionIndicatorPhotoButton.visibility = View.GONE
                    capturePhotoButton11Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        vacuatorValvesPhotoGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.vacuatorValvesPhotoYes -> {
                    capturePhotoButton7.visibility = View.VISIBLE
                    capturePhotoButton7Request = true
                }
                R.id.vacuatorValvesPhotoNo -> {
                    capturePhotoButton7.visibility = View.GONE
                    capturePhotoButton7Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        captureOtherObservationPhotoGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.captureOtherObservationPhotoYes -> {
                    capturePhotoButton8.visibility = View.VISIBLE
                    capturePhotoButton8Request = true
                }
                R.id.captureOtherObservationPhotoNo -> {
                    capturePhotoButton8.visibility = View.GONE
                    capturePhotoButton8Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup7.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes -> {
                    primaryFuelPhotoButton.visibility = View.VISIBLE
                    capturePhotoButton10Request = true
                }
                R.id.capturePhotoNo7 -> {
                    primaryFuelPhotoButton.visibility = View.GONE
                    capturePhotoButton10Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup18.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes18 -> {
                    capturePhotoButton9.visibility = View.VISIBLE
                    capturePhotoButton9Request = true
                }

                R.id.capturePhotoNo18 -> {
                    capturePhotoButton9.visibility = View.GONE
                    capturePhotoButton9Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup10.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes10 -> {
                    oilFilterPhotoButton.visibility = View.VISIBLE
                    capturePhotoButton12Request = true
                }
                R.id.capturePhotoNo10 -> {
                    oilFilterPhotoButton.visibility = View.GONE
                    capturePhotoButton12Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup11.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes11 -> {
                    photoOfBypassOilFilter.visibility = View.VISIBLE
                    capturePhotoButton13Request = true
                }
                R.id.capturePhotoNo11 -> {
                    photoOfBypassOilFilter.visibility = View.GONE
                    capturePhotoButton13Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup12.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes12 -> {
                    photoOfHydraulicOilFilter.visibility = View.VISIBLE
                    capturePhotoButton14Request = true
                }
                R.id.capturePhotoNo12 -> {
                    photoOfHydraulicOilFilter.visibility = View.GONE
                    capturePhotoButton14Request = false
                }
            }
        }

        // Place the logic for radio button groups here:
        capturePhotoGroup13.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.capturePhotoYes13 -> {
                    photoOfTransmissionOilFilter.visibility = View.VISIBLE
                    capturePhotoButton15Request = true
                }
                R.id.capturePhotoNo13 -> {
                    photoOfTransmissionOilFilter.visibility = View.GONE
                    capturePhotoButton15Request = false
                }
            }
        }

        // Set the adapter for the spinner
        val airCleanerUnitsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.air_cleaner_units,
            android.R.layout.simple_spinner_item
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.air_cleaner_units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner
            myAirCleanerUnits.adapter = adapter
        }

        // Set up item selection listener
        myAirCleanerUnits.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    this@MainActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }

        // Set the adapter for the spinner
        val airCleanerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.air_cleaner_models,
            android.R.layout.simple_spinner_item
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.air_cleaner_models,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner
            myAirCleanerModels.adapter = adapter
        }

        // Set up item selction listener
        myAirCleanerModels.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    this@MainActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
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
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner
            myCustomerNames.adapter = adapter
        }

        // Set up item selection listener
        myCustomerNames.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                    this@MainActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }

        // Get reference to the Spinner
        val myInspectedBy = binding.myInspectedByPlant

        // Set the adapter for the spinner
        val inspectedByAdapter = ArrayAdapter.createFromResource(
            this@MainActivity,
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
                    this@MainActivity,
                    "Selected: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }

        val navView: BottomNavigationView = binding.navView

        // Place submit button logic here:
        submitButton.setOnClickListener {
            showSaveOptionsDialog()
        }

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

                if (capturePhotoButton1Request == true) {
                    val imageId1 = getImageIdFromPath(this, filePath)

                    if (imageId1 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId1, myImageView1)
                        myImageView1.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton2Request == true) {
                    val imageId2 = getImageIdFromPath(this, filePath)

                    if (imageId2 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId2, myImageView2)
                        myImageView2.visibility = View.VISIBLE
                    } else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton3Request == true) {
                    val imageId3 = getImageIdFromPath(this, filePath)

                    if (imageId3 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId3, myImageView3)
                        myImageView3.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton4Request == true) {
                    val imageId4 = getImageIdFromPath(this, filePath)

                    if (imageId4 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
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

                if (capturePhotoButton5Request == true) {
                    val imageId5 = getImageIdFromPath(this, filePath)

                    if (imageId5 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
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

                if (capturePhotoButton7Request == true) {
                    val imageId7 = getImageIdFromPath(this, filePath)

                    if (imageId7 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId7, myImageView6)
                        myImageView6.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton8Request == true) {
                    val imageId8 = getImageIdFromPath(this, filePath)

                    if (imageId8 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId8, myImageView8)
                        myImageView8.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton9Request == true) {
                    val imageId9 = getImageIdFromPath(this, filePath)

                    if (imageId9 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId9, myImageView9)
                        myImageView9.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton10Request == true) {
                    val imageId10 = getImageIdFromPath(this, filePath)

                    if (imageId10 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId10, myImageView9)
                        myImageView9.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton11Request == true) {
                    val imageId11 = getImageIdFromPath(this, filePath)

                    if (imageId11 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId11, myImageView10)
                        myImageView10.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton12Request == true) {
                    val imageId12 = getImageIdFromPath(this, filePath)

                    if (imageId12 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId12, myImageView11)
                        myImageView11.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton13Request == true) {
                    val imageId13 = getImageIdFromPath(this, filePath)

                    if (imageId13 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId13, myImageView12)
                        myImageView12.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton14Request == true) {
                    val imageId14 = getImageIdFromPath(this, filePath)

                    if (imageId14 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId14, myImageView13)
                        myImageView13.visibility = View.VISIBLE
                    }
                    else {
                        // Image not found in MediaStore
                        Log.e("MainActivity", "Image not found in MediaStore")
                        Toast.makeText(this, "Image not found in MediaStore", Toast.LENGTH_SHORT).show()
                    }
                }

                if (capturePhotoButton15Request == true) {
                    val imageId15 = getImageIdFromPath(this, filePath)

                    if (imageId15 != null) {
                        // You have the image ID!
                        Log.d("MainActivity", "Image ID: $imageId")
                        Toast.makeText(this, "Image ID: $imageId", Toast.LENGTH_SHORT).show()
                        // Now you can use the imageId with loadImageFromSharedStorage
                        loadImageFromSharedStorage(this, imageId15, myImageView14)
                        myImageView14.visibility = View.VISIBLE
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


        // Place photoOfEquipment button logic here:
        photoOfEquipmentButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton1.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton2.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton3.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton4.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton5.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton7.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton8.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        capturePhotoButton9.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        primaryFuelPhotoButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        restrictionIndicatorPhotoButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        oilFilterPhotoButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        photoOfBypassOilFilter.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        photoOfHydraulicOilFilter.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo Capture logic here
        photoOfTransmissionOilFilter.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Connect the navController to the BottomNavigationView
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