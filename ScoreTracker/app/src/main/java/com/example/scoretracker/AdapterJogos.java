package com.example.scoretracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.classes.EquipaClass;
import com.example.scoretracker.classes.Jogo;

import java.util.ArrayList;

public class AdapterJogos extends RecyclerView.Adapter<AdapterJogos.ExampleViewHolder> {

    private ArrayList<Jogo> mJogos = new ArrayList<>();
    private AdapterJogos.OnClickListener mOnClickListener;

    public AdapterJogos(ArrayList<Jogo> listaJogos, AdapterJogos.OnClickListener mOnClickListener) {
        mJogos = listaJogos;
        this.mOnClickListener = mOnClickListener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;

        public AdapterJogos.OnClickListener onClickListener;

        public ExampleViewHolder(View itemView, AdapterJogos.OnClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.date);
            mTextView2 = itemView.findViewById(R.id.Equipa1);
            mTextView3 = itemView.findViewById(R.id.Golos1);
            mTextView4 = itemView.findViewById(R.id.Golos2);
            mTextView5 = itemView.findViewById(R.id.Equipa2);


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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jogo,parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mOnClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdapterJogos.ExampleViewHolder holder, int position) {
        Jogo currentItem = mJogos.get(position);

        holder.mTextView1.setText(String.valueOf(currentItem.getDate()));
        holder.mTextView2.setText(currentItem.getTeam1());
        holder.mTextView3.setText(String.valueOf(currentItem.getGoal1()));
        holder.mTextView4.setText(String.valueOf(currentItem.getGoal2()));
        holder.mTextView5.setText(String.valueOf(currentItem.getTeam2()));



    }


    @Override
    public int getItemCount() {
        return mJogos.size();
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