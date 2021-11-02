package com.example.bussmaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class PolyActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
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
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_mtrl_chip_checked_circle),
                    10f
                )
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
        Toast.makeText(
            this, "Route type " + polyline.tag.toString(),
            Toast.LENGTH_SHORT
        ).show()
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

    override fun onMapReady(googleMap: GoogleMap) {
        val polyline1 = googleMap.addPolyline(
            (PolylineOptions())
                .clickable(true)
                .add(
                    LatLng(53.33815389590934, 83.77469414253068),
                    LatLng(53.342736564102, 83.76908890051887),
                    LatLng(53.34777270096085, 83.7634810309036),
                    LatLng(53.350401077285724, 83.76274382680981),
                    LatLng(53.354456568456875, 83.76890323434378),
                    LatLng(53.351428410179736, 83.7729372718316),
                    LatLng(53.34307997814641, 83.78186785099385),
                    LatLng(53.33248619089282, 83.7882962541985),
                    LatLng(53.3237970392562, 83.79535792325308),
                    LatLng(53.31315236824314, 83.78073043001066),
                    LatLng(53.30825573406941, 83.77398707846207),
                    LatLng(53.29138198449919, 83.7586051010744),
                    LatLng(53.28570365063831, 83.75319049329742),
                    LatLng(53.28231018114933, 83.67123238454016)
                )
        )
        polyline1.tag = "пл.Октября - Завод:автобус МАЗ-103485(В 429 ХК 22)"
        stylePolyline(polyline1)

        val polyline2 = googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(53.30631092891604, 83.55628527874524),
                    LatLng(53.307591952568245, 83.62088760461215),
                    LatLng(53.28785179264473, 83.64277871587521),
                    LatLng(53.28950194880267, 83.6475506372007),
                    LatLng(53.289468825265246, 83.64754691764256),
                    LatLng(53.28788240803803, 83.65638877113003),
                    LatLng(53.289233890851605, 83.66180870262205),
                    LatLng(53.28955421970917, 83.67363611675667),
                    LatLng(53.28239423191965, 83.67131561433406)
                )
        )
        polyline2.tag = "пос.Октябрьский - Завод:автобус ПАЗ33205(Е 268 УС 22)"
        stylePolyline(polyline2)

        val polygon1 = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                    LatLng(53.33815389590934, 83.77469414253068),
                    LatLng(53.342736564102, 83.76908890051887),
                    LatLng(53.34777270096085, 83.7634810309036),
                    LatLng(53.350401077285724, 83.76274382680981),
                    LatLng(53.354456568456875, 83.76890323434378),
                    LatLng(53.351428410179736, 83.7729372718316),
                    LatLng(53.34307997814641, 83.78186785099385),
                    LatLng(53.33248619089282, 83.7882962541985),
                    LatLng(53.3237970392562, 83.79535792325308),
                    LatLng(53.31315236824314, 83.78073043001066),
                    LatLng(53.30825573406941, 83.77398707846207),
                    LatLng(53.29138198449919, 83.7586051010744),
                    LatLng(53.28570365063831, 83.75319049329742),
                    LatLng(53.28231018114933, 83.67123238454016)
                )
        )
        polygon1.tag = "alpha"
        stylePolygon(polygon1)

        val polygon2 = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                    LatLng(53.30631092891604, 83.55628527874524),
                    LatLng(53.307591952568245, 83.62088760461215),
                    LatLng(53.28785179264473, 83.64277871587521),
                    LatLng(53.28950194880267, 83.6475506372007),
                    LatLng(53.289468825265246, 83.64754691764256),
                    LatLng(53.28788240803803, 83.65638877113003),
                    LatLng(53.289233890851605, 83.66180870262205),
                    LatLng(53.28955421970917, 83.67363611675667),
                    LatLng(53.28239423191965, 83.67131561433406)
                )
        )
        polygon2.tag = "beta"
        stylePolygon(polygon2)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.684, 133.903), 4f))

//        googleMap.setOnPolylineClickListener(this)
//        googleMap.setOnPolygonClickListener(this)
    }

}