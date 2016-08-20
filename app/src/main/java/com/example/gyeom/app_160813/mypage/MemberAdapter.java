package com.example.gyeom.app_160813.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyeom.app_160813.R;
import com.example.gyeom.app_160813.member.MemberBean;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-08-20.
 */
public class MemberAdapter extends BaseAdapter {

    static class ViewHolder{
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPhone;
    }

    ArrayList<MemberBean> list;
    LayoutInflater inflater;

    // alphabet 순서대로
    int[] imgs={
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb,
            R.drawable.icecream,
            R.drawable.jellybean,
            R.drawable.kitkat,
            R.drawable.lollipop
    };

    public MemberAdapter(Context context, ArrayList<MemberBean> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.member_list, null);
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivPhoto.setImageResource(imgs[position]);
        holder.tvName.setText(list.get(position).getName());
        holder.tvPhone.setText(list.get(position).getPhone());

        return convertView;
    }
}
