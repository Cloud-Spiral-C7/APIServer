// websocketの接続先
// (特殊なプロトコルなのでapache+tomcat連携，つまり50080経由は不可能)
// (普通に直接tomcat，つまり8080経由で呼び出す)
var wsuri = 'ws://' + window.location.hostname + ':8080/' + window.location.pathname.split('/')[1] + '/api/ws';

// websocketオブジェクト
var ws;

// REST APIへのエンドポイント
var endpoint = '../api';

// REST呼び出し
$('#rest').click(function() {
	$.ajax({
		url: endpoint + '/count',
		success: function(xml) {
			// APIで取得した返り値をそのままテキストとして表示
			var count = $(xml).text();
			$('#count').html(count);
		}
	});
});

// 接続押下時
$('#connect').click(function() {
	// WebSocket作成
	ws = new WebSocket(wsuri);

	// WebSocket open時の処理
	ws.onopen = function() {
		$('#log').prepend('チャットに参加しました' + '<br/>');
	};

	// WebSocket message受信時の処理
	ws.onmessage = function(message) {
		console.log(message.data);
		var json = JSON.parse(message.data);
		$('#log').prepend(json.body + '<br/>');
	};

	// WebSocket error時の処理
	ws.onerror = function() {
		$('#log').prepend('接続エラーです' + '<br/>');
	};

	// WebSocket close時のイベントハンドラ登録
	ws.onclose = function() {
		$('#log').prepend('切断されました' + '<br/>');
	};

	// Windowが閉じられた(例：ブラウザを閉じた)時のイベントを設定
	$(window).unload(function() {
		ws.onclose();
	});
});

// send押下時
$('#send').submit(function() {
	// 送るデータを作る
	var uid = $('#uid').val();
	var body = $('#body').val();
	var msg = {uid:uid, body:body};

	// WebSocketを使いメッセージをサーバに送信
	// （JSON.stringifyでJSONをテキストに変換）
	ws.send(JSON.stringify(msg));

	return false;
});