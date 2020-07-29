package com.example.location_vietnam

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_eatting.*

private var linearLayoutManager: LinearLayoutManager? = null
var locationArrayEating = ArrayList<LocationData>()
private var addLocationCode: Int = 1
private var editLocationCode: Int = 2
private var editPositionInLocation: Int = 0

class EattingFragment : Fragment(), onItemClick {

    lateinit var tourAdapter: LocationAdapter

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_eatting, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.tour_rv) as RecyclerView
        linearLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        tourAdapter = LocationAdapter(createLocation(), this)
        tourAdapter.setLocationInterFace(this)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter = tourAdapter

        return rootView

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(tour_rv)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_paste) {
            val intent = Intent(activity as MainActivity, AddLocation::class.java)
            startActivityForResult(intent, addLocationCode)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == addLocationCode && resultCode == Activity.RESULT_OK) {
                val location: LocationData =
                    data.getSerializableExtra("return_location") as LocationData
                tourAdapter.addItem(location)

            } else {
                Log.d("AAA", "CANCEL")
            }
            if (requestCode == editLocationCode && resultCode == Activity.RESULT_OK) {
                val locationEdit: LocationData =
                    data.getSerializableExtra("edit_data") as LocationData
                tourAdapter.updateIteamAtPosidion(editPositionInLocation, locationEdit)
            } else {
                Log.d("AAA", "CANCEL EDIT")
            }

        }
    }


    fun createLocation(): ArrayList<LocationData> {
        var location: LocationData
        for (i in 1..10) {
            location = LocationData(
                "05/03",
                "da lat" + i,
                "da lat tren vui" +
                        "\nciliekc me" +
                        "cuoc phieu luu ky thu",
                "https://akisoto.com/wp-content/uploads/2019/06/Canva-night-light-dalat-city-vietnam-landmark-city-center-1024x683.jpg",
                "05/03/2000",
                "toi" +
                        "\n rat la" +
                        "\n vui",
                R.drawable.editblue,
                R.drawable.shareblue,
                R.drawable.deletblue

            )
            locationArrayEating.add(location)
        }
        return locationArrayEating
    }

    override fun onLocationClick(data: LocationData) {
        val intent = Intent(activity as MainActivity, LocationDetail::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    override fun onDeletItemClick() {
        Toast.makeText(activity as MainActivity, "item deleted", Toast.LENGTH_LONG).show()
    }

    override fun onEditLocationClick(data: LocationData, position: Int) {
        val intent = Intent(activity as MainActivity, EditLocation::class.java)
        intent.putExtra("edit_data", data)
        editPositionInLocation = position
        startActivityForResult(intent, editLocationCode)
    }

    override fun onShareLocationName(locationName: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, locationName)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Please select app: "))
    }

    @SuppressLint("ResourceType")
    override fun onLongClickItem(position: Int, data: LocationData) {
        val popUp = PopupMenu(activity as Activity, view?.findViewById(R.id.root_element))
        popUp.inflate(R.menu.context_menu)
        popUp.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.delete_choice -> {
                        tourAdapter.delteLocation(position)
                        return true
                    }
                    R.id.share_choice -> {
                        onShareLocationName(data.locaName)
                        return true
                    }
                    R.id.edit_choice -> {
                        onEditLocationClick(data, position)
                        return true
                    }
                    else -> return false
                }
            }
        })
        popUp.show()
    }
}
//    companion object{
//        private val LOCATE_DATA = "locationdata"
//        fun newInstance (locationData: LocationData):EattingFragment{
//            val fragment = EattingFragment()
//            val args = Bundle()
//            args.putSerializable(LOCATE_DATA,locationData)
//            fragment.arguments = args
//            return fragment
//        }
//    }


//    fun setFragment(fragmentInter : onItemClick){
//        this.fragmentInter = fragmentInter
//    }

