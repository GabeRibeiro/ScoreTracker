package com.example.scoretracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.classes.Liga;

import java.util.ArrayList;
import java.util.List;

public class AdapterRVLigas_Futebol extends RecyclerView.Adapter<AdapterRVLigas_Futebol.ExampleViewHolder> implements Filterable{

    private ArrayList<Liga> mLigas = new ArrayList<>();
    private ArrayList<Liga> mLigaSearch = new ArrayList<>();
    private AdapterRVLigas_Futebol.OnClickListener mOnClickListener;

    public AdapterRVLigas_Futebol(ArrayList<Liga> listaLigas, AdapterRVLigas_Futebol.OnClickListener mOnClickListener) {
        mLigas = listaLigas;
        this.mOnClickListener = mOnClickListener;
        mLigaSearch = new ArrayList<>(mLigas);
    }

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Liga> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mLigaSearch);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Liga i : mLigaSearch){
                    if(i.getLiga_txt().toLowerCase().contains(filterPattern) || i.getPais_txt().toLowerCase().contains(filterPattern)){
                        filteredList.add(i);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mLigas.clear();
            mLigas.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };


    public interface OnClickListener {
        void OnItemListener(int position);
        void OnFavListener(int position);
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mImageView2;

        public ExampleViewHolder(View itemView, AdapterRVLigas_Futebol.OnClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image1);
            mTextView1 = itemView.findViewById(R.id.pais1);
            mTextView2 = itemView.findViewById(R.id.liga1);
            mImageView2 = itemView.findViewById(R.id.star2);

            itemView.setOnClickListener(v ->
                    listener.OnItemListener(getAdapterPosition())
            );

            mImageView2.setOnClickListener(v ->
                    listener.OnFavListener(getAdapterPosition())
            );
        }

    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligas_list,parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mOnClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdapterRVLigas_Futebol.ExampleViewHolder holder, int position) {
        Liga currentItem = mLigas.get(position);

        holder.mImageView.setImageResource(currentItem.getPais_img());
        holder.mTextView1.setText(currentItem.getPais_txt());
        holder.mTextView2.setText(currentItem.getLiga_txt());
        holder.mImageView2.setImageResource(currentItem.getFav_img());

    }

    @Override
    public int getItemCount() { return mLigas.size(); }


}
