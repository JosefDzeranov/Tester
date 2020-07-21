package com.ioio.tester;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ioio.tester.db.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Question> list = new ArrayList<>();
    private TestListener listener;

    public Adapter(List<Question> items, TestListener l) {
        list.addAll(items);
        listener = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private Question question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ((RadioGroup) view.findViewById(R.id.radioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    boolean isRight = false;
                    if (view.findViewById(i).getTag() != null) isRight = true;
                    listener.onChange(question.number, isRight);
                }
            });
        }

        public void bind(Question question) {
            this.question = question;
            ((TextView) view.findViewById(R.id.question)).setText("Вопрос: " + question.question);
            RadioButton answer1 = view.findViewById(R.id.answer1);
            answer1.setText(question.answers.get(0));
            if (question.answers.get(0).equals(question.rightAnswer)) answer1.setTag(true);

            RadioButton answer2 = view.findViewById(R.id.answer2);
            if (question.answers.get(1).equals(question.rightAnswer)) answer2.setTag(true);

            RadioButton answer3 = view.findViewById(R.id.answer3);
            if (question.answers.get(2).equals(question.rightAnswer)) answer3.setTag(true);

            RadioButton answer4 = view.findViewById(R.id.answer4);
            if (question.answers.get(3).equals(question.rightAnswer)) answer4.setTag(true);

            answer1.setText(question.answers.get(0));
            answer2.setText(question.answers.get(1));
            answer3.setText(question.answers.get(2));
            answer4.setText(question.answers.get(3));
        }
    }
}
