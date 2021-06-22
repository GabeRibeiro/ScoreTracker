package com.example.scoretracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.classes.Desporto;

import java.util.ArrayList;
import java.util.List;


public class AdapterRVDesportos extends RecyclerView.Adapter<com.example.scoretracker.AdapterRVDesportos.ExampleViewHolder> implements Filterable {

        private ArrayList<Desporto> mDesportos = new ArrayList<>();
        private ArrayList<Desporto> mDesportosSearch = new ArrayList<>();
        private OnClickListener mOnClickListener;

        public AdapterRVDesportos(ArrayList<Desporto> listaDesportos, OnClickListener mOnClickListener) {
            mDesportos = listaDesportos;
            this.mOnClickListener =  mOnClickListener;
            mDesportosSearch = new ArrayList<>(mDesportos);
        }

        @Override
        public Filter getFilter() {
            return filtro;
        }

        private Filter filtro = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Desporto> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(mDesportosSearch);
                }
                else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Desporto i : mDesportosSearch){
                        if(i.getNome().toLowerCase().contains(filterPattern)){
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
                mDesportos.clear();
                mDesportos.addAll((List)results.values);
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
                public ImageView mImageView2;
                public ExampleViewHolder(View itemView, final OnClickListener listener) {
                    super(itemView);
                    mImageView = itemView.findViewById(R.id.image_d1);
                    mTextView1 = itemView.findViewById(R.id.nome1);
                    mImageView2 = itemView.findViewById(R.id.star);

                    itemView.setOnClickListener(v ->
                            listener.OnItemListener(getAdapterPosition())
                    );

                    mImageView2.setOnClickListener(v ->
                            listener.OnFavListener(getAdapterPosition())
                    );



                }


        }




        @Override
        public AdapterRVDesportos.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.desportos_list,parent, false);
            AdapterRVDesportos.ExampleViewHolder evh = new AdapterRVDesportos.ExampleViewHolder(v, mOnClickListener);
            return evh;
        }

        @Override
        public void onBindViewHolder(AdapterRVDesportos.ExampleViewHolder holder, int position) {
            Desporto currentItem = mDesportos.get(position);

            holder.mImageView.setImageResource(currentItem.getDesporto_img());
            holder.mTextView1.setText(currentItem.getNome());
            holder.mImageView2.setImageResource(currentItem.getFav_img());


        }
        @Override
        public int getItemCount() {
            return mDesportos.size();
        }



    }

