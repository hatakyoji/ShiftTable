package kyoji.shifttable;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends ListFragment {


    private ItemListAdapter adapter;

    public HomeActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set adapter
        adapter = new ItemListAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.add("ばっしーさん", false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    public void addListItem(){
        adapter.add("No Name" , false);
    }


}
