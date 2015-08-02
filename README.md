[Android Fragment で onCreateView が呼ばれずに onDestroy が呼ばれることはありますか - スタック・オーバーフロー](http://ja.stackoverflow.com/a/12777/2808)
で頂いた回答を再現するためのコードです。

イチからソースを作るのが面倒だったので、[こちらのソース](http://dev.classmethod.jp/smartphone/android/android-tips-31-fragment-pager-adapter/)を流用させて頂いています。
https://github.com/suwa-yuki/TraditionalColorViewer


# 確認方法

動作させる端末やエミュレーターの設定で、アクティビティを保持しないようにします。
Nexus5では以下の箇所にチェックを入れることになります。
設定 > 開発者向けオプション > アクティビティを保持しない

ログは EVENT タグで出力しているので、これでフィルタすると見やすいです。

アプリを起動し、数ページ移動します。
ページを繰るごとにonCreateとonCreateViewが呼ばれます。
<pre>
I/EVENT﹕ onCreate page: 0
I/EVENT﹕ onCreateView page: 0
I/EVENT﹕ onCreate page: 1
I/EVENT﹕ onCreateView page: 1
I/EVENT﹕ onCreate page: 2
I/EVENT﹕ onCreateView page: 2
I/EVENT﹕ onDestroyView page: 0
I/EVENT﹕ onCreate page: 3
I/EVENT﹕ onCreateView page: 3
I/EVENT﹕ onDestroyView page: 1
I/EVENT﹕ onCreate page: 4
I/EVENT﹕ onCreateView page: 4
</pre>


ホームボタンを押してホームに戻ります。
onCreate, onCreateViewが呼ばれたものに対してonDestroyView, onDestroyが呼ばれます。
<pre>
I/EVENT﹕ onDestroy page: 0
I/EVENT﹕ onDestroy page: 1
I/EVENT﹕ onDestroyView page: 2
I/EVENT﹕ onDestroy page: 2
I/EVENT﹕ onDestroyView page: 3
I/EVENT﹕ onDestroy page: 3
I/EVENT﹕ onDestroyView page: 4
I/EVENT﹕ onDestroy page: 4
</pre>

アプリを再度起動します。
現在表示されているページより前のページのonCreateと、現在ページ付近のonCreateViewが呼ばれます。
<pre>
I/EVENT﹕ onCreate page: 0
I/EVENT﹕ onCreate page: 1
I/EVENT﹕ onCreate page: 2
I/EVENT﹕ onCreate page: 3
I/EVENT﹕ onCreate page: 4
I/EVENT﹕ onCreateView page: 2
I/EVENT﹕ onCreateView page: 3
I/EVENT﹕ onCreateView page: 4
</pre>

再度ホームボタンを押します。
onCreateが呼ばれたページではonDestroyが呼ばれます。
それに加えて、onCreateViewが呼ばれたページではonDestroyViewが呼ばれます。
<pre>
I/EVENT﹕ onDestroy page: 0
I/EVENT﹕ onDestroy page: 1
I/EVENT﹕ onDestroyView page: 2
I/EVENT﹕ onDestroy page: 2
I/EVENT﹕ onDestroyView page: 3
I/EVENT﹕ onDestroy page: 3
I/EVENT﹕ onDestroyView page: 4
I/EVENT﹕ onDestroy page: 4
</pre>

上のログにおいて、page 0と1では onCreateView及びonDestroyViewが呼ばれずにonDestroyされています。
