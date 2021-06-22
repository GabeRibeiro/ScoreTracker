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

import com.example.scoretracker.classes.EquipaClass;

import java.util.ArrayList;
import java.util.List;

public class AdapterClassific extends RecyclerView.Adapter<AdapterClassific.ExampleViewHolder> implements Filterable{

    private ArrayList<EquipaClass> mEquipas = new ArrayList<>();
    private ArrayList<EquipaClass> mEquipaSearch = new ArrayList<>();
    private AdapterClassific.OnClickListener mOnClickListener;

    public AdapterClassific(ArrayList<EquipaClass> listaEquipas, AdapterClassific.OnClickListener mOnClickListener) {
        mEquipas = listaEquipas;
        this.mOnClickListener = mOnClickListener;
        mEquipaSearch = new ArrayList<>(mEquipas);
    }

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<EquipaClass> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mEquipaSearch);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (EquipaClass i : mEquipaSearch){
                    if(i.getNome().toLowerCase().contains(filterPattern) ){
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
            mEquipas.clear();
            mEquipas.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };


    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView1;
        public ImageView mImageView;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;
        public TextView mTextView7;
        public AdapterClassific.OnClickListener onClickListener;

        public ExampleViewHolder(View itemView, AdapterClassific.OnClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.date);
            mImageView = itemView.findViewById(R.id.club_badge);
            mTextView2 = itemView.findViewById(R.id.Equipa1);
            mTextView3 = itemView.findViewById(R.id.Golos1);
            mTextView4 = itemView.findViewById(R.id.Golos2);
            mTextView5 = itemView.findViewById(R.id.clube);
            mTextView6 = itemView.findViewById(R.id.goals);
            mTextView7 = itemView.findViewById(R.id.assists);

            this.onClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.OnClickListener(getAdapterPosition());

        }
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mOnClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdapterClassific.ExampleViewHolder holder, int position) {
        EquipaClass currentItem = mEquipas.get(position);

        holder.mTextView1.setText(String.valueOf(currentItem.getPos()));
        holder.mImageView.setImageResource(currentItem.getImg());
        holder.mTextView2.setText(currentItem.getNome());
        holder.mTextView3.setText(String.valueOf(currentItem.getPontos()));
        holder.mTextView4.setText(String.valueOf(currentItem.getM_p()));
        holder.mTextView5.setText(String.valueOf(currentItem.getM_w()));
        holder.mTextView6.setText(String.valueOf(currentItem.getM_d()));
        holder.mTextView7.setText(String.valueOf(currentItem.getM_l()));


    }


    @Override
    public int getItemCount() {
        return mEquipas.size();
    }

    public interface OnClickListener {
        void OnClickListener(int position);
    }

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
