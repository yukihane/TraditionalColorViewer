package jp.classmethod.android.sample.fragmentpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.classmethod.android.sample.traditionalcolorviewer.R;

/**
 * 色情報を表示する Fragment.
 */
public class TraditionalColorFragment extends Fragment {

    private static final String TAG = "EVENT";

    private int page = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView page: " + page);

        // データを取得
        Bundle bundle = getArguments();
        int page = bundle.getInt("page");
        String color = bundle.getString("color");
        String name = bundle.getString("name");
        String description = bundle.getString("description");

        // View をつくる
        View layout = inflater.inflate(R.layout.fragment_traditional_color, container, false);
        layout.setBackgroundColor(Color.parseColor(color));

        // 色名
        TextView nameView = (TextView) layout.findViewById(R.id.name_view);
        nameView.setText(name);
        // 説明 (色名の由来)
        TextView textView = (TextView) layout.findViewById(R.id.text_view);
        textView.setText(description);
        // 色コード
        TextView colorView = (TextView) layout.findViewById(R.id.color_view);
        colorView.setText(color);
        // ページ
        TextView pageView = (TextView) layout.findViewById(R.id.page_view);
        pageView.setText(String.valueOf(page));

        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        page = bundle.getInt("page");

        Log.i(TAG, "onCreate page: " + page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView page: " + page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy page: " + page);
    }
}
