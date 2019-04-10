package in.compunet.pillaimatrimony.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import in.compunet.pillaimatrimony.R;
import in.compunet.pillaimatrimony.model.CheckboxModel;


public class RecyclerCheckboxAdapter extends RecyclerView.Adapter<RecyclerCheckboxAdapter.ViewHolder> {


    private ArrayList<CheckboxModel> checkboxModelArrayList;
    private Activity activity;
    private CheckboxModel checkboxModel;

    public ArrayList<CheckboxModel> getCheckboxModelArrayList() {
        return this.checkboxModelArrayList;
    }

    public void setCheckboxModelArrayList(ArrayList<CheckboxModel> checkboxModelArrayList) {
        this.checkboxModelArrayList = checkboxModelArrayList;
    }
    public RecyclerCheckboxAdapter(Activity activity, ArrayList<CheckboxModel> checkboxModelArrayList) {
        this.activity = activity;
        this.checkboxModelArrayList = checkboxModelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_checkbox_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        checkboxModel = checkboxModelArrayList.get(position);
        viewHolder.txt_title.setText(checkboxModel.getStrTitle());
        viewHolder.checkbox.setChecked(checkboxModelArrayList.get(position).isTitleChecked());
        viewHolder.checkbox.setTag(position);
        viewHolder.rc_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) viewHolder.checkbox.getTag();
                    if(pos==0) {
                        if (checkboxModelArrayList.get(pos).isTitleChecked()) {
                            checkboxModelArrayList.get(pos).setTitleChecked(false);
                        } else {
                            checkboxModelArrayList.get(pos).setTitleChecked(true);
                            for(int i=1;i<checkboxModelArrayList.size();i++){
                                checkboxModelArrayList.get(i).setTitleChecked(false);
                            }

                        }
                    }
                    else{
                        if(checkboxModelArrayList.get(0).isTitleChecked()){
                            checkboxModelArrayList.get(0).setTitleChecked(false);
                        }
                        if (checkboxModelArrayList.get(pos).isTitleChecked()) {
                            checkboxModelArrayList.get(pos).setTitleChecked(false);
                        } else {
                            checkboxModelArrayList.get(pos).setTitleChecked(true);
                        }

                    }
                    notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
           return checkboxModelArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_title;
        private CheckBox checkbox;
        private CardView rc_card_view;
        ViewHolder(View view) {
            super(view);
            checkbox=view.findViewById(R.id.checkbox);
            txt_title=view.findViewById(R.id.txt_title);
            rc_card_view=view.findViewById(R.id.rc_card_view);
        }
    }

}
