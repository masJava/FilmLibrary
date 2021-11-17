package mas.com.filmLib.view.descriptionscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import mas.com.filmLib.R
import mas.com.filmLib.databinding.ActivityDescriptionBinding
import mas.com.filmLib.model.data.DataModelFilm
import mas.com.filmLib.utils.network.isOnline
import mas.com.filmLib.utils.ui.AlertDialogFragment

class DescriptionActivity : AppCompatActivity() {

    private var vb: ActivityDescriptionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        setActionbarHomeButtonAsUp()
        vb?.descriptionScreenSwipeRefreshLayout?.setOnRefreshListener { startLoadingOrShowError() }
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {
        val bundle = intent.extras
        val gson = Gson()
        val filmData: DataModelFilm =
            gson.fromJson(bundle?.getString(FILM_EXTRA), DataModelFilm::class.java)
        if (filmData == DataModelFilm()) {
            stopRefreshAnimationIfNeeded()
        } else {
            vb?.title?.text = filmData.title
            vb?.overview?.text = filmData.overview
            val runtime = "Продолжительность: %s ч. %s мин.".format(
                filmData.runtime / 60,
                filmData.runtime % 60
            )
            vb?.runtime?.text = runtime
            val context = vb?.root?.context
            if (context != null) {
                vb?.descriptionImageview?.let {
                    usePicassoToLoadPhoto(context, it, filmData.poster_path)
                }
            }
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(applicationContext)) {
            setData()
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun stopRefreshAnimationIfNeeded() {

        vb?.descriptionScreenSwipeRefreshLayout?.let {
            if (it.isRefreshing) {
                it.isRefreshing = false
            }
        }
    }

    private fun usePicassoToLoadPhoto(context: Context, imageView: ImageView, imageLink: String) {
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerInside()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError() {
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"

        private const val FILM_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"

        fun getIntent(
            context: Context,
            dataFilm: String
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(FILM_EXTRA, dataFilm)
        }
    }
}
