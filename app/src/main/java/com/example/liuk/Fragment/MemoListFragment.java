package com.example.liuk.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuk.Activity.MainActivity;
import com.example.liuk.DB.MemoDB;
import com.example.liuk.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MemoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoListFragment extends ListFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private View.OnClickListener btnAddMemo_clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    public MemoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemoListFragment newInstance(String param1, String param2) {
        MemoListFragment fragment = new MemoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private SimpleCursorAdapter adapter = null;
    private MemoDB db;
    private SQLiteDatabase dbRead;
    TextView textView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        MainActivity activity = (MainActivity)getActivity();
        textView = (TextView)activity.findViewById(R.id.menu_memo);

        db = new MemoDB(getActivity());
        dbRead = db.getReadableDatabase();

        adapter = new SimpleCursorAdapter(getActivity(),R.layout.memo_list_cell,null, new String[] {MemoDB.COLUMN_MEMO_TITLE,MemoDB.COLUMN_MEMO_REMIND_DATE}, new int[]{R.id.memoTitle,R.id.memoDate});
        setListAdapter(adapter);

        refreshMemoListView();


    }

    public  void refreshMemoListView(){
        //adapter.changeCursor(dbRead.query(MemoDB.TABLE_MEMO,null,null,null,null,null,null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo_list, container, false);

        view.findViewById(R.id.btn_add_memo).setOnClickListener(btnAddMemo_clickHandler);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        if(textView != null){
            textView.setTextColor(Color.parseColor("#FF6600"));
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(textView != null){
            textView.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }


}
