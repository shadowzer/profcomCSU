package ru.csu.profcom.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.csu.profcom.R;

public class CategoryAdapter extends BaseAdapter {

    Context context;
    List<Category> allCategories;
    List<Category> userCategories;
    HashMap<Category, CheckBox> checked;
    private static LayoutInflater inflater = null;

    public CategoryAdapter(Context context, List<Category> allCategories, List<Category> userCategories) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.allCategories = allCategories;
        this.userCategories = userCategories;
        this.checked  = new HashMap<>();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setCheckedItem(Category category, CheckBox checkBox, Boolean isChecked) {
        if (!isChecked && checked.containsKey(category)){
            checked.remove(category);
        } else {
            checked.put(category, checkBox);
        }
    }

    public HashMap<Category, CheckBox> getCheckedItems(){
        return checked;
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
        return allCategories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.category_row, null);
        final CheckBox checkBox = (CheckBox) vi.findViewById(R.id.categoryCheckBox);
        checkBox.setText(allCategories.get(position).getName());
        checkBox.setChecked(false);
        checkBox.setTag(allCategories.get(position));
        for (int i = 0; i < userCategories.size(); ++i) {
            //if (userCategories.get(i).getId().equals(((Category)checkBox.getTag()).getId())) {
            if (userCategories.get(i).getId() == allCategories.get(position).getId()) {
                checkBox.setChecked(true);
                setCheckedItem(allCategories.get(position), checkBox, true);
                break;
            }
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckedItem((Category) buttonView.getTag(), (CheckBox)buttonView, isChecked);
            }
        });
        return vi;
    }
}