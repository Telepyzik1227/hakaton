package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.myapplication.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
 abstract class PolyActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener{
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }
     fun OnMapReady(googleMap: GoogleMap){
         val polyline1 = googleMap.addPolyline((PolylineOptions())
             .clickable(true)
             .add(
                 LatLng(-35.016, 143.321),
                 LatLng(-34.747, 145.592),
                 LatLng(-34.364, 147.891),
                 LatLng(-33.501, 150.217),
                 LatLng(-32.306, 149.248),
                 LatLng(-32.491, 147.309)))
         polyline1.tag = "A"
         stylePolyline(polyline1)

         val polyline2 = googleMap.addPolyline(PolylineOptions()
             .clickable(true)
             .add(
                 LatLng(-29.501, 119.700),
                 LatLng(-27.456, 119.672),
                 LatLng(-25.971, 124.187),
                 LatLng(-28.081, 126.555),
                 LatLng(-28.848, 124.229),
                 LatLng(-28.215, 123.938)))
         polyline2.tag = "B"
         stylePolyline(polyline2)

         val polygon1 = googleMap.addPolygon(PolygonOptions()
             .clickable(true)
             .add(
                 LatLng(-27.457, 153.040),
                 LatLng(-33.852, 151.211),
                 LatLng(-37.813, 144.962),
                 LatLng(-34.928, 138.599)))
         polygon1.tag = "alpha"
         stylePolygon(polygon1)

         val polygon2 = googleMap.addPolygon(PolygonOptions()
             .clickable(true)
             .add(
                 LatLng(-31.673, 128.892),
                 LatLng(-31.952, 115.857),
                 LatLng(-17.785, 122.258),
                 LatLng(-12.4258, 130.7932)))
         polygon2.tag = "beta"
         stylePolygon(polygon2)
         googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.684, 133.903), 4f))

         googleMap.setOnPolylineClickListener(this)
         googleMap.setOnPolygonClickListener(this)
     }
     private val COLOR_BLACK_ARGB = -0x1000000
     private val POLYLINE_STROKE_WIDTH_PX = 12

     /**
      * Styles the polyline, based on type.
      * @param polyline The polyline object that needs styling.
      */
     private fun stylePolyline(polyline: Polyline) {
         val type = polyline.tag?.toString() ?: ""
         when (type) {
             "A" -> {
                 polyline.startCap = CustomCap(
                     BitmapDescriptorFactory.fromResource(R.drawable.ic_mtrl_chip_checked_circle), 10f)
             }
             "B" -> {
                 polyline.startCap = RoundCap()
             }
         }
         polyline.endCap = RoundCap()
         polyline.width = POLYLINE_STROKE_WIDTH_PX.toFloat()
         polyline.color = COLOR_BLACK_ARGB
         polyline.jointType = JointType.ROUND
     }
     private val PATTERN_GAP_LENGTH_PX = 20
     private val DOT: PatternItem = Dot()
     private val GAP: PatternItem = Gap(PATTERN_GAP_LENGTH_PX.toFloat())
     private val PATTERN_POLYLINE_DOTTED = listOf(GAP, DOT)

     /**
      * Listens for clicks on a polyline.
      * @param polyline The polyline object that the user has clicked.
      */
     override fun onPolylineClick(polyline: Polyline) {
         if (polyline.pattern == null || !polyline.pattern!!.contains(DOT)) {
             polyline.pattern = PATTERN_POLYLINE_DOTTED
         } else {
             polyline.pattern = null
         }
         Toast.makeText(this, "Route type " + polyline.tag.toString(),
             Toast.LENGTH_SHORT).show()
     }

     /**
      * Listens for clicks on a polygon.
      * @param polygon The polygon object that the user has clicked.
      */
     override fun onPolygonClick(polygon: Polygon) {
         var color = polygon.strokeColor xor 0x00ffffff
         polygon.strokeColor = color
         color = polygon.fillColor xor 0x00ffffff
         polygon.fillColor = color
         Toast.makeText(this, "Area type ${polygon.tag?.toString()}", Toast.LENGTH_SHORT).show()
     }

     private val COLOR_WHITE_ARGB = -0x1
     private val COLOR_GREEN_ARGB = -0xc771c4
     private val COLOR_PURPLE_ARGB = -0x7e387c
     private val COLOR_ORANGE_ARGB = -0xa80e9
     private val COLOR_BLUE_ARGB = -0x657db
     private val POLYGON_STROKE_WIDTH_PX = 8
     private val PATTERN_DASH_LENGTH_PX = 20

     private val DASH: PatternItem = Dash(PATTERN_DASH_LENGTH_PX.toFloat())

     private val PATTERN_POLYGON_ALPHA = listOf(GAP, DASH)


     private val PATTERN_POLYGON_BETA = listOf(DOT, GAP, DASH, GAP)

     /**
      * Styles the polygon, based on type.
      * @param polygon The polygon object that needs styling.
      */
     private fun stylePolygon(polygon: Polygon) {
         val type = polygon.tag?.toString() ?: ""
         var pattern: List<PatternItem>? = null
         var strokeColor = COLOR_BLACK_ARGB
         var fillColor = COLOR_WHITE_ARGB
         when (type) {
             "alpha" -> {
                 pattern = PATTERN_POLYGON_ALPHA
                 strokeColor = COLOR_GREEN_ARGB
                 fillColor = COLOR_PURPLE_ARGB
             }
             "beta" -> {
                 pattern = PATTERN_POLYGON_BETA
                 strokeColor = COLOR_ORANGE_ARGB
                 fillColor = COLOR_BLUE_ARGB
             }
         }
         polygon.strokePattern = pattern
         polygon.strokeWidth = POLYGON_STROKE_WIDTH_PX.toFloat()
         polygon.strokeColor = strokeColor
         polygon.fillColor = fillColor
     }
 }


