package ru.csu.profcom.retrofit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.csu.profcom.R;

public class QuestionAdapter extends BaseAdapter {

    Context context;
    List<Question> questions;
    private static LayoutInflater inflater = null;

    public QuestionAdapter(Context context, List<Question> newsList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.questions = newsList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.question_row, null);

        TextView question = (TextView) vi.findViewById(R.id.questionTextView);
        TextView answerer = (TextView) vi.findViewById(R.id.answererTextView);
        TextView answer = (TextView) vi.findViewById(R.id.answerTextView);

        question.setText("Вопрос: " + questions.get(position).getQuestion());
        if (questions.get(position).getAnswerer() != null) {
            answerer.setText("Ответил(а): " + questions.get(position).getAnswerer().getSurName() + " " + questions.get(position).getAnswerer().getFirstName());
            answer.setText("Ответ: " + questions.get(position).getAnswer());
        } else {
            answerer.setText("К сожалению, на Ваш вопрос до сих пор никто не ответил. :(");
            answer.setText("");
        }
        return vi;
    }
}