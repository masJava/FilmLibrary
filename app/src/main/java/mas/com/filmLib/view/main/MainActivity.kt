package mas.com.filmLib.view.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import mas.com.filmLib.R
import mas.com.filmLib.databinding.ActivityMainBinding
import mas.com.filmLib.model.data.AppState
import mas.com.filmLib.utils.network.isOnline
import mas.com.filmLib.view.base.BaseActivity
import mas.com.filmLib.view.descriptionscreen.DescriptionActivity
import mas.com.filmLib.view.main.adapter.MainAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private var vb: ActivityMainBinding? = null
    override lateinit var model: MainViewModel
    private var page = 1
    private var maxPage = 1
    private var incrementBt: MenuItem? = null
    private var decrementBt: MenuItem? = null
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(film: Int) {
                model.getDataFilm(film, isOnline(applicationContext))
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        iniViewModel()
        initViews()
        model.getData(page, isOnline(applicationContext))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        decrementBt = menu?.findItem(R.id.decrement)
        incrementBt = menu?.findItem(R.id.increment)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.increment -> if (page <= maxPage) {
                page++
                model.getData(page, isOnline(applicationContext))
            }
            R.id.decrement -> if (page > 1) {
                page--
                model.getData(page, isOnline(applicationContext))
            }
            else -> return false
        }
        return true
    }

    private fun checkMenuButton() {
        incrementBt?.isEnabled = page != maxPage
        decrementBt?.isEnabled = page != 1
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                page = data.page
                maxPage = data.total_pages
                vb?.tvPageNumber?.text = "${getString(R.string.page)} ${data.page}"
                checkMenuButton()
                adapter.setData(data.results)
            }
            is AppState.ToDescriptionActivity -> {
                val newData = appState.data
                Log.d("", newData.toString())
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        newData
                    )
                )
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    vb?.progressBarHorizontal?.visibility = VISIBLE
                    vb?.progressBarRound?.visibility = GONE
                    vb?.progressBarHorizontal?.progress = appState.progress
                } else {
                    vb?.progressBarHorizontal?.visibility = GONE
                    vb?.progressBarRound?.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun iniViewModel() {
        if (vb?.mainActivityRecyclerview?.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })

    }

    private fun initViews() {
        vb?.mainActivityRecyclerview?.layoutManager = LinearLayoutManager(applicationContext)
        vb?.mainActivityRecyclerview?.adapter = adapter
    }

    private fun showViewWorking() {
        vb?.loadingFrameLayout?.visibility = GONE
    }

    private fun showViewLoading() {
        vb?.loadingFrameLayout?.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
