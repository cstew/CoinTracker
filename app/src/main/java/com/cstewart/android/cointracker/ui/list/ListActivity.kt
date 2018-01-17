package com.cstewart.android.cointracker.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cstewart.android.cointracker.CoinApplication
import com.cstewart.android.cointracker.R
import com.cstewart.android.cointracker.data.CoinRepository
import com.cstewart.android.cointracker.data.database.model.Symbol
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: CoinRepository

    var recyclerView: RecyclerView? = null
    var adapter: SymbolAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        CoinApplication.graph.inject(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        adapter = SymbolAdapter(emptyList())
        recyclerView!!.adapter = adapter

        val factory = ListViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ListViewModel::class.java)

        viewModel.getSymbols().observe(this, Observer {
            adapter?.swapSymbols(it!!)
        })
    }

    class SymbolAdapter(var symbols: List<Symbol>) : RecyclerView.Adapter<SymbolHolder>() {
        override fun onBindViewHolder(holder: SymbolHolder?, position: Int) {
            holder!!.textView.text = symbols[position].name
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SymbolHolder {
            val inflater = LayoutInflater.from(parent!!.context)
            val view = inflater.inflate(R.layout.list_item_symbol, parent, false)
            return SymbolHolder(view)
        }

        override fun getItemCount(): Int {
            return symbols.size
        }

        fun swapSymbols(symbols: List<Symbol>) {
            this.symbols = symbols
            notifyDataSetChanged()
        }
    }

    class SymbolHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
        }

    }
}
