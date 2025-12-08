set names utf8;
set foreign_key_checks = 0;
drop datbase if exists ecsite;

create database if not exists ecsite;
use ecsite;

--ログイン情報
drop table if exists login_user_transaction;

create table login_user_transaction(
	id int not null primary key auto_increment, --登録順の番号
	login_id varchar(16) unique, --ログインID
	login_pass varchar(16), --LOGINパスワード
	user_name varchar(50),--ユーザーネーム
	insert_date datetime,--登録時の日時と時間
	updated_date datetime--更新時の日時と時間
);

--商品情報
drop table if exists item_info_transaction;

create table item_info_transaction(
	id int not null primary key auto_increment,
	item_name varchar(30),--商品の名前
	item_price int,--商品の価格
	item_stock int,--商品の在庫数
	insert_date datetime.
	update_date datetime
);

--購入情報
drop table if exsits user_buy_item_transaction;

create table user_buy_item_transaction(
	id int not null primary key auto_increment,
	item_transaction_id int,
	total_price int,--購入の合計金額
	total_count int,--購入の合計個数
	user_master_id varchar(16),--マスターID
	pay varchar(30),--支払い
	insert_date datetime,
	delete_date datetime
);

INSERT INTO item_info_transaction(item_name, item_price, item_stock) VALUES("ノートBOOK", "100", "50");
INSERT INTO login_user_transaction(login_id, login_pass, user_name) VALUES("internous","internous01","test");
