package ru.csu.profcom.retrofit;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import ru.csu.profcom.R;
import ru.csu.profcom.SavePhotoTask;

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

        if (newsList.get(position).getImage() != null) {
            byte[] bytes = Base64.decode(newsList.get(position).getImage(), Base64.DEFAULT);
            String fileName = newsList.get(position).getTitle() + newsList.get(position).getDate() + ".jpeg";
            SavePhotoTask savePhotoTask = new SavePhotoTask(fileName, vi.getContext());
            savePhotoTask.execute(bytes);

            String path = Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
            //String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + fileName;
            DisplayMetrics displaymetrics = new DisplayMetrics();
            ((Activity)vi.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int width = displaymetrics.widthPixels;
            Picasso.with(vi.getContext())
                    .load(new File(path))
                    .resize(width, width)
                    .centerCrop()
                    .into(image);
            /*Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            image.setImageBitmap(bitmap);*/
        }
        return vi;
    }


}