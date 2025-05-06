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
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navdoforms.databinding.ActivityBirthCertificatesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.IOException

class BirthCertificates : AppCompatActivity() {
    private lateinit var binding: ActivityBirthCertificatesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var TimeStamp: String? = null
        val fileformat = ".jpg"

        super.onCreate(savedInstanceState)

        binding = ActivityBirthCertificatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the reference to the Spinner
        val customerNamesSpinner = binding.myCustomerNamesCertificate
        val inspectedBySpinner = binding.myInspectedByCertificate
        val airCleanerModelsSpinner = binding.myAirCleanerModelsCertificate
        val airCleanerUnitsSpinner = binding.myAirCleanerUnitsCertificate

        // Place radio group buttons in a variable here:
        val primaryAirCleanerElementGroup = binding.primaryAirCleanerElementGroup
        val airCleanerArragementPhotoGroup = binding.airCleanerArragementPhotoGroup

        // Place photo capture image view here:
        val myImageView = binding.equipmentImageView
        val imageView1 = binding.primaryAirCleanerElementImageView
        val imageView2 = binding.primaryAirCleanerElementImageView

        // Place photo capture buttons here:
        val photoOfEquipmentButton = binding.photoOfEquipment
        val primaryAirCleanerElementButton = binding.primaryAirCleanerElementPhotoButton
        val airCleanerArragementPhotoButton = binding.airCleanerArrangementPhotoButton

        // Place image capture request launcher here:
        var capturePhotoButtonRequest = false
        var capturePhotoButtonRequest1 = false

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
                        loadImageFromSharedStorage(this, imageId1, imageView1)
                        imageView1.visibility = View.VISIBLE
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
                        loadImageFromSharedStorage(this, imageId2, imageView2)
                        imageView2.visibility = View.VISIBLE
                    } else {
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

        // Place radio button selection logic here:
        primaryAirCleanerElementGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.primaryAirCleanerElementYes -> {
                    primaryAirCleanerElementButton.visibility = View.VISIBLE
                    capturePhotoButtonRequest = true
                }

                R.id.primaryAirCleanerElementNo -> {
                    primaryAirCleanerElementButton.visibility = View.GONE
                    capturePhotoButtonRequest = false
                }
            }
        }

        // Place radio button selection logic here:
        airCleanerArragementPhotoGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.airCleanerArragementYes -> {
                    airCleanerArragementPhotoButton.visibility = View.VISIBLE
                    capturePhotoButtonRequest1 = true
                }
                R.id.airCleanerArrangementNo -> {
                    airCleanerArragementPhotoButton.visibility = View.GONE
                    capturePhotoButtonRequest1 = false
                }
            }
        }

        // Place photo capture button logic here:
        photoOfEquipmentButton.setOnClickListener {
            capturePhotoButtonRequest = true
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        primaryAirCleanerElementButton.setOnClickListener {
            capturePhotoButtonRequest = true
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Place photo capture button logic here:
        airCleanerArragementPhotoButton.setOnClickListener {
            capturePhotoButtonRequest1 = true
            val intent = Intent(this, CameraActivity::class.java)
            cameraActivityLauncher.launch(intent)
        }

        // Get the reference adapter for the Spinner
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
            airCleanerUnitsSpinner.adapter = adapter
        }

        // Set up item selection listener
        airCleanerUnitsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@BirthCertificates,
                    "Selected air cleaner unit: $selectedItem",
                    Toast.LENGTH_SHORT
                    ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }

        }

        // Get the reference to the Spinner
        val airCleanerModelAdapter = ArrayAdapter.createFromResource(
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
            airCleanerModelsSpinner.adapter = adapter
        }

        // Set up item selection listener
        airCleanerModelsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@BirthCertificates,
                    "Selected air cleaner model: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }



        // Set up the adapter for the Spinner
        val adapter = ArrayAdapter.createFromResource(
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
            customerNamesSpinner.adapter = adapter
        }

        // Set up item selection listener
        customerNamesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                // Handle item selection
                Toast.makeText(
                    this@BirthCertificates,
                    "Selected customer name: ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }

        // Set the adapter for the inspectedBySpinner
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
            inspectedBySpinner.adapter = adapter
        }

        // Set up item selection listener
        inspectedBySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@BirthCertificates,
                    "Selected inspected by: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection
            }
        }

        val navView: BottomNavigationView = binding.navView

        // Place submit button variable here:
        val submitButton = binding.submitBtn1

        // Place submit button logic here:
        submitButton.setOnClickListener {
            showSaveOptionsDialog()
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