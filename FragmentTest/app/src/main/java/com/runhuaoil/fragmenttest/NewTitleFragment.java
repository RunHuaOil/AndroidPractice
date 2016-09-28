package com.runhuaoil.fragmenttest;

import android.app.Activity;
import android.app.Fragment;

import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RunHua on 2016/9/27.
 */

public class NewTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private List<News> newList;

    private NewsAdapter newAdapter;

    private boolean TwoPane = false;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newList = getNewsList();
        newAdapter = new NewsAdapter(activity, R.layout.news_item_layout, newList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.new_title_fragment_layout, container, false);

        listView = (ListView) view.findViewById(R.id.list_view_id);
        listView.setAdapter(newAdapter);
        listView.setOnItemClickListener(this);

        return view;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        News new1 = newList.get(position);
        if (TwoPane){
            NewContentFragment newContentFragment = (NewContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newContentFragment.refresh(new1.getTitle(), new1.getContent());
        }else {
            NewsContentActivity.actionStart(getActivity(), new1.getTitle(), new1.getContent());
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null){
            TwoPane = true;
        }else{
            TwoPane = false;
        }
    }

    private List<News> getNewsList() {

        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar dining hall food. Students with learning disabilities will face these transitions while also grappling with a few more hurdles.");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);

        return newsList;
    }



}
