package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = this.findViewById(R.id.recyclerView);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // 设置 item 增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mList = getList();
        mHomeAdapter = new HomeAdapter(this, mList);
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("userId", mList.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mHomeAdapter);

        SearchView searchView = findViewById(R.id.searchview);
        //设置该SearchView默认是否自动缩小为图标
        searchView.setIconifiedByDefault(false);
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("search please!");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //单机搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                //实际应用中应该在该方法内执行实际查询，此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(MainActivity.this, "你的选择是：" + query,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            //用户输入字符时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> filterString=filter(mList,newText);
                mHomeAdapter.setFilter(filterString);
                return true;
            }
        });
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("518021910"+i);
        }
        return list;
    }
    private List<String>filter(List<String>strings,String text){
        List<String> filterString=new ArrayList<>();

        for (String word:mList){
            if (word.contains(text))
                filterString.add(word);
        }
        return filterString;
    }
}
