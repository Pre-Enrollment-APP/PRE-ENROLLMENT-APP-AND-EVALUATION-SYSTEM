package com.example.cccpre_enrollmentapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleView_Config {
    private Context mContext;
    private GradeAdapter mGradeAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Grades> grade, List<String> keys) {
        mContext = context;
        mGradeAdapter = new GradeAdapter(grade, keys);
        recyclerView.setLayoutManager( new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mGradeAdapter);
    }


    class GradeList extends RecyclerView.ViewHolder {
        private TextView code;
        private TextView description;
        private TextView finalgrade;
        private String key;

        public GradeList(View view) {
            super(view);

            code = itemView.findViewById(R.id.subcode);
            description = itemView.findViewById(R.id.description);
            finalgrade = itemView.findViewById(R.id.grades);


        }

        public void bind(Grades grade, String key) {
            code.setText(grade.getCourse_Code());
            description.setText(grade.getDesc_title());
            finalgrade.setText(grade.getGrade());
        }


    }

    class GradeAdapter extends RecyclerView.Adapter<GradeList> {
        private List<Grades> gradelist;
        private List<String> mKeys;

        public GradeAdapter(List<Grades> gradelist, List<String> mKeys) {
            this.gradelist = gradelist;
            this.mKeys = mKeys;


        }

        @NonNull
        @Override
        public GradeList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(mContext).inflate(R.layout.gradesentry,parent,false);
            return new GradeList(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GradeList holder, int position) {
            holder.bind(gradelist.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return gradelist.size();
        }
    }

}




