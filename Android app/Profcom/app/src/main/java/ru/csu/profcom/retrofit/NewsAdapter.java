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

public class NewsAdapter extends BaseAdapter {

    Context context;
    List<News> newsList;
    private static LayoutInflater inflater = null;

    public NewsAdapter(Context context, List<News> newsList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.newsList = newsList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return newsList.get(position);
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
            vi = inflater.inflate(R.layout.news_row, null);
        TextView content = (TextView) vi.findViewById(R.id.contentTextView);
        TextView author = (TextView) vi.findViewById(R.id.authorTextView);
        ImageView image = (ImageView) vi.findViewById(R.id.newsImageView);
        TextView datetime = (TextView) vi.findViewById(R.id.newsDateTime);

        author.setText(newsList.get(position).getUser().getSurName() + " " + newsList.get(position).getUser().getFirstName());
        content.setText(newsList.get(position).getMessage());
        // FIXME: 21.12.2016 FIX MYSQL DATETIME CONVERTING TO JAVA DATE
        //datetime.setText(newsList.get(position).getTestDate().getDate() + "." + (newsList.get(position).getTestDate().getMonth() + 1) + "." + (newsList.get(position).getTestDate().getYear() + 1900)
        //        + "\t\t" + getTimeInteger(newsList.get(position).getTestDate().getHours()) + ":" + getTimeInteger(newsList.get(position).getTestDate().getMinutes()));
        // FIXME: 21.12.2016 FIND A WAY TO DECODE BLOB MYSQL DATA TO IMAGE
        if (newsList.get(position).getImage() != null) {
            byte[] bytes = newsList.get(position).getImage().getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            /*Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            bitmap.copyPixelsFromBuffer(buffer); // 1000000*/
            image.setImageBitmap(bitmap);
        }
        return vi;
    }

    private String getTimeInteger(int integer) {
        if (integer >= 0 && integer < 10) {
            return "0" + integer;
        } else {
            return String.valueOf(integer);
        }
    }
}