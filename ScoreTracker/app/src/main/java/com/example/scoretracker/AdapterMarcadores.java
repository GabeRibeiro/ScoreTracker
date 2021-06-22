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

import com.example.scoretracker.classes.Jogador_stats;

import java.util.ArrayList;

public class AdapterMarcadores extends RecyclerView.Adapter<com.example.scoretracker.AdapterMarcadores.ExampleViewHolder> /*implements Filterable*/ {

        private ArrayList<Jogador_stats> mJogadores = new ArrayList<>();
        private ArrayList<Jogador_stats> mJogadorSearch = new ArrayList<>();
        private com.example.scoretracker.AdapterMarcadores.OnClickListener mOnClickListener;

        public AdapterMarcadores(ArrayList<Jogador_stats> jogador_stats, com.example.scoretracker.AdapterMarcadores.OnClickListener mOnClickListener) {
            mJogadores = jogador_stats;
            this.mOnClickListener = mOnClickListener;
            mJogadorSearch = new ArrayList<>(mJogadores);
        }

        /*@Override
        public Filter getFilter() {
            return filtro;
        }

        private Filter filtro = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Jogador_stats> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(mJogadorSearch);
                }
                else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Jogador_stats i : mJogadorSearch){
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
                mJogadores.clear();
                mJogadores.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };*/


        public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView pos;
            public ImageView nation;
            public TextView nome;
            public TextView club;
            public TextView goals;
            public TextView assists;
            public com.example.scoretracker.AdapterMarcadores.OnClickListener onClickListener;

            public ExampleViewHolder(View itemView, com.example.scoretracker.AdapterMarcadores.OnClickListener listener) {
                super(itemView);
                pos = itemView.findViewById(R.id.date);
                nation = itemView.findViewById(R.id.club_badge);
                nome = itemView.findViewById(R.id.Equipa1);
                club = itemView.findViewById(R.id.clube);
                goals = itemView.findViewById(R.id.goals);
                assists = itemView.findViewById(R.id.assists);
                this.onClickListener = listener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                onClickListener.OnClickListener(getAdapterPosition());

            }
        }


        @Override
        public com.example.scoretracker.AdapterMarcadores.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marcadores,parent, false);
            com.example.scoretracker.AdapterMarcadores.ExampleViewHolder evh = new com.example.scoretracker.AdapterMarcadores.ExampleViewHolder(v, mOnClickListener);
            return evh;
        }

        @Override
        public void onBindViewHolder(com.example.scoretracker.AdapterMarcadores.ExampleViewHolder holder, int position) {
            Jogador_stats currentItem = mJogadores.get(position);



            holder.nation.setImageResource(currentItem.getPais());
            holder.nome.setText(currentItem.getNome());
            holder.club.setText(String.valueOf(currentItem.getClube()));
            holder.goals.setText(String.valueOf(currentItem.getGolos()));
            holder.assists.setText(String.valueOf(currentItem.getAssists()));
            holder.pos.setText(String.valueOf(position+1));

        }


        @Override
        public int getItemCount() {
            return mJogadores.size();
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

