package com.sel.smartfood.ui.shopcart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.sel.smartfood.R;
import com.sel.smartfood.ui.shop.ShopFragment;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends Fragment {

    ListView    lv_shopcart;
    TextView    tv_notification;
    TextView    tv_total_price;
    Button      btn_payment;
    Button      btn_continue;
    Toolbar     toolbar_shopcart;
    ShopCartAdapter shopCartAdapter;


    public ShopCartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        Maps(view);
        CheckData();
        EventUtil();
        
        return view;
    }

    private void EventUtil() {
        int total_price = 0;
        for (int i = 0; i < ShopFragment.orderProductList.size(); ++i){
            total_price += ShopFragment.orderProductList.get(i).getProduct_price();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_total_price.setText("Giá: " + decimalFormat.format(total_price) + " Đ");
    }

    private void CheckData() {
        if (ShopFragment.orderProductList.size() <= 0) {
            // update Adapter
            shopCartAdapter.notifyDataSetChanged();
            tv_notification.setVisibility(View.VISIBLE);
            lv_shopcart.setVisibility(View.INVISIBLE);
        }else{
            // update Adapter
            shopCartAdapter.notifyDataSetChanged();
            tv_notification.setVisibility(View.INVISIBLE);
            lv_shopcart.setVisibility(View.VISIBLE);

        }
    }

    private void Maps(View view) {
        lv_shopcart = (ListView) view.findViewById(R.id.lv_shopcart);
        tv_notification = (TextView) view.findViewById(R.id.tv_shopcart_notification);
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        btn_payment = (Button) view.findViewById(R.id.btn_payment);
        btn_continue = (Button) view.findViewById(R.id.btn_shopcart_continue);
//        toolbar_shopcart = (Toolbar) view.findViewById(R.id.toolbar_shopcart);

        shopCartAdapter = new ShopCartAdapter( this.getActivity(), ShopFragment.orderProductList);
        lv_shopcart.setAdapter(shopCartAdapter);



    }
}
