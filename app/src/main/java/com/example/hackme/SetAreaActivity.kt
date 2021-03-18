package com.example.hackme

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SetAreaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_area)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val latitude = 51.4516
        val longitude = 5.4799
        val zoomLevel = 15f
        val circle = map.addCircle(
            CircleOptions()
                .center(LatLng(51.4517, 5.4807))
                .radius(250.0)
                .strokeColor(Color.GREEN)
        )

        val homeLatLng = LatLng(latitude, longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true

        enableMyLocation()
    }

    //    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val tb: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        tb.inflateMenu(R.menu.mainmenu)
//        tb.setOnMenuItemClickListener(
//            object : OnMenuItemClickListener() {
//                fun onMenuItemClick(item: MenuItem?): Boolean {
//                    return onOptionsItemSelected(item)
//                }
//            })
//        return true
//    }
//
//    //NEW
//    fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.getItemId()) {
//            R.id.action_refresh -> {
//                val fragment: Fragment = fragmentManager
//                    .findFragmentById(R.id.listFragment) as Fragment
//                fragment.updateListContent()
//            }
//            R.id.action_settings -> {
//                val intent = Intent(this, MyPreferences::class.java)
//                startActivity(intent)
//                Toast.makeText(this, "Safety area selected", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            else -> {
//            }
//        }
//        return true
//    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        }
        else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

//    fun onRssItemSelected(link: String?) {
//        if (resources.getBoolean(R.bool.twoPaneMode)) {
//            val fragment: Fragment = fragmentManager
//                .findFragmentById(R.id.Fragment) as Fragment
//            fragment.setText(link)
//        } else {
//            val intent = Intent(
//                applicationContext,
//                DetailActivity::class.java
//            )
//            intent.putExtra(DetailActivity.EXTRA_URL, link)
//            startActivity(intent)
//        }
//    }
//
//    fun showContextMenu(item: RssItem) {
//        this.selectedItem = item
//        startActionMode(this)
//    }
//
//    fun goToActionMode(item: RssItem) {
//        this.selectedItem = item
//        startActionMode(this)
//    }
//
//
//    fun onCreateActionMode(mode: ActionMode, menu: Menu?): Boolean {
//        val inflater: MenuInflater = mode.getMenuInflater()
//        inflater.inflate(R.menu.actionmode, menu)
//        return true
//    }
//
//    fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
//        return false
//    }
//
//    fun onActionItemClicked(mode: ActionMode, item: MenuItem?): Boolean {
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.putExtra(
//            Intent.EXTRA_TEXT, "Safety area selected"
//        )
//        intent.type = "text/plain"
//        startActivity(intent)
//        mode.finish()
//        selectedItem = null
//        return true
//    }
}