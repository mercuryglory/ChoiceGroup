package com.example.choicegroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercury.choicegroup.ChoiceGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_tag1)
    TextView     tvTag1;
    @Bind(R.id.choicegroup_bill)
    ChoiceGroup  choicegroupBill;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.tv_tag2)
    TextView     tvTag2;
    @Bind(R.id.choicegroup_bank)
    ChoiceGroup  choicegroupBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        List<String> listBill = new ArrayList<>();
        listBill.add("10万以内");
        listBill.add("10-20万");
        listBill.add("20-30万");
        listBill.add("30-50万");
        listBill.add("50-100万");
        listBill.add("100-500万");
        listBill.add("500万以上");
        choicegroupBill.setColumn(3);
        choicegroupBill.setValues(listBill);
        choicegroupBill.setView(this);

        List<String> listBank = new ArrayList<>();
        listBank.add("国股银行");
        listBank.add("城商银行");
        listBank.add("农商外资");
        listBank.add("农合农信");
        listBank.add("村镇银行");
        choicegroupBank.setColumn(3);
        choicegroupBank.setValues(listBank);
        choicegroupBank.setView(this);

    }
}
