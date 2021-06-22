package com.example.scoretracker;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.R;
import com.example.scoretracker.classes.FavoritoItem;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavoritos extends RecyclerView.Adapter<com.example.scoretracker.AdapterFavoritos.ExampleViewHolder> implements Filterable{

    private ArrayList<FavoritoItem> mLigas = new ArrayList<>();
    private ArrayList<FavoritoItem> mLigaSearch = new ArrayList<>();
    private com.example.scoretracker.AdapterFavoritos.OnClickListener mOnClickListener;

    public AdapterFavoritos(ArrayList<FavoritoItem> listaLigas, com.example.scoretracker.AdapterFavoritos.OnClickListener mOnClickListener) {
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
            List<FavoritoItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mLigaSearch);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (FavoritoItem i : mLigaSearch){
                    if(i.getClube().toLowerCase().contains(filterPattern) || i.getDesporto().toLowerCase().contains(filterPattern)){
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

        public ExampleViewHolder(View itemView, com.example.scoretracker.AdapterFavoritos.OnClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.club_badge);
            mTextView1 = itemView.findViewById(R.id.Equipa1);
            mTextView2 = itemView.findViewById(R.id.desporto);
            mImageView2 = itemView.findViewById(R.id.favs);

            itemView.setOnClickListener(v ->
                    listener.OnItemListener(getAdapterPosition())
            );

            mImageView2.setOnClickListener(v ->
                    listener.OnFavListener(getAdapterPosition())
            );
        }

    }


    @Override
    public com.example.scoretracker.AdapterFavoritos.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav,parent, false);
        com.example.scoretracker.AdapterFavoritos.ExampleViewHolder evh = new com.example.scoretracker.AdapterFavoritos.ExampleViewHolder(v, mOnClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(com.example.scoretracker.AdapterFavoritos.ExampleViewHolder holder, int position) {
        FavoritoItem currentItem = mLigas.get(position);

        holder.mImageView.setImageResource(currentItem.getClub_img());
        holder.mTextView1.setText(currentItem.getClube());
        holder.mTextView2.setText(currentItem.getDesporto());
        holder.mImageView2.setImageResource(currentItem.getFav_img());

    }

    @Override
    public int getItemCount() { return mLigas.size(); }

    public static class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

}
