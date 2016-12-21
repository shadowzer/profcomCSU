package ru.csu.profcom.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ru.csu.profcom.R;

public class CategoryAdapter extends BaseAdapter {

    Context context;
    List<Category> allCategories;
    List<Category> userCategories;
    private static LayoutInflater inflater = null;

    public CategoryAdapter(Context context, List<Category> allCategories, List<Category> userCategories) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.allCategories = allCategories;
        this.userCategories = userCategories;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return allCategories.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return allCategories.get(position);
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
            vi = inflater.inflate(R.layout.category_row, null);
        CheckBox checkBox = (CheckBox) vi.findViewById(R.id.categoryCheckBox);
        checkBox.setText(allCategories.get(position).getName());
        boolean flag = false;
        for (int i = 0; i < userCategories.size(); ++i) {
            if (userCategories.get(i).getName().equals(checkBox.getText())) {
                checkBox.setChecked(true);
                flag = true;
                break;
            }
        }
        if (!flag)
            checkBox.setChecked(false);
        return vi;
    }
}