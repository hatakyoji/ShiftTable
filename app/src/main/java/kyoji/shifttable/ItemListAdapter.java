package kyoji.shifttable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luckymint on 2017/03/31.
 */

public class ItemListAdapter extends ArrayAdapter {

    // 見易さのために定義。普段は直接 getView で指定する。
    private static final int resource = R.layout.custom_listview_item;

    public ItemListAdapter(Context context){
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // super.getView() は 呼ばない(カスタムビューにしているため)
        View view;

        // テンプレート処理。
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        // データをgetItemで取る
        Item item = (Item) getItem(position);

        // カスタムビューの場合はViewが確実にあるtry-catch は不要ためか。
        TextView id = (TextView) view.findViewById(R.id.user_name);
        id.setText(item.user_name);
        TextView name = (TextView) view.findViewById(R.id.input_state);
        if(item.input_state){
            name.setText("入力完了");
        }else{
            name.setText("未入力");
        }

        return view;
    }

    // 設定されている CustomListItem の ArrayList を返す。
    // 縦横切替などでデータを移行するために使う。
    public ArrayList<Item> getItemList() {
        // 今回は Bundle#putParcelableArrayList() を使うことを想定する。
        // 必要に応じて Bundle#putSparseParcelableArray() を使ってもいい。

        int size = getCount();
        ArrayList<Item> itemList = new ArrayList<Item>(size);
        for (int index = 0; index < size; index++) {
            itemList.add((Item) getItem(index));
        }
        return itemList;
    }

    // Bundleから復元するときに必要になるはず。
    public void addAll(ArrayList<Item> parcelableArrayList) {
        // 強制でキャスト。落ちる場合は、設計か実装が間違っている。
        @SuppressWarnings("unchecked")
        ArrayList<Item> itemList = (ArrayList<Item>) parcelableArrayList;
        super.addAll(itemList);
    }

    public void add(String user_name, Boolean input_state) {
        Item item = new Item(user_name, input_state);
        super.add(item);
    }

    // 削除
    public void remove(int index) {
        if (index < 0 || index >= getCount()) {
            return;
        }
        remove(getItem(index));
    }

}

